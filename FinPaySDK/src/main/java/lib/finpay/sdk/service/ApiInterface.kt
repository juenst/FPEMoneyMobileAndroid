package lib.finpay.sdk.service

import lib.finpay.sdk.model.TokenModel
import lib.finpay.sdk.model.TokenRequestModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun getToken(@Body info: TokenRequestModel): Call<ResponseBody>
}
