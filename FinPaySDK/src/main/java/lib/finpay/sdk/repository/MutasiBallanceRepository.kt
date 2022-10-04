package lib.finpay.sdk.repository

import com.example.testing.Signature
import lib.finpay.sdk.constant.Constant
import lib.finpay.sdk.model.MutasiBallanceModel
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


class MutasiBallanceRepository() {

    companion object {
        private lateinit var signature: Signature

        fun mutasiBallance(
            phoneNumber: String,
            pin: String,
            custName: String,
            otp: String,
            custStatusCode: String,
            transAmount: String,
            transType: String,
            transDesc: String,
            startDate: String,
            endDate: String,
            tokenID: String,
            onResult: (MutasiBallanceModel) -> Unit)  {
                val sdf = SimpleDateFormat("yyyyMMdHHmmss")
                val currentDate = sdf.format(Date())
                val mapJson = mapOf(
                    "requestType" to "mutasiBalance",
                    "phoneNumber" to phoneNumber,
                    "pin" to pin,
                    "reqDtime" to currentDate,
                    "custName" to custName,
                    "otp" to otp,
                    "custStatusCode" to custStatusCode,
                    "transNumber" to currentDate,
                    "transAmount" to transAmount,
                    "transType" to transType,
                    "transDesc" to transDesc,
                    "startDate" to startDate,
                    "endDate" to endDate,
                    "tokenID" to tokenID,
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
                requestBody["requestType"] = "mutasiBalance"
                requestBody["phoneNumber"] = phoneNumber
                requestBody["pin"] = pin
                requestBody["reqDtime"] = currentDate
                requestBody["custName"] = custName
                requestBody["otp"] = otp
                requestBody["custStatusCode"] = custStatusCode
                requestBody["transNumber"] = currentDate
                requestBody["transAmount"] = transAmount
                requestBody["transType"] = transType
                requestBody["transDesc"] = transDesc
                requestBody["startDate"] = startDate
                requestBody["endDate"] = endDate
                requestBody["tokenID"] = tokenID

                val request = BaseService.getRetrofitInstance().create(Api::class.java)

                request.mutasiBallance(requestBody).enqueue(object : Callback<MutasiBallanceModel> {
                    override fun onFailure(call: Call<MutasiBallanceModel>, t: Throwable) {
                        println("response failure")
                        println(t.message)
                    }
                    override fun onResponse(
                        call: Call<MutasiBallanceModel>,
                        response: Response<MutasiBallanceModel>
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