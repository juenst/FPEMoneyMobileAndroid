package lib.finpay.sdk.corekit.repository

import lib.finpay.sdk.corekit.helper.Signature
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.model.Topup
import lib.finpay.sdk.corekit.model.TopupInquiry
import lib.finpay.sdk.corekit.model.WidgetTopUp
import lib.finpay.sdk.corekit.service.BaseServices
import lib.finpay.sdk.corekit.service.network.Api
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class TopupRepository() {

    companion object {
        var tokenID: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.TOKEN_ID)!!
        var phoneNumber: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)!!
        var userName: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_USERNAME)!!
        var password: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_PASSWORD)!!
        var secretKey: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_SECRET_KEY)!!

        fun topup(
            amount: String,
            sof: String,
            onSuccess: (TopupInquiry) -> Unit,
            onFailed: (String) -> Unit
        )  {
                //create signature
                val sdf = SimpleDateFormat("yyyyMMdHHmmss", Locale.ENGLISH)
                val currentDate = sdf.format(Date())
                val mapJson = mapOf(
                    "requestType" to "apiTopup",
                    "reqDtime" to currentDate,
                    "transNumber" to currentDate,
                    "amount" to amount,
                    "sof" to sof,
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
                requestBody["requestType"] = "apiTopup"
                requestBody["signature"] = signatureID
                requestBody["reqDtime"] = currentDate
                requestBody["transNumber"] = currentDate
                requestBody["phoneNumber"] = phoneNumber
                requestBody["tokenID"] = tokenID
                requestBody["amount"] = amount
                requestBody["sof"] = sof

                val request = BaseServices.getRetrofitInstanceCoBrand().create(Api::class.java)
                request.topup(requestBody).enqueue(object : Callback<TopupInquiry> {
                    override fun onFailure(call: Call<TopupInquiry>, t: Throwable) {
                        onFailed(t.message.toString())
                    }
                    override fun onResponse(
                        call: Call<TopupInquiry>,
                        response: Response<TopupInquiry>
                    ) {
                        if (response.code() == 200) {
                            if (response.body()?.statusCode == "000") {
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