package lib.finpay.sdk.domain.exception

data class Failure(val code: Int, val msg: String) : Throwable()
