package com.finpay.sdk

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.finpay.sdk.constant.Constant
import lib.finpay.sdk.FinPaySDK
import com.finpay.wallet.view.AppActivity


class MainActivity : AppCompatActivity() {
    lateinit var finPaySDK: FinPaySDK

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textTokenId = findViewById(R.id.tokenId) as TextView
        var textSaldo = findViewById(R.id.saldo) as TextView
        var btnCallSDK = findViewById(R.id.btn_call_sdk) as TextView
        var btnCallWallet = findViewById(R.id.btn_call_wallet) as TextView

        finPaySDK = FinPaySDK()

        finPaySDK.init(
            Constant().userName,
            Constant().password,
            Constant().secretKey,
            "TRX1234567890"
        )


        btnCallSDK.setOnClickListener {
            FinPaySDK().getToken(
                onResult = {
                    token->
                    textTokenId.setText(token.getTokenID())
                }
            )
            FinPaySDK().getToken(
                onResult = {
                        token->
                    textTokenId.setText(token.getTokenID())
                }
            )
        }


        btnCallWallet.setOnClickListener {
            println("test")
            Intent(this, AppActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}