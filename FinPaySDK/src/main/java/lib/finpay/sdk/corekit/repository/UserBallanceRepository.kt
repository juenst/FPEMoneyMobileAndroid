package lib.finpay.sdk.corekit.repository

import com.example.testing.Signature
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.model.UserBalance
import lib.finpay.sdk.corekit.service.BaseService
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

        fun getUserBallance(onSuccess: (UserBalance) -> Unit, onFailed: (String) -> Unit)  {
            //create signature
            val sdf = SimpleDateFormat("yyyyMMddHHmmss")
            val currentDate = sdf.format(Date())
            val mapJson = mapOf(
                "requestType" to "getBalance",
                "reqDtime" to currentDate,
                "transNumber" to currentDate,
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
            requestBody["reqDtime"] = currentDate
            requestBody["transNumber"] = currentDate
            requestBody["phoneNumber"] = phoneNumber
            requestBody["tokenID"] = tokenID

            val request = BaseServices.getRetrofitInstance().create(Api::class.java)
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
                            FinpaySDK.getToken({},{})
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