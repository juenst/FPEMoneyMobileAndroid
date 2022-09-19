//package lib.finpay.sdk.viewmodel
//
//import com.example.testing.Signature
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.observers.DisposableSingleObserver
//import io.reactivex.schedulers.Schedulers
//import lib.finpay.sdk.base.BaseViewModel
//import lib.finpay.sdk.model.TokenModel
//import lib.finpay.sdk.service.BaseService
//import lib.finpay.sdk.service.network.Api
//import lib.finpay.sdk.service.network.ApiService
//import java.text.SimpleDateFormat
//import java.util.*
//
//abstract class TokenViewModel : BaseViewModel() {
//    private val TAG = "TokenViewModel"
//    private lateinit var service: ApiService
//    private lateinit var signature: Signature
//
//    open fun getToken(
//        merchantUsername: String,
//        merchantPassword: String,
//        merchantSecretKey: String,
//        transNumber: String
//    ) {
//        isLoading.setValue(false)
//        val sdf = SimpleDateFormat("yyyyMMdHHmmss")
//        val currentDate = sdf.format(Date())
//        val mapJson = mapOf(
//            "requestType" to "getToken",
//            "reqDtime" to currentDate,
//            "transNumber" to transNumber
//        )
//        signature = Signature()
//        val signatureID = signature.createSignature(merchantSecretKey, mapJson)
//        val retIn = BaseService.getRetrofitInstance().create(Api::class.java)
//
//        val requestBody : HashMap<String, String> = hashMapOf()
//        requestBody["requestType"] = "getToken"
//        requestBody["signature"] = signatureID
//        requestBody["reqDtime"] = currentDate
//        requestBody["transNumber"] = transNumber
//
//        disposable.add(
//            service.getToken(requestBody)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSuccess { response ->
//                    if (response.getStatus() === 200) {
//
//                    }
//                }
//                .subscribeWith(object : DisposableSingleObserver<TokenModel>() {
//                    override fun onSuccess(response: TokenModel) {
//                        isLoading.setValue(false)
//                        if (response.getStatusCode() !== "000") {
//                            errorMessage.setValue(response.getStatusDesc())
//                            return
//                        }
//                        errorMessage.setValue(null)
//                    }
//
//                    override fun onError(e: Throwable) {
//                        isLoading.setValue(false)
//                        errorMessage.setValue(e.message)
//                    }
//                })
//        )
//    }
//}