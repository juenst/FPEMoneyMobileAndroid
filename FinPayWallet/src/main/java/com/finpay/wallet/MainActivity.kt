package com.finpay.wallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.midtrans.sdk.uikit.SdkUIFlowBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mains)
    }

    private fun makePayment() {
//        SdkUIFlowBuilder.init()
//            .setContext(this)
//            .setMerchantBaseUrl(BuildConfig.BASE_URL)
//            .setClientKey(BuildConfig.CLIENT_KEY)
//            .setTransactionFinishedCallback(this)
//            .enableLog(true)
//            .setColorTheme(new CustomColorTheme("#777777","#f77474" , "#3f0d0d"))
//             .buildSDK()
    }
}