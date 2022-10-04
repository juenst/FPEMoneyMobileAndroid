package lib.finpay.sdk.repository

import com.example.testing.Signature
import lib.finpay.sdk.constant.Constant
import lib.finpay.sdk.model.ReqActivationModel
import lib.finpay.sdk.model.TokenModel
import lib.finpay.sdk.model.TransactionModel
import lib.finpay.sdk.service.BaseService
import lib.finpay.sdk.service.network.Api
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class TransactionRepository() {

    companion object {
        private lateinit var signature: Signature

        fun transaction(
            phoneNumber: String,
            tokenID: String,
            transAmount: String,
            transType: String,
            transDesc: String,
            dataBagi: String,
            onResult: (TransactionModel) -> Unit)  {
                val sdf = SimpleDateFormat("yyyyMMdHHmmss")
                val currentDate = sdf.format(Date())
                val mapJson = mapOf(
                    "requestType" to "transaction",
                    "reqDtime" to currentDate,
                    "transNumber" to currentDate,
                    "phoneNumber" to phoneNumber,
                    "tokenID" to tokenID,
                    "transAmount" to transAmount,
                    "transType" to transType,
                    "transDesc" to transDesc,
                    "dataBagi" to dataBagi
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
                requestBody["requestType"] = "transaction"
                requestBody["signature"] = signatureID
                requestBody["reqDtime"] = currentDate
                requestBody["transNumber"] = currentDate
                requestBody["phoneNumber"] = phoneNumber
                requestBody["tokenID"] = tokenID
                requestBody["transAmount"] = transAmount
                requestBody["transType"] = transType
                requestBody["transDesc"] = transDesc
                requestBody["dataBagi"] = dataBagi

                val request = BaseService.getRetrofitInstance().create(Api::class.java)

                request.transaction(requestBody).enqueue(object : Callback<TransactionModel> {
                    override fun onFailure(call: Call<TransactionModel>, t: Throwable) {
                        println("response failure")
                        println(t.message)
                    }
                    override fun onResponse(
                        call: Call<TransactionModel>,
                        response: Response<TransactionModel>
                    ) {
                        if (response.code() == 200) {
                            if (response.body()?.getStatusCode() == "000") {
                                onResult(response.body()!!)
                            } else {
                                println("statusCode != 200")
                                println(response.body()?.getStatusDesc())
                            }
                        } else {
                            println("response code != 200")
                        }
                    }
                })
        }
    }
}