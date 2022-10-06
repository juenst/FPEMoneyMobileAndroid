package lib.finpay.sdk.base

import android.content.Context
import lib.finpay.sdk.FinPaySDK

public abstract class BaseSdKBuilder {
    protected var merchantUsername: String? = null
    protected var merchantPassword: String? = null
    protected var merchantSecretKey: String? = null
    protected var context: Context? = null

    fun BaseSdkBuilder() {}

//    abstract fun enableLog(var1: Boolean): T?

//    open fun buildSDK(): FinPaySDK? {
//        return if (this.isValidData()) {
//            MidtransSDK.delegateInstance(this)
//        } else {
//            com.midtrans.sdk.corekit.core.Logger.e("already performing an transaction")
//            null
//        }
//    }
}