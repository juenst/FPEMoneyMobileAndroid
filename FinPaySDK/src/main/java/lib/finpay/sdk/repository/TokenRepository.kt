//package lib.finpay.sdk.repository
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import lib.finpay.sdk.constant.Constant
//import lib.finpay.sdk.model.TokenModel
//import lib.finpay.sdk.presentation.TokenResponse
//import lib.finpay.sdk.service.ApiClient
//import lib.finpay.sdk.service.ApiInterface
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.Field
//import retrofit2.http.FormUrlEncoded
//import retrofit2.http.GET
//import retrofit2.http.POST
//
//
//class TokenRepository {
//    var endpoints : ApiClient = ApiClient()
//
//    fun getToken() : LiveData<TokenResponse> {
//        val apiResponse = MutableLiveData<TokenResponse>()
//        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)
//        val call : Call<TokenModel> = apiService.getToken("getToken","json","en")
//        call.enqueue(object : Callback<TokenModel>{
//            override fun onFailure(call: Call<TokenModel>?, t: Throwable?) {
//                apiResponse.postValue(TokenResponse(t!!))
//            }
//
//            override fun onResponse(call: Call<TokenModel>?, response: Response<TokenModel>?) {
//                if (response!!.isSuccessful) {
//                    apiResponse.postValue(TokenResponse(response.body()!!))
//                }else{
//                    apiResponse.postValue(TokenResponse(response.code()))
//                }
//            }
//
//        })
//
//        return apiResponse
//    }
//}
//}