package lib.finpay.sdk.corekit.repository

import com.example.testing.Signature
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.model.QrisInquiryModel
import lib.finpay.sdk.corekit.model.QrisPaymentModel
import lib.finpay.sdk.corekit.service.BaseServices
import lib.finpay.sdk.corekit.service.network.Api
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class QrisPayRepository() {

    companion object {
        private lateinit var signature: Signature

        fun inquiry(
            phoneNumber: String,
            tokenID: String,
            stringQris: String,
            onSuccess: (QrisInquiryModel) -> Unit,
            onFailed: (String) -> Unit)  {
                val sdf = SimpleDateFormat("yyyyMMdHHmmss")
                val currentDate = sdf.format(Date())
                val mapJson = mapOf(
                    "requestType" to "inquiryQrisMPM",
                    "reqDtime" to currentDate,
                    "transNumber" to currentDate,
                    "phoneNumber" to phoneNumber,
                    "tokenID" to tokenID,
                    "stringQris" to stringQris,
                )
                signature = Signature()
                val signatureID = signature.createSignature(mapJson)
                val credential = Credentials.basic(
                    Constant.userName,
                    Constant.password
                )
                var header : HashMap<String, String> = hashMapOf()
                header["Authorization"] = credential
                val requestBody : HashMap<String, String> = hashMapOf()
                requestBody["requestType"] = "inquiryQrisMPM"
                requestBody["signature"] = signatureID
                requestBody["reqDtime"] = currentDate
                requestBody["transNumber"] = currentDate
                requestBody["phoneNumber"] = phoneNumber
                requestBody["tokenID"] = tokenID
                requestBody["stringQris"] = stringQris

                val request = BaseServices.getRetrofitInstance().create(Api::class.java)

                request.qrisInquiry(requestBody).enqueue(object : Callback<QrisInquiryModel> {
                    override fun onFailure(call: Call<QrisInquiryModel>, t: Throwable) {
                        onFailed(t.message.toString())
                    }
                    override fun onResponse(
                        call: Call<QrisInquiryModel>,
                        response: Response<QrisInquiryModel>
                    ) {
                        if (response.code() == 200) {
                            if (response.body()?.statusCode == "000") {
                                onSuccess(response.body()!!)
                            } else {
                                onFailed(response.body()?.statusDesc.toString())
                            }
                        } else {
                            onFailed("Opss..something went wrong")
                        }
                    }
                })
        }

        fun payment(
            phoneNumber: String,
            tokenID: String,
            sof: String,
            amount: String,
            amountTips: String,
            reffFlag: String,
            pinToken: String,
            onSuccess: (QrisPaymentModel) -> Unit,
            onFailed: (String) -> Unit)  {
            val sdf = SimpleDateFormat("yyyyMMdHHmmss")
            val currentDate = sdf.format(Date())
            val mapJson = mapOf(
                "requestType" to "inquiryQrisMPM",
                "reqDtime" to currentDate,
                "transNumber" to currentDate,
                "phoneNumber" to phoneNumber,
                "tokenID" to tokenID,
                "sof" to sof,
                "amount" to amount,
                "amountTips" to amountTips,
                "reffFlag" to reffFlag,
                "pinToken" to pinToken,
            )
            signature = Signature()
            val signatureID = signature.createSignature(mapJson)
            val credential = Credentials.basic(
                Constant.userName,
                Constant.password
            )
            var header : HashMap<String, String> = hashMapOf()
            header["Authorization"] = credential
            val requestBody : HashMap<String, String> = hashMapOf()
            requestBody["requestType"] = "inquiryQrisMPM"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = currentDate
            requestBody["transNumber"] = currentDate
            requestBody["phoneNumber"] = phoneNumber
            requestBody["tokenID"] = tokenID
            requestBody["sof"] = sof
            requestBody["amount"] = amount
            requestBody["amountTips"] = amountTips
            requestBody["reffFlag"] = reffFlag
            requestBody["pinToken"] = pinToken

            val request = BaseServices.getRetrofitInstance().create(Api::class.java)

            request.qrisPayment(requestBody).enqueue(object : Callback<QrisPaymentModel> {
                override fun onFailure(call: Call<QrisPaymentModel>, t: Throwable) {
                    onFailed(t.message.toString())
                }
                override fun onResponse(
                    call: Call<QrisPaymentModel>,
                    response: Response<QrisPaymentModel>
                ) {
                    if (response.code() == 200) {
                        if (response.body()?.statusCode == "000") {
                            onSuccess(response.body()!!)
                        } else {
                            onFailed(response.body()?.statusDesc.toString())
                        }
                    } else {
                        onFailed("Opss..something went wrong")
                    }
                }
            })
        }
    }
}