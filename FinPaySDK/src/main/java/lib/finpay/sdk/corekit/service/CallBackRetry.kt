package lib.finpay.sdk.corekit.service

import com.google.gson.Gson
import lib.finpay.sdk.corekit.model.DataHistoryTransaction
import lib.finpay.sdk.corekit.model.Token
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


abstract class CallbackTokenWithRetry<T>(call: Call<T>) : Callback<T> {
    private val call: Call<T>
    private var retryCount = 0
    fun onFailure(t: Throwable) {
        println(TAG + " " + t.localizedMessage)
        if (retryCount++ < TOTAL_RETRIES) {
            println(TAG + " Retrying... (" + retryCount + " out of" + TOTAL_RETRIES + ")")
            retry()
        }
    }

    fun onResponse(response: Response<T>) {
        if (response.code() == 200) {
            val json = JSONObject(response.body().toString())
            val gson = Gson()
            val responses = gson.fromJson(json.toString(), Token::class.java)
            if (responses.statusCode == "000") {
                onFinalResponse(
                    call,
                    response
                )
            } else {
                if (retryCount++ < TOTAL_RETRIES) {
                    println(TAG + " Retrying... (" + retryCount + " out of" + TOTAL_RETRIES + ")")
                    retry()
                }
            }
        } else {
            if (retryCount++ < TOTAL_RETRIES) {
                println(TAG + " Retrying... (" + retryCount + " out of" + TOTAL_RETRIES + ")")
                retry()
            }
        }

    }

    open fun onFinalResponse(
        call: Call<T>?,
        response: Response<T>
    ) { // to be overriden by calling class
    }

    open fun onFinalFailure(call: Call<T>?, t: Throwable?) { // to be overriden by calling class
    }


    private fun retry() {
        call.clone().enqueue(this)
    }

    companion object {
        private const val TOTAL_RETRIES = 3
        private val TAG = CallbackTokenWithRetry::class.java.simpleName
    }

    init {
        this.call = call
    }
}