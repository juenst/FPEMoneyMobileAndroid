package lib.finpay.sdk.repository

import com.example.testing.Signature
import lib.finpay.sdk.constant.Constant
import lib.finpay.sdk.model.TokenModel
import lib.finpay.sdk.model.UserBallanceModel
import lib.finpay.sdk.service.BaseService
import lib.finpay.sdk.service.network.Api
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class UserBallanceRepository() {

    companion object {
        private lateinit var signature: Signature

        fun getUserBallance(
            phoneNumber: String,
            tokenID: String,
            onResult: (UserBallanceModel) -> Unit)  {
                val sdf = SimpleDateFormat("yyyyMMdHHmmss")
                val currentDate = sdf.format(Date())
                val mapJson = mapOf(
                    "requestType" to "getBalance",
                    "reqDtime" to currentDate,
                    "transNumber" to currentDate,
                    "phoneNumber" to phoneNumber,
                    "tokenID" to tokenID
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
                requestBody["requestType"] = "getBalance"
                requestBody["signature"] = signatureID
                requestBody["reqDtime"] = currentDate
                requestBody["transNumber"] = currentDate
                requestBody["phoneNumber"] = phoneNumber
                requestBody["tokenID"] = tokenID

                val request = BaseService.getRetrofitInstance().create(Api::class.java)

                request.getUserBallance(requestBody).enqueue(object : Callback<UserBallanceModel> {
                    override fun onFailure(call: Call<UserBallanceModel>, t: Throwable) {
                        println("response failure")
                        println(t.message)
                    }
                    override fun onResponse(
                        call: Call<UserBallanceModel>,
                        response: Response<UserBallanceModel>
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