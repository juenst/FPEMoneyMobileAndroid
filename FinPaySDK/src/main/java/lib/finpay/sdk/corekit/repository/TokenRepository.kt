package lib.finpay.sdk.corekit.repository

import lib.finpay.sdk.corekit.helper.Signature
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.helper.DateHelper
import lib.finpay.sdk.corekit.helper.TransactionHelper
import lib.finpay.sdk.corekit.model.Token
import lib.finpay.sdk.corekit.service.BaseServices
import lib.finpay.sdk.corekit.service.network.Api
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class TokenRepository() {

    companion object {
        var tokenID: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.TOKEN_ID)!!
        var phoneNumber: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)!!
        var userName: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_USERNAME)!!
        var password: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_PASSWORD)!!
        var secretKey: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_SECRET_KEY)!!

        fun getToken(transNumber: String, onSuccess: (Token) -> Unit, onFailed: (String) -> Unit)  {
                //create signature
                var transactionNumber = TransactionHelper.getTransNumber(transNumber)
                val mapJson = mapOf(
                    "requestType" to "getToken",
                    "reqDtime" to DateHelper.getCurrentDate(),
                    "transNumber" to transactionNumber
                )
                FinpaySDK.signature = Signature()
                val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)

                //auth header
                val credential = Credentials.basic(userName, password)
                var header : HashMap<String, String> = hashMapOf()
                header["Authorization"] = credential

                //request body
                val requestBody : HashMap<String, String> = hashMapOf()
                requestBody["requestType"] = "getToken"
                requestBody["signature"] = signatureID
                requestBody["reqDtime"] = DateHelper.getCurrentDate()
                requestBody["transNumber"] = transactionNumber

                val request = BaseServices.getRetrofitInstanceCoBrand().create(Api::class.java)
                request.getToken(requestBody).enqueue(object : Callback<Token> {
                    override fun onFailure(call: Call<Token>, t: Throwable) {
                        onFailed(t.message.toString())
                    }
                    override fun onResponse(
                        call: Call<Token>,
                        response: Response<Token>
                    ) {
                        if (response.code() == 200) {
                            if (response.body()?.statusCode == "000") {
                                FinpaySDK.prefHelper.setStringToShared(
                                    SharedPrefKeys.TOKEN_ID,
                                    response.body()!!.tokenID.toString()
                                )
                                onSuccess(response.body()!!)
                            } else if (response.body()?.statusCode == "A1004") {
                                onFailed(response.body()?.statusDesc.toString())
                            } else if (response.body()?.statusCode == "A1003") {
                                onFailed(response.body()?.statusDesc.toString())
                            } else {
                                onFailed(response.body()?.statusDesc.toString())
                            }
//                        } else if(response.code() == 401) {
//                            println("test => "+response.code().toString())
//                            println("test => "+ response.message())
//                            if (response.body()?.statusCode == "A1004") {
//                                onFailed(response.body()?.statusDesc.toString())
//                            } else if (response.body()?.statusCode == "A1003") {
//                                onFailed(response.body()?.statusDesc.toString())
//                            } else {
//                                onFailed(response.body()?.statusDesc.toString())
//                            }
                        } else {
                            onFailed(Constant.defaultErrorMessage)
                        }
                    }
                })
        }
    }
}