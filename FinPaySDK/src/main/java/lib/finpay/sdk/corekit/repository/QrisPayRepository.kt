package lib.finpay.sdk.corekit.repository

import lib.finpay.sdk.corekit.helper.Signature
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.helper.DateHelper
import lib.finpay.sdk.corekit.helper.TransactionHelper
import lib.finpay.sdk.corekit.model.QrisInquiry
import lib.finpay.sdk.corekit.model.QrisPayment
import lib.finpay.sdk.corekit.service.BaseServices
import lib.finpay.sdk.corekit.service.network.Api
import lib.finpay.sdk.uikit.utilities.PrefHelper
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class QrisPayRepository() {

    companion object {
        var tokenID: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.TOKEN_ID)!!
        var phoneNumber: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)!!
        var userName: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_USERNAME)!!
        var password: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_PASSWORD)!!
        var secretKey: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_SECRET_KEY)!!

        fun inquiry(
            transNumber: String,
            stringQris: String,
            onSuccess: (QrisInquiry) -> Unit,
            onFailed: (String) -> Unit)  {
                //create signature
                val mapJson = mapOf(
                    "requestType" to "inquiryQrisMPM",
                    "reqDtime" to DateHelper.getCurrentDate(),
                    "transNumber" to TransactionHelper.getTransNumber(transNumber),
                    "phoneNumber" to phoneNumber,
                    "tokenID" to tokenID,
                    "stringQris" to stringQris,
                )
                FinpaySDK.signature = Signature()
                val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)

                //auth header
                val credential = Credentials.basic(userName, password)
                var header : HashMap<String, String> = hashMapOf()
                header["Authorization"] = credential

                //request body
                val requestBody : HashMap<String, String> = hashMapOf()
                requestBody["requestType"] = "inquiryQrisMPM"
                requestBody["signature"] = signatureID
                requestBody["reqDtime"] = DateHelper.getCurrentDate()
                requestBody["transNumber"] = TransactionHelper.getTransNumber(transNumber)
                requestBody["phoneNumber"] = phoneNumber
                requestBody["tokenID"] = tokenID
                requestBody["stringQris"] = stringQris

                val request = BaseServices.getRetrofitInstanceCoBrand().create(Api::class.java)
                request.qrisInquiry(requestBody).enqueue(object : Callback<QrisInquiry> {
                    override fun onFailure(call: Call<QrisInquiry>, t: Throwable) {
                        onFailed(t.message.toString())
                    }
                    override fun onResponse(
                        call: Call<QrisInquiry>,
                        response: Response<QrisInquiry>
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

        fun payment(
            transNumber: String,
            sof: String,
            amount: String,
            amountTips: String,
            reffFlag: String,
            pinToken: String,
            onSuccess: (QrisPayment) -> Unit,
            onFailed: (String) -> Unit)  {
                //create signature
                val mapJson = mapOf(
                    "requestType" to "inquiryQrisMPM",
                    "reqDtime" to DateHelper.getCurrentDate(),
                    "transNumber" to TransactionHelper.getTransNumber(transNumber),
                    "phoneNumber" to phoneNumber,
                    "tokenID" to tokenID,
                    "sof" to sof,
                    "amount" to amount,
                    "amountTips" to amountTips,
                    "reffFlag" to reffFlag,
                    "pinToken" to pinToken,
                )
                FinpaySDK.signature = Signature()
                val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)

                //auth header
                val credential = Credentials.basic(userName, password)
                var header : HashMap<String, String> = hashMapOf()
                header["Authorization"] = credential

                //request body
                val requestBody : HashMap<String, String> = hashMapOf()
                requestBody["requestType"] = "inquiryQrisMPM"
                requestBody["signature"] = signatureID
                requestBody["reqDtime"] = DateHelper.getCurrentDate()
                requestBody["transNumber"] = TransactionHelper.getTransNumber(transNumber)
                requestBody["phoneNumber"] = phoneNumber
                requestBody["tokenID"] = tokenID
                requestBody["sof"] = sof
                requestBody["amount"] = amount
                requestBody["amountTips"] = amountTips
                requestBody["reffFlag"] = reffFlag
                requestBody["pinToken"] = pinToken

                val request = BaseServices.getRetrofitInstanceCoBrand().create(Api::class.java)
                request.qrisPayment(requestBody).enqueue(object : Callback<QrisPayment> {
                    override fun onFailure(call: Call<QrisPayment>, t: Throwable) {
                        onFailed(t.message.toString())
                    }
                    override fun onResponse(
                        call: Call<QrisPayment>,
                        response: Response<QrisPayment>
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