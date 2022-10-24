package lib.finpay.sdk.uikit.view.transaction

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.utilities.TextUtils

class TransactionDetailQrisActivity : AppCompatActivity()  {
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

    val merchantName: String? by lazy { intent.getStringExtra("merchantName") }
    val merchantId: String? by lazy { intent.getStringExtra("merchantId") }
    val nevaNumber: String? by lazy { intent.getStringExtra("nevaNumber") }
    val amount: String? by lazy { intent.getStringExtra("amount") }
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
//    val result = intent.getSerializableExtra("result") as? QrisPayment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_detail_qris)
        supportActionBar!!.hide()

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

        status.text = if(statusDesc!!.uppercase() == "BERHASIL") "Transaksi Berhasil" else "Transaksi Gagal"

//        txtTotalBayar.text = TextUtils.formatRupiah((if(result?.bit61Parse?.amount == null || result.bit61Parse?.amount == "") "0" else result.bit61Parse?.amount)!!.toDouble())
//        txtTanggal.text = ": -"
//        txtNoTrans.text = ": "+result?.bit61Parse?.invoice
//        txtMerchantName.text = ": "+result?.bit61Parse?.merchantName
//        txtLocationMerchant.text = ": "+result?.bit61Parse?.merchantLocation
//        txtTerminalID.text = ": "+result?.bit61Parse?.terminalID
//        txtReffID.text = ": "+reffID
//        txtCustomerPAN.text = ": "+result?.bit61Parse?.customerPAN
//        txtMerchantPAN.text = ": "+result?.bit61Parse?.merchantPAN

        txtTotalBayar.text = TextUtils.formatRupiah((if(amount == null || amount == "") "0" else amount)!!.toDouble())
        txtPrice.text = TextUtils.formatRupiah((if(amount == null || amount == "") "0" else amount)!!.toDouble())
        txtTanggal.text = ": -"
        txtNoTrans.text = ": "+invoice
        txtMerchantName.text = ": "+merchantName
        txtLocationMerchant.text = ": "+merchantLocation
        txtTerminalID.text = ": "+terminalID
        txtReffID.text = ": "+reffID
        txtCustomerPAN.text = ": "+customerPAN
        txtMerchantPAN.text = ": "+merchantPAN
        txtBiayaLayanan.text = "Rp0"

        btnBack.setOnClickListener{
            finish()
        }
    }

}