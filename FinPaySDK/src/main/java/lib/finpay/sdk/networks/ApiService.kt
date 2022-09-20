package lib.finpay.sdk.networks

import kotlinx.coroutines.Deferred
import lib.finpay.sdk.model.TokenModel
import lib.finpay.sdk.model.UserBallanceModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type:application/json")
    @POST("api.php")
    suspend fun getTokenAsync(
        @Body body: HashMap<String, String>
    ): Response<TokenModel>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    suspend fun getBalanceAsync(
        @Body body: HashMap<String, String>
    ): Response<UserBallanceModel>
}

