package lib.finpay.sdk.corekit.service

interface ApiCallback<T> {
    fun onException(error: Throwable)
    fun onSuccess(data: T)
    fun onError(error: String)
}