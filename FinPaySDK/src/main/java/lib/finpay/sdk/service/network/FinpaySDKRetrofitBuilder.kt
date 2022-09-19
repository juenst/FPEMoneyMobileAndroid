package lib.finpay.sdk.service.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import lib.finpay.sdk.helper.BasicAuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FinPaySDKRetrofitBuilder {
    private val BASE_URL: String = "https://demos.finnet.co.id/emondev/"
    private val USERNAME: String = "MT77764DKM83N"
    private val PASSWORD: String = "YJV3AM0y"

    private val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder().addInterceptor(
        BasicAuthInterceptor(USERNAME, PASSWORD)
    ).apply { this.addInterceptor(interceptor) }.build()

    fun apiService(): FinpaySDKApi {
        return Retrofit.Builder().client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(FinpaySDKApi::class.java)
    }
}