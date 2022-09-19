package lib.finpay.sdk.service.network

import lib.finpay.sdk.model.ApiResponse
import lib.finpay.sdk.model.TokenModel
import lib.finpay.sdk.model.UserBallanceModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface Api {
    @Headers("Content-Type:application/json")
    @POST("api.php")
    suspend fun getToken(
        //@HeaderMap auth: Map<String, String>,
        @Body body: HashMap<String, String>
    ): Call<TokenModel>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun getBalance(
        //@HeaderMap auth: Map<String, String>,
        @Body body: HashMap<String, String>
    ): Call<UserBallanceModel>
}
