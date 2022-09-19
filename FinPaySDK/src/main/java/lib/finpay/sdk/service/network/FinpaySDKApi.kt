package lib.finpay.sdk.service.network

import kotlinx.coroutines.Deferred
import lib.finpay.sdk.model.TokenModel
import lib.finpay.sdk.model.UserBallanceModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface FinpaySDKApi {
    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun getTokenAsync(
        //@HeaderMap auth: Map<String, String>,
        @Body body: HashMap<String, String>
    ): Deferred<Response<TokenModel>>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun getBalanceAsync(
        //@HeaderMap auth: Map<String, String>,
        @Body body: HashMap<String, String>
    ): Deferred<Response<UserBallanceModel>>
}