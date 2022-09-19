package lib.finpay.sdk.service.network

import ApiResponse
import io.reactivex.Single
import lib.finpay.sdk.model.TokenModel
import retrofit2.Call
import javax.inject.Inject


public class ApiService {
    var api: Api? = null

    @Inject
    fun ApiService(api: Api?) {
        this.api = api
    }

    private fun generateAuthenticationHeader(authKey: String): HashMap<String, String> {
        val authHeader: HashMap<String, String> = HashMap()
        authHeader["Authorization"] = authKey
        return authHeader
    }

    fun getToken(body: HashMap<String, String>): Call<TokenModel> {
//        val auth: HashMap<String, String> = generateAuthenticationHeader(token)
        return api!!.getToken(body)
    }
}