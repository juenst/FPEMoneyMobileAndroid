package com.finpay.sdk.example

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.finpay.sdk.R
import lib.finpay.sdk.corekit.model.Credential
import lib.finpay.sdk.uikit.FinpaySDKUI
import lib.finpay.sdk.uikit.helper.FinpayTheme


class HomeActivity : AppCompatActivity() {
    private lateinit var btnLogout: ImageView
    private lateinit var btnTopUp: LinearLayout
    private lateinit var btnTransfer: LinearLayout
    private lateinit var btnHistoryTransaction: LinearLayout
    private lateinit var btnWallet: LinearLayout
    private lateinit var btnQris: LinearLayout

    private lateinit var sectionUpgradeAccount: LinearLayout

    private lateinit var btnPulsaData: LinearLayout
    private lateinit var btnPascaBayar: LinearLayout
    private lateinit var btnAlfamart: LinearLayout
    private lateinit var btnBpjs: LinearLayout

    private lateinit var btnPLN: LinearLayout
    private lateinit var btnTelkom: LinearLayout
    private lateinit var btnIndihome: LinearLayout
    private lateinit var btnVoucherDeals: LinearLayout

    private lateinit var btnVoucherTvKabel: LinearLayout
    private lateinit var btnPdam: LinearLayout
    private lateinit var btnInternetTvKabel: LinearLayout
    private lateinit var btnCicilan: LinearLayout

    private lateinit var btnFinpay: LinearLayout
    private lateinit var btnInsurance: LinearLayout
    private lateinit var btnTelkomsel: LinearLayout
    private lateinit var btnPegadaian: LinearLayout
    private lateinit var btnPajak: LinearLayout

