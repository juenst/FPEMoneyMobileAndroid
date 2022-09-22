package lib.finpay.sdk

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.testing.Signature
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lib.finpay.sdk.model.TokenModel
import lib.finpay.sdk.model.UserBallanceModel
import lib.finpay.sdk.repository.TokenRepository
import lib.finpay.sdk.service.ApiResult
import lib.finpay.sdk.service.BaseService
import lib.finpay.sdk.service.network.Api
import lib.finpay.sdk.viewmodel.TokenViewModel
//import lib.finpay.sdk.viewmodel.TokenViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.system.measureTimeMillis


class FinPaySDK{
    private lateinit var signature: Signature

    fun getToken(
        merchantUsername: String,
        merchantPassword: String,
        merchantSecretKey: String,
        transNumber: String,
        onSuccess: (TokenModel) -> Unit = {},
//        onError: (Throwable) -> Unit = {}
    ): TokenModel {
        val sdf = SimpleDateFormat("yyyyMMdHHmmss")
        val currentDate = sdf.format(Date())
        val mapJson = mapOf(
            "requestType" to "getToken",
            "reqDtime" to currentDate,
            "transNumber" to transNumber
        )
        signature = Signature()
        val signatureID = signature.createSignature(merchantSecretKey, mapJson)
        val request = BaseService.getRetrofitInstance().create(Api::class.java)
        val requestBody: HashMap<String, String> = hashMapOf()
        requestBody["requestType"] = "getToken"
        requestBody["signature"] = signatureID
        requestBody["reqDtime"] = currentDate
        requestBody["transNumber"] = transNumber

        val time = measureTimeMillis {
            request.getToken(requestBody).enqueue(object : Callback<TokenModel> {
                override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                    println("response failure")
                    println(t.message)
                }

                override fun onResponse(
                    call: Call<TokenModel>,
                    response: Response<TokenModel>
                ) {
                    if (response.code() == 200) {
                        if (response.body()?.getStatusCode() == "000") {
                            println("response ok")
                            println(response.body()?.getTokenID())
                            onSuccess(response.body()!!)
                        } else {
                            println("statusCode != 200")
                            println(response.body()?.getStatusDesc())
                        }
                    } else {
                        println("response code != 200")
                        print(response.body()?.getStatusDesc())
                    }
                }
            })
        }
        println("get data time $time ms")
        return TokenModel()
    }

    fun getBalance(
        merchantUsername: String,
        merchantPassword: String,
        merchantSecretKey: String,
        transNumber: String,
        phoneNumber: String,
        onSuccess: (UserBallanceModel) -> Unit = {},
    ) {
        val token: TokenModel
        token = getToken(
            merchantUsername = merchantUsername,
            merchantPassword = merchantPassword,
            merchantSecretKey = merchantSecretKey,
            transNumber = transNumber,
            onSuccess = {
                tokens->
                println("tokenID => ...")
                println(tokens.getTokenID().toString())
                val sdf = SimpleDateFormat("yyyyMMdHHmmss")
                val currentDate = sdf.format(Date())
                val mapJson = mapOf(
                    "requestType" to "getBalance",
                    "reqDtime" to currentDate,
                    "transNumber" to transNumber,
                    "phoneNumber" to phoneNumber,
                    "tokenID" to tokens.getTokenID().toString()
                )
                signature = Signature()
                val signatureID = signature.createSignature(merchantSecretKey, mapJson)
                val request = BaseService.getRetrofitInstance().create(Api::class.java)

                val requestBody : HashMap<String, String>  = hashMapOf()
                requestBody["requestType"] = "getBalance"
                requestBody["signature"] = signatureID
                requestBody["reqDtime"] = currentDate
                requestBody["transNumber"] = transNumber
                requestBody["phoneNumber"] = phoneNumber
                requestBody["tokenID"] = tokens.getTokenID().toString()

                request.getBalance(requestBody).enqueue(object : Callback<UserBallanceModel> {
                    override fun onFailure(call: Call<UserBallanceModel>, t: Throwable) {
                        println("response failure getUserBalance")
                        println(t.message)
                    }
                    override fun onResponse(call: Call<UserBallanceModel>, response: Response<UserBallanceModel>) {
                        if (response.code() == 200) {
                            if(response.body()?.getStatusCode() == "000") {
                                println("response ok getUserBalance")
                                println(response.body()?.getCustBalance())
                                onSuccess(response.body()!!)
                            } else {
                                println("statusCode != 200 getUserBalance")
                                println(response.body()?.getStatusDesc())
                            }
                        } else {
                            println("response code != 200 getUserBalance")
                            print(response.body()?.getStatusDesc())
                        }
                    }
                })
            },
        )
    }
}