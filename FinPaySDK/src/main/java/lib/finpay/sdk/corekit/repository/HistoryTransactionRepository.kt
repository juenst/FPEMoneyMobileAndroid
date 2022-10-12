package lib.finpay.sdk.corekit.repository

import com.example.testing.Signature
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.model.HistoryTransactionModel
import lib.finpay.sdk.corekit.service.BaseService
import lib.finpay.sdk.corekit.service.network.Api
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class HistoryTransactionRepository() {

    companion object {
        private lateinit var signature: Signature

        fun getHistoryTransaction(
            onResult: (HistoryTransactionModel) -> Unit)  {
                val sdf = SimpleDateFormat("yyyyMMdHHmmss")
                val currentDate = sdf.format(Date())
                val mapJson = mapOf(
                    "requestType" to "getToken",
                    "reqDtime" to currentDate,
                    "transNumber" to currentDate
                )
                signature = Signature()
                val signatureID = signature.createSignature(mapJson, "daYumnMb")
                val credential = Credentials.basic(
                    Constant.userName,
                    Constant.password
                )
                var header : HashMap<String, String> = hashMapOf()
                header["Authorization"] = credential
                val requestBody : HashMap<String, String> = hashMapOf()
                requestBody["requestType"] = "getToken"
                requestBody["signature"] = signatureID
                requestBody["reqDtime"] = currentDate
                requestBody["transNumber"] = currentDate

                val request = BaseService.getRetrofitInstance().create(Api::class.java)

                request.getHistoryTransaction(requestBody).enqueue(object : Callback<HistoryTransactionModel> {
                    override fun onFailure(call: Call<HistoryTransactionModel>, t: Throwable) {
                        println("response failure")
                        println(t.message)
                    }
                    override fun onResponse(
                        call: Call<HistoryTransactionModel>,
                        response: Response<HistoryTransactionModel>
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