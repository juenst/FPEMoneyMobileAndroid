package com.finpay.sdk

import android.os.Build
import android.os.Bundle
import android.widget.TextView
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
        var textTokenId = findViewById(R.id.tokenId) as TextView

//        lateinit var sdk: FinPaySDK

        val tokenID = FinPaySDK().getToken(
            Constant().userName,
            Constant().password,
            Constant().secretKey,
            "TRX1234567890"
        )

        println("Token ID yang di print di MainActivity => "+tokenID)
        textTokenId.text = tokenID
    }
}