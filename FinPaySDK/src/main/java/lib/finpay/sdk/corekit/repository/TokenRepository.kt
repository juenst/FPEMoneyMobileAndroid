package lib.finpay.sdk.corekit.repository

import lib.finpay.sdk.corekit.helper.Signature
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.model.Token
import lib.finpay.sdk.corekit.service.BaseServices
import lib.finpay.sdk.corekit.service.CallbackTokenWithRetry
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
        var phoneNumber: String =
            FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)!!
        var userName: String =
            FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_USERNAME)!!
        var password: String =
            FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_PASSWORD)!!
        var secretKey: String =
            FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_SECRET_KEY)!!

        fun getToken(onSuccess: (Token) -> Unit, onFailed: (String) -> Unit) {
            //create signature
            val sdf = SimpleDateFormat("yyyyMMdHHmmss")
            val currentDate = sdf.format(Date())
            val mapJson = mapOf(
                "requestType" to "getToken",
                "reqDtime" to currentDate,
                "transNumber" to currentDate
            )
            FinpaySDK.signature = Signature()
            val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)

            //auth header
            val credential = Credentials.basic(userName, password)
            var header: HashMap<String, String> = hashMapOf()
            header["Authorization"] = credential

            //request body
            val requestBody: HashMap<String, String> = hashMapOf()
            requestBody["requestType"] = "getToken"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = currentDate
            requestBody["transNumber"] = currentDate

            val request = BaseServices.getRetrofitInstanceCoBrand().create(Api::class.java)


            request.getToken(requestBody)
                .enqueue(object : CallbackTokenWithRetry<Token>(request.getToken(requestBody)) {
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
                            } else {
                                onFailed(response.body()?.statusDesc.toString())
                            }
                        } else {
                            onFailed(Constant.defaultErrorMessage)
                        }
                    }
                })
        }
    }
}