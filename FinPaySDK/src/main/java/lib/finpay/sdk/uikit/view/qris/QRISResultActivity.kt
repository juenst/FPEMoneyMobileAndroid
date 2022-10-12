package lib.finpay.sdk.uikit.view.qris

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import lib.finpay.sdk.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.TextUtils
import lib.finpay.sdk.uikit.view.pin.PinActivity


class QRISResultActivity : AppCompatActivity() {
    val stringQris: String? by lazy {
        intent.getStringExtra("resultQR")
    }

    lateinit var merchantName: TextView
    lateinit var txtTotalBayar: TextView
    lateinit var txtSaldo: TextView
    lateinit var txtAmount: EditText
    lateinit var btnBayar: Button
    lateinit var btnBack: ImageView
    lateinit var progressDialog: ProgressDialog
    lateinit var _tagihan: String
    lateinit var _biayaLayanan: String
    lateinit var _totalBayar: String
    lateinit var _saldo: String
    lateinit var _reffFlag: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qris_result)
        supportActionBar!!.hide()

        merchantName = findViewById(R.id.merchantName)
        txtTotalBayar = findViewById(R.id.txtTotalBayar)
        txtSaldo = findViewById(R.id.txtSaldo)
        txtAmount = findViewById(R.id.txtAmount)
        btnBayar = findViewById(R.id.btnBayar)
        btnBack = findViewById(R.id.btnBack)

        progressDialog = ProgressDialog(this@QRISResultActivity)
        _tagihan = "0"
        _biayaLayanan = "0"
        _totalBayar = "0"
        _saldo = "0"
        _reffFlag = ""

        progressDialog.setTitle("Mohon Menunggu")
        progressDialog.setMessage("Sedang Memuat ...")
        progressDialog.setCancelable(false) // blocks UI interaction
        progressDialog.show()
        FinpaySDK.qrisInquiry(
            this@QRISResultActivity,
            stringQris!!, {
                merchantName.text = it.bit61Parse!!.merchantName
                txtTotalBayar.text = TextUtils.formatRupiah(it.tagihan!!.toDouble())
                _tagihan = TextUtils.formatRupiah(it.tagihan!!.toDouble())
                _biayaLayanan = TextUtils.formatRupiah(it.fee[0].fee!!.toDouble())
                _totalBayar = TextUtils.formatRupiah(it.fee[0].total!!.toDouble())
                _reffFlag = it.conf.toString()
                progressDialog.dismiss()
            }, {
                progressDialog.dismiss()
                Toast.makeText(this@QRISResultActivity, it, Toast.LENGTH_LONG)
            }
        )

        FinpaySDK.getUserBallance(this@QRISResultActivity, {
            txtSaldo.text = TextUtils.formatRupiah(it.amount!!.toDouble())
            _saldo = TextUtils.formatRupiah(it.amount!!.toDouble())
        },{
            Toast.makeText(this@QRISResultActivity, it, Toast.LENGTH_LONG)
        })

        txtAmount.doOnTextChanged { text, start, before, count ->
            btnBayar.isEnabled = (!text.isNullOrBlank() && text.length>=1 && text != "0")
            ButtonUtils.checkButtonState(btnBayar)
        }

        ButtonUtils.checkButtonState(btnBayar)
        btnBayar.setOnClickListener {
            showDialogConfirmPayment(
                _tagihan,
                _biayaLayanan,
                _totalBayar,
                _saldo
            )
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    fun showDialogConfirmPayment(
        tagihan: String,
        biayaLayanan: String,
        totalBayar: String,
        saldo: String
    ) {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.dialog_confirm_payment)
        val btnPay = dialog.findViewById<Button>(R.id.btnPay)
        val txtTagihan = dialog.findViewById<TextView>(R.id.tagihan)
        val txtBiayaLayanan= dialog.findViewById<TextView>(R.id.biayaLayanan)
        val txtTotalBayar= dialog.findViewById<TextView>(R.id.totalBayar)
        val txtSaldo= dialog.findViewById<TextView>(R.id.saldo)

        txtTagihan!!.text = tagihan
        txtBiayaLayanan!!.text = biayaLayanan
        txtTotalBayar!!.text = totalBayar
        txtSaldo!!.text = saldo

        btnPay?.setOnClickListener {
            val intent = Intent(this@QRISResultActivity, PinActivity::class.java)
            intent.putExtra("pinType", "paymentQris")
            intent.putExtra("sof", "mc")
            intent.putExtra("amount", "0")
            intent.putExtra("amountTips", "0")
            intent.putExtra("reffFlag", _reffFlag)
            startActivity(intent)
        }
        dialog.show()
    }
}