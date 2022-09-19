package lib.finpay.sdk

import com.example.testing.Signature
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import lib.finpay.sdk.di.component.SdkComponent
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class FinPaySDK : HasAndroidInjector {
    @Inject
    lateinit var dispactchingActivityInjector: DispatchingAndroidInjector<Any>

    private lateinit var signature: Signature
    private lateinit var sdkComponent: SdkComponent

    override fun androidInjector(): AndroidInjector<Any> = dispactchingActivityInjector

    fun initSDK() {
        sdkComponent.inject(this)
    }

    fun getToken(
        merchantUsername: String,
        merchantPassword: String,
        merchantSecretKey: String,
        transNumber: String
    ): String? {
        val sdf = SimpleDateFormat("yyyyMMdHHmmss")
        val currentDate = sdf.format(Date())
        val mapJson = mapOf(
            "requestType" to "getToken",
            "reqDtime" to currentDate,
            "transNumber" to transNumber
        )
        signature = Signature()
        val signatureID = signature.createSignature(merchantSecretKey, mapJson)
        val requestBody: HashMap<String, String> = hashMapOf()
        requestBody["requestType"] = "getToken"
        requestBody["signature"] = signatureID
        requestBody["reqDtime"] = currentDate
        requestBody["transNumber"] = transNumber
        var tokenID: String? = ""
        return tokenID
    }

    fun getBalance(
        merchantUsername: String,
        merchantPassword: String,
        merchantSecretKey: String,
        transNumber: String,
        phoneNumber: String,
    ) {
        val sdf = SimpleDateFormat("yyyyMMdHHmmss")
        val currentDate = sdf.format(Date())
        val mapJson = mapOf(
            "requestType" to "getBalance",
            "reqDtime" to currentDate,
            "transNumber" to transNumber,
            "phoneNumber" to phoneNumber,
            "tokenID" to ""
        )
        signature = Signature()
        val signatureID = signature.createSignature(merchantSecretKey, mapJson)
        val requestBody : HashMap<String, String>  = hashMapOf()
        requestBody["requestType"] = "getToken"
        requestBody["signature"] = signatureID
        requestBody["reqDtime"] = currentDate
        requestBody["transNumber"] = transNumber
        requestBody["phoneNumber"] = phoneNumber
        requestBody["tokenID"] = transNumber
    }
}