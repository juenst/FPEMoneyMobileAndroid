package lib.finpay.sdk.corekit.base

import android.content.Context
import lib.finpay.sdk.corekit.FinpaySDK

public abstract class BaseSdKBuilder<T> {
    protected var username: String? = null
    protected var password: String? = null
    protected var clientKey: String? = null
    protected var context: Context? = null

    fun BaseSdkBuilder() {}

    abstract fun enableLog(var1: Boolean): T?

    open fun buildSDK(): FinpaySDK? {
        return if (this.isValidData()) {
            //FinPaySDK.delegateInstance(this)
            println("ok")
            FinpaySDK()
        } else {
            //Logger.e("already performing an transaction")
            null
        }
    }

    open fun isValidData(): Boolean {
        var message: String
        var exception: RuntimeException
        if (this.username == null || this.password == null || this.clientKey == null || context == null) {
            message = "Client key  and context cannot be null or empty. Please set the client key and context"
            exception = RuntimeException(message)
            //com.midtrans.sdk.corekit.core.Logger.e(var1, var2)
            println(message)
            println(exception)
        }
        return true
    }
}