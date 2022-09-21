//package com.finpay.wallet.repository.login
//
//import androidx.lifecycle.MutableLiveData
//import com.google.gson.Gson
//import com.finpay.wallet.R
//import com.finpay.wallet.service.network.Api
//import com.finpay.wallet.service.network.ApiResult
//import com.finpay.wallet.utilities.authorization
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import okhttp3.RequestBody
//import retrofit2.HttpException
//import java.net.UnknownHostException
//import javax.inject.Inject
//
//class CheckBorrowerRepository @Inject constructor(private val api: Api) {
//
////    fun checkBorrower(mobile: String) : MutableLiveData<ApiResult<CheckBorrower>> {
////        val result = MutableLiveData<ApiResult<CheckBorrower>>()
////        result.postValue(ApiResult.loading())
////        GlobalScope.launch {
////            try {
////                val response = api.checkBorrower(mobile)
////                result.postValue(ApiResult.success(response))
////            }
////            catch (e: Exception)
////            {
////                when(e) {
////                    is UnknownHostException -> {
////                        result.postValue(ApiResult.errorInt(R.string.srSomethingWentWrongPleaseTryAgainLater))
////                    }
////                    is HttpException -> {
////                        if(e.response()!=null){
////                            val errorJson = e.response()!!.errorBody()!!.string()
////                            //val responseApi = Gson().fromJson(errorJson, ResponseApi::class.java)
////                        }
////                        result.postValue(ApiResult.errorInt(R.string.srServerError))
////                    }
////                    else -> {
////                        result.postValue(ApiResult.errorInt(R.string.srServerError))
////                    }
////                }
////            }
////        }
////        return result
////    }
//
//}