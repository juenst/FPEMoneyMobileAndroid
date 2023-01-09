package lib.finpay.sdk.uikit.view.transaction

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.TextUtils

class TransactionDetailQrisActivity : AppCompatActivity()  {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    lateinit var btnBack: ImageView
    lateinit var txtTanggal: TextView
    lateinit var txtTotalBayar: TextView
    lateinit var txtNoTrans: TextView
    lateinit var txtMerchantName: TextView
    lateinit var txtLocationMerchant: TextView
    lateinit var txtTerminalID: TextView
    lateinit var txtReffID: TextView
    lateinit var txtCustomerPAN: TextView
    lateinit var txtMerchantPAN: TextView
    lateinit var txtPrice: TextView
    lateinit var txtBiayaLayanan: TextView
    lateinit var status: TextView
    lateinit var bg: LinearLayout

    val merchantName: String? by lazy { intent.getStringExtra("merchantName") }
    val merchantId: String? by lazy { intent.getStringExtra("merchantId") }
    val nevaNumber: String? by lazy { intent.getStringExtra("nevaNumber") }
    val amount: String? by lazy { intent.getStringExtra("amount") }
    val price: String? by lazy { intent.getStringExtra("price") }
    val fee: String? by lazy { intent.getStringExtra("fee") }
    val paymentCode: String? by lazy { intent.getStringExtra("paymentCode") }
    val pointOfMethod: String? by lazy { intent.getStringExtra("pointOfMethod") }
    val tipsType: String? by lazy { intent.getStringExtra("tipsType") }
    val tipsAmount: String? by lazy { intent.getStringExtra("tipsAmount") }
    val tipsPercentage: String? by lazy { intent.getStringExtra("tipsPercentage") }
    val acquirerName: String? by lazy { intent.getStringExtra("acquirerName") }
    val merchantLocation: String? by lazy { intent.getStringExtra("merchantLocation") }
    val merchantPAN: String? by lazy { intent.getStringExtra("merchantName") }
    val terminalID: String? by lazy { intent.getStringExtra("terminalID") }
    val isOnUs: String? by lazy { intent.getStringExtra("isOnUs") }
    val customerPAN: String? by lazy { intent.getStringExtra("customerPAN") }
    val invoice: String? by lazy { intent.getStringExtra("invoice") }
    val reffID: String? by lazy { intent.getStringExtra("reffID") }
    val statusDesc: String? by lazy { intent.getStringExtra("statusDesc") }
    val transactionDate: String? by lazy { intent.getStringExtra("transactionDate") }
    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
//    val result = intent.getSerializableExtra("result") as? QrisPayment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_detail_qris)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        bg = findViewById(R.id.bg)
        btnBack = findViewById(R.id.btnBack)
        txtTanggal = findViewById(R.id.txtTanggal)
        txtTotalBayar = findViewById(R.id.txtTotalBayar)
        txtNoTrans = findViewById(R.id.txtNoTrans)
        txtMerchantName = findViewById(R.id.txtMerchantName)
        txtLocationMerchant = findViewById(R.id.txtLocationMerchant)
        txtTerminalID = findViewById(R.id.txtTerminalID)
        txtReffID = findViewById(R.id.txtReffID)
        txtCustomerPAN = findViewById(R.id.txtCustomerPAN)
        txtMerchantPAN = findViewById(R.id.txtMerchantPAN)
        txtPrice = findViewById(R.id.txtPrice)
        txtBiayaLayanan = findViewById(R.id.txtBiayaLayanan)
        status = findViewById(R.id.status)

        status.text = if(statusDesc!!.uppercase() == "BERHASIL") "Transaksi Berhasil" else if(statusDesc!!.uppercase() == "GAGAL") "Transaksi Gagal" else "Transaksi Pending"

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        bg.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if(finpayTheme?.getAppBarBackgroundColor() == null) {
                window.setStatusBarColor(Color.parseColor("#333333"))
            } else {
                window.setStatusBarColor(finpayTheme?.getAppBarBackgroundColor()!!)
            }
        }

        txtTotalBayar.text = TextUtils.formatRupiah((if(amount == null || amount == "") "0" else amount)!!.toDouble())
        txtPrice.text = TextUtils.formatRupiah((if(price == null || price == "") "0" else price)!!.toDouble())
        txtTanggal.text = ": "+transactionDate
        txtNoTrans.text = ": "+invoice
        txtMerchantName.text = ": "+merchantName
        txtLocationMerchant.text = ": "+merchantLocation
        txtTerminalID.text = ": "+terminalID
        txtReffID.text = ": "+reffID
        txtCustomerPAN.text = ": "+customerPAN
        txtMerchantPAN.text = ": "+merchantPAN
        txtBiayaLayanan.text = TextUtils.formatRupiah((if(fee == null || fee == "") "0" else fee)!!.toDouble())

        btnBack.setOnClickListener{
            finish()
        }
    }

}