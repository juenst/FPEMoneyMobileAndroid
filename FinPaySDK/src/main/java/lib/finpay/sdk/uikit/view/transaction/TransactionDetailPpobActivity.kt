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
import lib.finpay.sdk.corekit.helper.TransactionHelper
import lib.finpay.sdk.corekit.model.PpobPayment
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.TextUtils

class TransactionDetailPpobActivity : AppCompatActivity()  {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    lateinit var btnBack: ImageView
    lateinit var txtTanggal: TextView
    lateinit var txtTotalBayar: TextView
    lateinit var txtNoTrans: TextView
    lateinit var txtSerialNumber: TextView
    lateinit var txtTransactionType: TextView
    lateinit var txtNomorTujuan: TextView
    lateinit var txtPrice: TextView
    lateinit var txtBiayaLayanan: TextView
    lateinit var status: TextView
    lateinit var bg: LinearLayout

////    val amountDpp: String? by lazy { intent.getStringExtra("amountDpp") }
////    val amountPpn: String? by lazy { intent.getStringExtra("amountPpn") }
////    val ppn: String? by lazy { intent.getStringExtra("ppn") }
////    val nomorReferensi: String? by lazy { intent.getStringExtra("nomorReferensi") }
////    val nilaiTagihan: String? by lazy { intent.getStringExtra("nilaiTagihan") }
////    val customerId: String? by lazy { intent.getStringExtra("customerId") }
////    val customerName: String? by lazy { intent.getStringExtra("customerName") }
////    val npwp: String? by lazy { intent.getStringExtra("npwp") }
////    val kodeDivre: String? by lazy { intent.getStringExtra("kodeDivre") }
////    val kodeDatel: String? by lazy { intent.getStringExtra("kodeDatel") }
////    val jumlahBill: String? by lazy { intent.getStringExtra("jumlahBill") }
////    val statusDesc: String? by lazy { intent.getStringExtra("statusDesc") }
    val transNumber: String? by lazy { intent.getStringExtra("transNumber") }
    val transactionDate: String? by lazy { intent.getStringExtra("transactionDate") }
    val transactionType: String? by lazy { intent.getStringExtra("transactionType") }
    val amount: String? by lazy { intent.getStringExtra("amount") }
    val price: String? by lazy { intent.getStringExtra("price") }
    val fee: String? by lazy { intent.getStringExtra("fee") }
    val result: PpobPayment? by lazy { if(intent.getSerializableExtra("result") == null) null else intent.getSerializableExtra("result") as PpobPayment }
    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_detail_ppob)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        bg = findViewById(R.id.bg)
        btnBack = findViewById(R.id.btnBack)
        txtTanggal = findViewById(R.id.txtTanggal)
        txtTotalBayar = findViewById(R.id.txtTotalBayar)
        txtTransactionType = findViewById(R.id.transactionType)
        txtNoTrans = findViewById(R.id.txtNoTrans)
        txtSerialNumber = findViewById(R.id.txtSerialNumber)
        txtNomorTujuan = findViewById(R.id.txtNomorTujuan)
        txtPrice = findViewById(R.id.txtPrice)
        txtBiayaLayanan = findViewById(R.id.txtBiayaLayanan)
        status = findViewById(R.id.status)

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

        status.text = if(result?.statusDesc!!.uppercase() == "BERHASIL") "Transaksi Berhasil" else "Transaksi Gagal"
        txtTotalBayar.text = TextUtils.formatRupiah((if(amount == null || amount == "") "0" else amount)!!.toDouble())
        txtPrice.text = TextUtils.formatRupiah((if(price == null || price == "") "0" else price)!!.toDouble())
        txtTanggal.text = ": "+transactionDate
        txtNoTrans.text = ": "+ TransactionHelper.getTransNumber(transNumber!!)
        txtSerialNumber.text = ": -"
        txtTransactionType.text = ": "+transactionType
        txtNomorTujuan.text = ": "+result?.bit61Parse?.customerId
        txtBiayaLayanan.text = TextUtils.formatRupiah((if(fee == null || fee == "") "0" else fee)!!.toDouble())

        btnBack.setOnClickListener{
            finish()
        }
    }

}