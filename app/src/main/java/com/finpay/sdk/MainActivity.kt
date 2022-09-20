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
import kotlin.system.measureTimeMillis
import com.finpay.wallet.MainActivity as WalletActivity

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textTokenId = findViewById(R.id.tokenId) as TextView


        FinPaySDK().getToken(
            Constant().userName,
            Constant().password,
            Constant().secretKey,
            "TRX1234567890",
            onSuccess = {
                tokens->
                FinPaySDK().getBalance(
                    Constant().userName,
                    Constant().password,
                    Constant().secretKey,
                    "TRX1234567890",
                    "083815613839",
                    tokens.getTokenID()!!,
                    onSuccess = {
                        userBallanceModel ->
                        textTokenId.setText(userBallanceModel.getCustBalance())
                    }
                )
            }
        )



        textTokenId.setOnClickListener {
            println("test")
            Intent(this, AppActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}