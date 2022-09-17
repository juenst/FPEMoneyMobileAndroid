package lib.finpay.sdk.service

import com.google.gson.GsonBuilder
import lib.finpay.sdk.model.TokenModel
import lib.finpay.sdk.model.TokenRequestModel
import okhttp3.Credentials
import okhttp3.Interceptor
//import lib.finpay.sdk.repository.TokenRepository
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {
//    @Headers("Content-Type:application/json", "Authorization : Basic TVQ3Nzc2NERLTTgzTjpZSlYzQU0weQ==")
    @Headers("Content-Type:application/json")
//    @Headers("Authorization : Basic TVQ3Nzc2NERLTTgzTjpZSlYzQU0weQ==")
    @POST("api.php")
    fun getToken(@Body info: TokenRequestModel): Call<TokenModel>
}

class RetrofitInstance {
    companion object {
        val BASE_URL: String = "https://demos.finnet.co.id/emondev/"

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(BasicAuthInterceptor("MT77764DKM83N", "YJV3AM0y")).apply {
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

class BasicAuthInterceptor(username: String, password: String): Interceptor {
    private var credentials: String = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()
        return chain.proceed(request)
    }
}
