package lib.finpay.sdk.corekit.repository

import lib.finpay.sdk.corekit.helper.Signature
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.helper.DateHelper
import lib.finpay.sdk.corekit.helper.TransactionHelper
import lib.finpay.sdk.corekit.model.UserBalance
import lib.finpay.sdk.corekit.service.BaseServices
import lib.finpay.sdk.corekit.service.network.Api
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class UserBallanceRepository() {

    companion object {
        var tokenID: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.TOKEN_ID)!!
        var phoneNumber: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)!!
        var userName: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_USERNAME)!!
        var password: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_PASSWORD)!!
        var secretKey: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_SECRET_KEY)!!

        fun getUserBallance(
            transNumber: String,
            onSuccess: (UserBalance) -> Unit,
            onFailed: (String) -> Unit)  {
            //create signature
            val mapJson = mapOf(
                "requestType" to "getBalance",
                "reqDtime" to DateHelper.getCurrentDate(),
                "transNumber" to TransactionHelper.getTransNumber(transNumber),
                "phoneNumber" to phoneNumber,
                "tokenID" to tokenID
            )
            FinpaySDK.signature = Signature()
            val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)

            //auth header
            val credential = Credentials.basic(userName, password)
            var header : HashMap<String, String> = hashMapOf()
            header["Authorization"] = credential

            //request body
            val requestBody : HashMap<String, String> = hashMapOf()
            requestBody["requestType"] = "getBalance"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = DateHelper.getCurrentDate()
            requestBody["transNumber"] = TransactionHelper.getTransNumber(transNumber)
            requestBody["phoneNumber"] = phoneNumber
            requestBody["tokenID"] = tokenID

            val request = BaseServices.getRetrofitInstanceCoBrand().create(Api::class.java)
            request.getUserBallance(requestBody).enqueue(object : Callback<UserBalance> {
                override fun onFailure(call: Call<UserBalance>, t: Throwable) {
                    onFailed(t.message!!)
                }
                override fun onResponse(
                    call: Call<UserBalance>,
                    response: Response<UserBalance>
                ) {
                    if (response.code() == 200) {
                        if (response.body()?.statusCode == "000") {
                            onSuccess(response.body()!!)
                        } else if(response.body()?.statusCode == "A2081")  {
                            //FinpaySDK.getToken({},{})
                            println("token expired")
                        } else {
                            onFailed(response.body()?.statusDesc!!)
                        }
                    } else {
                        onFailed(Constant.defaultErrorMessage)
                    }
                }
            })
        }
    }
}