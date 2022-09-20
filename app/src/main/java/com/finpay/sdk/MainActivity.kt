package com.finpay.sdk

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.testing.Signature
import com.finpay.sdk.constant.Constant
import com.finpay.wallet.view.app.AppActivity
import lib.finpay.sdk.FinPaySDK
import java.util.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textTokenId = findViewById(R.id.tokenId) as TextView

        val tokenID = FinPaySDK().getToken(
            Constant().userName,
            Constant().password,
            Constant().secretKey,
            "TRX1234567890"
        )

        if (tokenID != null) {
            println(tokenID)
        } else {
            println("kosong")
//        println("Token ID yang di print di MainActivity => "+tokenID
        }
//        textTokenId.text = tokenID

        textTokenId.setOnClickListener {
            println("test")
            Intent(this, AppActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}