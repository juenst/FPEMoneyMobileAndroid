package lib.finpay.sdk.repository

import com.example.testing.Signature
import lib.finpay.sdk.constant.Constant
import lib.finpay.sdk.model.ReqActivationModel
import lib.finpay.sdk.model.ReqConfirmationModel
import lib.finpay.sdk.service.BaseService
import lib.finpay.sdk.service.network.Api
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class ReqConfirmationRepository() {

    companion object {
        private lateinit var signature: Signature

        fun reqConfirmation(
            phoneNumber: String,
            tokenID: String,
            custName: String,
            otp: String,
            custStatusCode: String,
            onResult: (ReqConfirmationModel) -> Unit)  {
                val sdf = SimpleDateFormat("yyyyMMdHHmmss")
                val currentDate = sdf.format(Date())
                val mapJson = mapOf(
                    "requestType" to "reqConfirmation",
                    "reqDtime" to currentDate,
                    "transNumber" to currentDate,
                    "phoneNumber" to phoneNumber,
                    "tokenID" to tokenID,
                    "custName" to custName,
                    "otp" to otp,
                    "custStatusCode" to custStatusCode
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
                requestBody["requestType"] = "reqConfirmation"
                requestBody["signature"] = signatureID
                requestBody["reqDtime"] = currentDate
                requestBody["transNumber"] = currentDate
                requestBody["phoneNumber"] = phoneNumber
                requestBody["tokenID"] = tokenID
                requestBody["custName"] = custName
                requestBody["otp"] = otp
                requestBody["custStatusCode"] = custStatusCode

                val request = BaseService.getRetrofitInstance().create(Api::class.java)

                request.reqConfirmation(requestBody).enqueue(object : Callback<ReqConfirmationModel> {
                    override fun onFailure(call: Call<ReqConfirmationModel>, t: Throwable) {
                        println("response failure")
                        println(t.message)
                    }
                    override fun onResponse(
                        call: Call<ReqConfirmationModel>,
                        response: Response<ReqConfirmationModel>
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