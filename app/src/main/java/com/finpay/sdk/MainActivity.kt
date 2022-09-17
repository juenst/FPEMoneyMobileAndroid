package com.finpay.sdk

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.testing.Signature
import com.finpay.sdk.constant.Constant
import lib.finpay.sdk.FinPaySDK
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var signature: Signature

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tokenID = FinPaySDK().getToken(
            userName = Constant().userName,
            password = Constant().password,
            secretKey = Constant().secretKey,
            phoneNumber = "083815613839",
            transNumber = "TRX1234567890"
        )

        print(tokenID)
    }
}