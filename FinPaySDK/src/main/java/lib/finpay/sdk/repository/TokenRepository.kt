package lib.finpay.sdk.repository

import androidx.lifecycle.MutableLiveData
import com.example.testing.Signature
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lib.finpay.sdk.R
import lib.finpay.sdk.model.TokenModel
import lib.finpay.sdk.service.network.Api
import lib.finpay.sdk.service.ApiResult
import lib.finpay.sdk.service.BaseService
import okhttp3.Credentials
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class TokenRepository @Inject constructor(private val api: Api) {
    private lateinit var signature: Signature

    fun getToken(merchantUsername: String, merchantPassword: String, merchantSecretKey: String, transNumber: String) : MutableLiveData<ApiResult<TokenModel>> {
        val sdf = SimpleDateFormat("yyyyMMdHHmmss")
        val currentDate = sdf.format(Date())
        val mapJson = mapOf(
            "requestType" to "getToken",
            "reqDtime" to currentDate,
            "transNumber" to transNumber
        )
        signature = Signature()
        val signatureID = signature.createSignature(merchantSecretKey, mapJson)
        val credential = Credentials.basic(merchantUsername, merchantPassword)
        var header : HashMap<String, String> = hashMapOf()
        header["Authorization"] = credential
        val requestBody : HashMap<String, String> = hashMapOf()
        requestBody["requestType"] = "getToken"
        requestBody["signature"] = signatureID
        requestBody["reqDtime"] = currentDate
        requestBody["transNumber"] = transNumber

        val tokenResult = MutableLiveData<ApiResult<TokenModel>>()
        tokenResult.postValue(ApiResult.loading())
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.getTokens(header, requestBody)
                if(response.getStatusCode() == "000") {
                    println("success")
                    tokenResult.postValue(ApiResult.success(response))
                }else{
                    println("test")
                }
            } catch (e: Exception) {
                when(e) {
                    is UnknownHostException -> {
                        tokenResult.postValue(ApiResult.errorInt(R.string.srSomethingWentWrongPleaseTryAgainLater))
                    }
                    is HttpException -> {
                        tokenResult.postValue(ApiResult.errorInt(R.string.srServerError))
                    }
                    is ConnectException -> {
                        tokenResult.postValue(ApiResult.errorInt(R.string.srSomethingWentWrongPleaseTryAgainLater))
                    }
                    else -> {
                        tokenResult.postValue(ApiResult.errorInt(R.string.srServerError))
                    }
                }
            }
        }
        return tokenResult
    }
}