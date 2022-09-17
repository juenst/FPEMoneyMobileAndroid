package lib.finpay.sdk.service

import lib.finpay.sdk.model.TokenRequestModel
//import lib.finpay.sdk.repository.TokenRepository
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {
    @Headers("Content-Type:application/json", "Authorization","Basic TVQ3Nzc2NERLTTgzTjpZSlYzQU0weQ==")
    @POST("api.php")
    fun getToken(@Body info: TokenRequestModel): retrofit2.Call<ResponseBody>

//    @Headers("Content-Type:application/json")
//    @POST("users")
//    fun registerUser(
//        @Body info: UserBody
//    ): retrofit2.Call<ResponseBody>
}

class RetrofitInstance {
    companion object {
        val BASE_URL: String = "https://demos.finnet.co.id/emondev/"

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
