package lib.finpay.sdk.service

interface ApiCallback<T> {
    fun onException(error: Throwable)
    fun onSuccess(data: T)
    fun onError(error: String)
}