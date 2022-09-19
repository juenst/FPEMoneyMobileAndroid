package lib.finpay.sdk.service.network

import lib.finpay.sdk.model.TokenModel
import retrofit2.Call


class ApiService constructor(api: Api) {
    var api: Api? = api
    private fun generateAuthenticationHeader(authKey: String): HashMap<String, String> {
        val authHeader: HashMap<String, String> = HashMap()
        authHeader["Authorization"] = authKey
        return authHeader
    }

    suspend fun getToken(body: HashMap<String, String>, token: String): Call<TokenModel> {
        val auth: HashMap<String, String> = generateAuthenticationHeader(token)
        return api!!.getToken(body)
    }
}