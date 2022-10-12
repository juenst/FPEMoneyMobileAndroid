package com.finpay.sdk

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.finpay.sdk.constant.Constant
import lib.finpay.sdk.FinPaySDK
import com.finpay.wallet.view.AppActivity
import com.finpay.wallet.view.splash.SplashActivity


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
        val btnOpenDialogQr = findViewById<Button>(R.id.btn_open_dialog_qr)
        finPaySDK = FinPaySDK()


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
            Intent(this, AppActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }

        btnOpenDialogQr.setOnClickListener {

        }
    }
}