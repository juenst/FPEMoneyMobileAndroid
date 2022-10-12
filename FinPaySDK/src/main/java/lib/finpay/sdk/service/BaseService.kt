package lib.finpay.sdk.service

import lib.finpay.sdk.helper.BasicAuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseService {
    companion object {
        val BASE_URL: String = "https://demos.finnet.co.id/emondev/"

        val BASE_URL_COBRAND: String = "https://demos.finnet.co.id/apicobrand/"


        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(
        BasicAuthInterceptor("MT77764DKM83N", "YJV3AM0y")).apply {
            this.addInterceptor(interceptor)
        }.build()



        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getRetrofitInstance2(
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL_COBRAND)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}