package com.finpay.sdk

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.uikit.FinpaySDKUI


class MainActivity : AppCompatActivity() {
    lateinit var finPaySDK: FinpaySDK

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textTokenId = findViewById(R.id.tokenId) as TextView
        var textSaldo = findViewById(R.id.saldo) as TextView
        var btnCallSDK = findViewById(R.id.btn_call_sdk) as TextView
        var btnCallWallet = findViewById(R.id.btn_call_wallet) as TextView
        val btnOpenDialogQr = findViewById<Button>(R.id.btn_open_dialog_qr)
        finPaySDK = FinpaySDK()
        btnCallSDK.setOnClickListener {
            FinpaySDK().getToken(
                onResult = {
                    token->
                    textTokenId.setText(token.getTokenID())
                }
            )
        }


        btnCallWallet.setOnClickListener {
            FinpaySDKUI.openWallet(this@MainActivity)
        }

        btnOpenDialogQr.setOnClickListener {
            FinpaySDKUI.openQris(this@MainActivity)
        }
    }
}