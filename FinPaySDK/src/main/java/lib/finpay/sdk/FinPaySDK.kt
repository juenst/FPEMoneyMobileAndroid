package lib.finpay.sdk

import com.example.testing.Signature
import lib.finpay.sdk.model.TokenModel
import lib.finpay.sdk.service.BaseService
import lib.finpay.sdk.service.network.Api
import lib.finpay.sdk.service.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class FinPaySDK{
    var service: ApiService? = null
    private lateinit var signature: Signature

    fun getToken(
        merchantUsername: String,
        merchantPassword: String,
        merchantSecretKey: String,
        transNumber: String
    ) : String? {
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

        val requestBody : HashMap<String, String>  = hashMapOf()
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
            override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                if (response.code() == 200) {
                    if(response.body()?.getStatusCode() == "000") {
                        println("response ok")
                        println(response.body()?.getTokenID())
                        tokenID = response.body()?.getTokenID().toString()
                    } else {
                        println("statusCode != 200")
                        println(response.body()?.getStatusDesc())
                        tokenID = ""
                    }
                } else {
                    println("response code != 200")
                    print(response.body()?.getStatusDesc())
                    tokenID = ""
                }
            }
        })
        println("tokenID => " + tokenID)
        return tokenID
    }
}