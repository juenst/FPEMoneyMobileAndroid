package lib.finpay.sdk.service.network

import ApiResponse
import io.reactivex.Single
import lib.finpay.sdk.model.TokenModel
import lib.finpay.sdk.model.UserBallanceModel
import retrofit2.Call
import retrofit2.http.*


interface Api {
    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun getToken(
        @Body body: HashMap<String, String>
    ): Call<TokenModel>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun getBalance(
        @Body body: HashMap<String, String>
    ): Call<UserBallanceModel>

    @FormUrlEncoded
    @Headers("Content-Type:application/json")
    @POST("api.php")
    suspend fun getTokens(
        @HeaderMap authorization: HashMap<String, String>,
        @Body body: HashMap<String, String>
    ): TokenModel
}
