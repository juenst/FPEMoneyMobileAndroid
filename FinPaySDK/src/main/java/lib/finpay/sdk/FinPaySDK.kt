package lib.finpay.sdk

import android.content.SharedPreferences
import com.example.testing.Signature
import lib.finpay.sdk.model.TokenModel
import lib.finpay.sdk.model.TokenRequestModel
import lib.finpay.sdk.service.Api
import lib.finpay.sdk.service.BaseService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class FinPaySDK {
    private lateinit var signature: Signature
    private lateinit var sharedPreferences: SharedPreferences

    fun getToken(
        secretKey: String,
        userName: String,
        password: String,
        phoneNumber: String,
        transNumber: String
    ) : String {
        val sdf = SimpleDateFormat("yyyyMMdHHmmss")
        val currentDate = sdf.format(Date())
        print(currentDate)
        val mapJson = mapOf(
            "requestType" to "getToken",
            "reqDtime" to currentDate,
            "transNumber" to transNumber
        )
        signature = Signature()
        val signatureID = signature.createSignature(secretKey, mapJson)
        val retIn = BaseService.getRetrofitInstance().create(Api::class.java)
        val requestInfo = TokenRequestModel(
            requestType = "getToken",
            signature = signatureID,
            reqDtime = currentDate,
            transNumber = transNumber
        )
        var tokenID = ""
        retIn.getToken(requestInfo).enqueue(object : Callback<TokenModel> {
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
                        print(response.body()?.getStatusDesc())
                        tokenID = ""
                    }
                } else {
                    println("response code != 200")
                    print(response.body()?.getStatusDesc())
                    tokenID = ""
                }
            }
        })
        return tokenID
    }
}