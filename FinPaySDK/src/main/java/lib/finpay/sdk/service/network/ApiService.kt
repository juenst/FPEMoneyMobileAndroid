package lib.finpay.sdk.service.network

import lib.finpay.sdk.model.ApiResponse
import io.reactivex.Single
import lib.finpay.sdk.model.TokenModel
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

    fun getToken(body: HashMap<String, String>, token: String): Single<ApiResponse<TokenModel>> {
        val auth: HashMap<String, String> = generateAuthenticationHeader(token)
        return api!!.getTokens(auth, body)
    }
}