    val userName: String? by lazy { if(intent.getStringExtra("userName") == null) "" else intent.getStringExtra("userName")}
    val password: String? by lazy { if(intent.getStringExtra("password") == null) "" else intent.getStringExtra("password")}
    val secretKey: String? by lazy { if(intent.getStringExtra("secretKey") == null) "" else intent.getStringExtra("secretKey")}
    val phoneNo: String? by lazy { if(intent.getStringExtra("phoneNo") == null) "" else intent.getStringExtra("phoneNo")}
    val name: String? by lazy { if(intent.getStringExtra("name") == null) "" else intent.getStringExtra("name")}


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homes)
        supportActionBar!!.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(Color.parseColor("#e60000"))
        }

        btnLogout = findViewById(R.id.btnLogout)
        btnTopUp = findViewById(R.id.btnTopup)
        btnTransfer = findViewById(R.id.btnTransfer)
        btnHistoryTransaction = findViewById(R.id.btnHistoryTransaction)
        btnWallet = findViewById(R.id.btnWallet)
        btnQris = findViewById(R.id.btnQris)

        sectionUpgradeAccount = findViewById(R.id.sectionUpgradeAccount)

        btnPulsaData = findViewById(R.id.btnPulsaData)
        btnPascaBayar = findViewById(R.id.btnPascaBayar)
        btnAlfamart = findViewById(R.id.btnAlfamart)
        btnBpjs = findViewById(R.id.btnBpjs)
        
        btnPLN = findViewById(R.id.btnPLN)
        btnTelkom = findViewById(R.id.btnTelkom)
        btnIndihome = findViewById(R.id.btnIndihome)
        btnVoucherDeals = findViewById(R.id.btnVoucherDeals)

        btnVoucherTvKabel = findViewById(R.id.btnVoucherTvKabel)
        btnPdam = findViewById(R.id.btnPdam)
        btnInternetTvKabel = findViewById(R.id.btnInternetTvKabel)
        btnCicilan = findViewById(R.id.btnCicilan)

        btnFinpay = findViewById(R.id.btnFinpay)
        btnInsurance = findViewById(R.id.btnInsurance)
        btnTelkomsel = findViewById(R.id.btnTelkomsel)
        btnPegadaian = findViewById(R.id.btnPegadaian)
        btnPajak = findViewById(R.id.btnPajak)

        btnLogout.setOnClickListener {

        }

        btnTopUp.setOnClickListener {
            FinpaySDKUI.topupUIBuilder("", this, credential(), theme())
        }
        btnTransfer.setOnClickListener {
            FinpaySDKUI.transferUIBuilder("", this, credential(), theme())
        }
        btnHistoryTransaction.setOnClickListener {
            FinpaySDKUI.historyTransactionUIBuilder("", this, credential(), theme())
        }
        btnWallet.setOnClickListener {
            FinpaySDKUI.walletUIBuilder("", this, credential(), theme())
        }
        btnQris.setOnClickListener {
            FinpaySDKUI.qrisUIBuilder("", this, credential(), theme())
        }
        sectionUpgradeAccount.setOnClickListener {
            FinpaySDKUI.upgradeAccountUIBuilder("", this, credential(), theme())
        }
        btnPulsaData.setOnClickListener {
            FinpaySDKUI.pulsaDataUIBuilder("", this, credential(), theme())
        }
        btnPascaBayar.setOnClickListener {
            FinpaySDKUI.pascaBayarUIBuilder("", this, credential(), theme())
        }
        btnAlfamart.setOnClickListener {
            FinpaySDKUI.alfamartUIBuilder("", this, credential(), theme())
        }
        btnBpjs.setOnClickListener {
            FinpaySDKUI.bpjsUIBuilder("", this, credential(), theme())
        }
        btnPLN.setOnClickListener {
            FinpaySDKUI.plnUIBuilder("", this, credential(), theme())
        }
        btnTelkom.setOnClickListener {
            FinpaySDKUI.telkomUIBuilder("", this, credential(), theme())
        }
        btnIndihome.setOnClickListener {
            FinpaySDKUI.indihomeUIBuilder("", this, credential(), theme())
        }
        btnVoucherDeals.setOnClickListener {
            FinpaySDKUI.voucherDealsUIBuilder("", this, credential(), theme())
        }
        btnVoucherTvKabel.setOnClickListener {
            FinpaySDKUI.voucherTvCableUIBuilder("", this, credential(), theme())
        }
        btnPdam.setOnClickListener {
            FinpaySDKUI.pdamUIBuilder("", this, credential(), theme())
        }
        btnInternetTvKabel.setOnClickListener {
            FinpaySDKUI.internetTvCableUIBuilder("", this, credential(), theme())
        }
        btnCicilan.setOnClickListener {
            FinpaySDKUI.instalmentUIBuilder("", this, credential(), theme())
        }
        btnFinpay.setOnClickListener {
            FinpaySDKUI.finpayUIBuilder("", this, credential(), theme())
        }
        btnInsurance.setOnClickListener {
            FinpaySDKUI.insuranceUIBuilder("", this, credential(), theme())
        }
        btnTelkomsel.setOnClickListener {
            FinpaySDKUI.telkomselUIBuilder("", this, credential(), theme())
        }
        btnPegadaian.setOnClickListener {
            FinpaySDKUI.pegadaianUIBuilder("", this, credential(), theme())
        }
        btnPajak.setOnClickListener {
            FinpaySDKUI.revenueUIBuilder("", this, credential(), theme())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun theme(): FinpayTheme {
        val theme = FinpayTheme()
        theme.setPrimaryColor(Color.parseColor("#e60000"))
        theme.setSecondaryColor(Color.parseColor("#e6dddd"))
        theme.setAppBarBackgroundColor(Color.parseColor("#e60000"))
        theme.setAppBarTextColor(Color.parseColor("#FFFFFF"))
        return theme
    }

    fun credential(): Credential {
        val cd = Credential()
        cd.setUsername(userName)
        cd.setPassword(password)
        cd.setSecretKey(secretKey)
        cd.setPhoneNumber(phoneNo)
        cd.setCustName(name)
        return cd
    }
}
