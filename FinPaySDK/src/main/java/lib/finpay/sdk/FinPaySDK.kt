package lib.finpay.sdk

import com.example.testing.Signature
import lib.finpay.sdk.model.TokenModel
import lib.finpay.sdk.model.UserBallanceModel
import lib.finpay.sdk.service.BaseService
import lib.finpay.sdk.service.network.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class FinPaySDK{
    //    var service: ApiService? = null
    private lateinit var signature: Signature

    suspend fun getToken(
        merchantUsername: String,
        merchantPassword: String,
        merchantSecretKey: String,
        transNumber: String
    ): String? {
        val sdf = SimpleDateFormat("yyyyMMdHHmmss")
        val currentDate = sdf.format(Date())
        val mapJson = mapOf(
            "requestType" to "getToken",
            "reqDtime" to currentDate,
            "transNumber" to transNumber
        )
        signature = Signature()
        val signatureID = signature.createSignature(merchantSecretKey, mapJson)
        val retIn = BaseService.getRetrofitInstance().create(Api::class.java)

        val requestBody: HashMap<String, String> = hashMapOf()
        requestBody["requestType"] = "getToken"
        requestBody["signature"] = signatureID
        requestBody["reqDtime"] = currentDate
        requestBody["transNumber"] = transNumber

        var tokenID: String? = ""
        retIn.getToken(requestBody).enqueue(object : Callback<TokenModel> {
            override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                println("response failure")
                println(t.message)
                tokenID = ""
            }

            override fun onResponse(
                call: Call<TokenModel>,
                response: Response<TokenModel>
            ) {
                if (response.code() == 200) {
                    if (response.body()?.statusCode == "000") {
                        println("response ok")
                        println("Response Body Get Token => TokenId : ${response.body()?.tokenID}")
                        tokenID = response.body()?.tokenID
                    } else {
                        println("statusCode != 200")
                        println(response.body()?.statusDesc)
                        tokenID = ""
                    }
                } else {
                    println("response code != 200")
                    print(response.body()?.statusDesc)
                    tokenID = ""
                }
            }
        })
        println("tokenID => $tokenID")
        return tokenID
    }

    fun getBalance(
        merchantUsername: String,
        merchantPassword: String,
        merchantSecretKey: String,
        transNumber: String,
        phoneNumber: String,
    ) {
        val sdf = SimpleDateFormat("yyyyMMdHHmmss")
        val currentDate = sdf.format(Date())
        val mapJson = mapOf(
            "requestType" to "getBalance",
            "reqDtime" to currentDate,
            "transNumber" to transNumber,
            "phoneNumber" to phoneNumber,
            "tokenID" to ""
        )

        signature = Signature()
        val signatureID = signature.createSignature(merchantSecretKey, mapJson)
        val retIn = BaseService.getRetrofitInstance().create(Api::class.java)

        val requestBody: HashMap<String, String> = hashMapOf()
        requestBody["requestType"] = "getToken"
        requestBody["signature"] = signatureID
        requestBody["reqDtime"] = currentDate
        requestBody["transNumber"] = transNumber
        requestBody["phoneNumber"] = phoneNumber
        requestBody["tokenID"] = transNumber

        retIn.getBalance(requestBody).enqueue(object : Callback<UserBallanceModel> {
            override fun onFailure(call: Call<UserBallanceModel>, t: Throwable) {
                println("response failure")
                println(t.message)
            }

            override fun onResponse(
                call: Call<UserBallanceModel>,
                response: Response<UserBallanceModel>
            ) {
                if (response.code() == 200) {
                    if (response.body()?.statusCode == "000") {
                        println("response ok")
                        println(response.body()?.custBalance)
                    } else {
                        println("statusCode != 200")
                        println(response.body()?.statusDesc)
                    }
                } else {
                    println("response code != 200")
                    print(response.body()?.statusDesc)
                }
            }
        })
    }
}