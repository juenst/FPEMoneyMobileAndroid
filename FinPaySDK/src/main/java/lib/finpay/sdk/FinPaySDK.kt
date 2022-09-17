package lib.finpay.sdk

import com.example.testing.Signature
import lib.finpay.sdk.model.TokenModel
import lib.finpay.sdk.model.TokenRequestModel
import lib.finpay.sdk.service.ApiInterface
import lib.finpay.sdk.service.BaseService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class FinPaySDK {
    private lateinit var signature: Signature

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
        val retIn = BaseService.getRetrofitInstance().create(ApiInterface::class.java)
        val requestInfo = TokenRequestModel(
            requestType = "getToken",
            signature = signatureID,
            reqDtime = currentDate,
            transNumber = transNumber
        )
        var tokenID = ""
        retIn.getToken(requestInfo).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println("response failure")
                println(t.message)
                tokenID = t.message.toString()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {
                    println("response ok")
                    println(response.body()?.string())
                    tokenID = ""
                } else {
                    println("response code bukan 200")
                    print(response)
                    tokenID = ""//response.body()?.validSIgnature.toString()
                }
            }
        })
        return tokenID
    }
}