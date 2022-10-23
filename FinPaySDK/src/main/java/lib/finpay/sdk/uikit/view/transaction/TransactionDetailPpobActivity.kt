package lib.finpay.sdk.uikit.view.transaction

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.utilities.TextUtils

class TransactionDetailPpobActivity : AppCompatActivity()  {
    lateinit var btnBack: ImageView
    lateinit var txtTanggal: TextView
    lateinit var txtTotalBayar: TextView
    lateinit var txtNoTrans: TextView
    lateinit var txtSerialNumber: TextView
    lateinit var txtNomorTujuan: TextView
    lateinit var txtPrice: TextView
    lateinit var txtBiayaLayanan: TextView
    lateinit var status: TextView

    val amountDpp: String? by lazy { intent.getStringExtra("amountDpp") }
    val amountPpn: String? by lazy { intent.getStringExtra("amountPpn") }
    val ppn: String? by lazy { intent.getStringExtra("ppn") }
    val nomorReferensi: String? by lazy { intent.getStringExtra("nomorReferensi") }
    val nilaiTagihan: String? by lazy { intent.getStringExtra("nilaiTagihan") }
    val customerId: String? by lazy { intent.getStringExtra("customerId") }
    val customerName: String? by lazy { intent.getStringExtra("customerName") }
    val npwp: String? by lazy { intent.getStringExtra("npwp") }
    val kodeDivre: String? by lazy { intent.getStringExtra("kodeDivre") }
    val kodeDatel: String? by lazy { intent.getStringExtra("kodeDatel") }
    val jumlahBill: String? by lazy { intent.getStringExtra("jumlahBill") }
    val statusDesc: String? by lazy { intent.getStringExtra("statusDesc") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_detail_qris)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        txtTanggal = findViewById(R.id.txtTanggal)
        txtTotalBayar = findViewById(R.id.txtTotalBayar)
        txtNoTrans = findViewById(R.id.txtNoTrans)
        txtSerialNumber = findViewById(R.id.txtSerialNumber)
        txtNomorTujuan = findViewById(R.id.txtNomorTujuan)
        txtPrice = findViewById(R.id.txtPrice)
        txtBiayaLayanan = findViewById(R.id.txtBiayaLayanan)
        status = findViewById(R.id.status)

        status.text = if(statusDesc!!.uppercase() == "BERHASIL") "Transaksi Berhasil" else "Transaksi Gagal"
        txtTotalBayar.text = TextUtils.formatRupiah((if(nilaiTagihan == null || nilaiTagihan == "") "0" else nilaiTagihan)!!.toDouble())
        txtPrice.text = TextUtils.formatRupiah((if(nilaiTagihan == null || nilaiTagihan == "") "0" else nilaiTagihan)!!.toDouble())
        txtTanggal.text = ": -"
        txtNoTrans.text = ": -"
        txtSerialNumber.text = ": "+customerId
        txtNomorTujuan.text = ": -"
        txtBiayaLayanan.text = "Rp0"

        btnBack.setOnClickListener{
            finish()
        }
    }

}