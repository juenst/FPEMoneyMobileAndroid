package lib.finpay.sdk.service

import lib.finpay.sdk.model.TokenModel
import lib.finpay.sdk.model.TokenRequestModel
import okhttp3.ResponseBody
//import org.graalvm.compiler.nodes.memory.MemoryCheckpoint.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST


interface Api {
    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun getToken(@Body info: TokenRequestModel): Call<TokenModel>

//    @Headers("Content-Type:application/json")
//    @POST("api.php")
////    fun getUserBallance(@Body info: TokenRequestModel): Call<ResponseBody>
//    fun getUserBallance(
//        @HeaderMap auth: Map<String?, String?>?,
//        @Body body: HashMap<String?, String?>?
//    ): Call<ApiResponse<MutableList<OvertimeRequest?>?>?>?
}
