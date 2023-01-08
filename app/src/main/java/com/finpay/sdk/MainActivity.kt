package com.finpay.sdk

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
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
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.view.payment.PaymentActivity
import lib.finpay.sdk.uikit.view.ppob.bpjs.BpjsKesehatanActivity
import lib.finpay.sdk.uikit.view.ppob.pdam.PDAMActivity
import lib.finpay.sdk.uikit.view.ppob.pln.PLNActivity


class MainActivity : AppCompatActivity() {
    lateinit var finPaySDK: FinpaySDK
    lateinit var progressDialog: ProgressDialog

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textTokenId = findViewById(R.id.tokenId) as TextView
        var textSaldo = findViewById(R.id.saldo) as TextView
        var btnCorekit = findViewById(R.id.btnCorekit) as Button
        var btnPairing = findViewById(R.id.btnPairing) as Button
        val btnUpgradeAccount = findViewById<Button>(R.id.btnUpgradeAccount)
        val btnQris = findViewById<Button>(R.id.btnQris)
        val btnTransfer = findViewById<Button>(R.id.btnTransfer)
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        finPaySDK = FinpaySDK()
        progressDialog = ProgressDialog(this)

        btnCorekit.setOnClickListener {
            progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Mohon Menunggu")
            progressDialog.setMessage("Sedang Memuat ...")
            progressDialog.setCancelable(false)
            progressDialog.show()
            FinpaySDK.getToken(
                "1234567890",
                this,
                {
                    textTokenId.setText(it.tokenID)
                    textSaldo.setText(it.statusDesc)
                    progressDialog.dismiss()
                },{
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(this@MainActivity, "", it)
                }
            )
        }


        btnPairing.setOnClickListener {
//            FinpaySDKUI.upgradeAccountUIBuilder("", this, credential())
            println("klik ini")
            FinpaySDKUI.applicationUIBuilder(java.util.UUID.randomUUID().toString(), this@MainActivity, credential(),theme())
        }

        btnUpgradeAccount.setOnClickListener {
            FinpaySDKUI.upgradeAccountUIBuilder(java.util.UUID.randomUUID().toString(), this@MainActivity, credential(),theme())
        }

        btnLogout.setOnClickListener {
            FinpaySDKUI.logout(this@MainActivity, {})
        }

        btnQris.setOnClickListener {
            FinpaySDKUI.qrisUIBuilder(java.util.UUID.randomUUID().toString(), this@MainActivity, credential(),theme())
        }

        btnTransfer.setOnClickListener {
//            FinpaySDKUI.transferUIBuilder(java.util.UUID.randomUUID().toString(), this@MainActivity, credential(), theme())
            FinpaySDKUI.telkomUIBuilder(java.util.UUID.randomUUID().toString(), this@MainActivity, credential(), theme())
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun theme(): FinpayTheme {
        val theme = FinpayTheme()
        theme.setPrimaryColor(Color.parseColor("#333333"))
        theme.setSecondaryColor(Color.parseColor("#AAAAAA"))
        theme.setAppBarBackgroundColor(Color.parseColor("#FFFFFF"))
        theme.setAppBarTextColor(Color.parseColor("#333333"))
        return theme
    }

    fun credential(): Credential {
        val cd = Credential()
        cd.setUsername("MT77764DKM83N")
        cd.setPassword("YJV3AM0y")
        cd.setSecretKey("daYumnMb")
        cd.setPhoneNumber("083815613839")
        cd.setCustName("Widiyanto Ramadhan")
        return cd
    }

}