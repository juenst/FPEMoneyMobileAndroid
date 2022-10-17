package com.finpay.sdk

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.finpay.sdk.constant.Constant
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.model.Credential
import lib.finpay.sdk.uikit.FinpaySDKUI
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.view.payment.PaymentActivity
import lib.finpay.sdk.uikit.view.pin.PinActivity
import lib.finpay.sdk.uikit.view.ppob.pulsa_data.PulsaDataActivity
import lib.finpay.sdk.uikit.view.transaction.TransactionHistoryActivity


class MainActivity : AppCompatActivity() {
    lateinit var finPaySDK: FinpaySDK

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textTokenId = findViewById(R.id.tokenId) as TextView
        var textSaldo = findViewById(R.id.saldo) as TextView
        var btnCallSDK = findViewById(R.id.btn_call_sdk) as Button
        var btnCallWallet = findViewById(R.id.btn_call_wallet) as Button
        val btnOpenDialogQr = findViewById<Button>(R.id.btn_open_dialog_qr)
        val btnTransfer = findViewById<Button>(R.id.btn_transfer)
        val btnLogout = findViewById<Button>(R.id.btn_logout)
        finPaySDK = FinpaySDK()

        btnCallSDK.setOnClickListener {

        }


        btnCallWallet.setOnClickListener {
            FinpaySDKUI.openApplication(this@MainActivity, credential())
        }

        btnOpenDialogQr.setOnClickListener {
//            FinpaySDKUI.openQris(this@MainActivity, credential())
            val intent = Intent(this@MainActivity, PaymentActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            FinpaySDKUI.logout(this@MainActivity)
        }

        btnTransfer.setOnClickListener {
//            FinpaySDKUI.openTransfer(this@MainActivity, credential())
            val intent = Intent(this, PulsaDataActivity::class.java)
            this.startActivity(intent)
        }

    }

    fun credential(): Credential {
        val cd = Credential()
        cd.setUsername(Constant.userName)
        cd.setPassword(Constant.password)
        cd.setSecretKey(Constant.secretKey)
        cd.setPhoneNumber("083815613839")
        cd.setCustName("Widiyanto Ramadhan")
        return cd
    }

}