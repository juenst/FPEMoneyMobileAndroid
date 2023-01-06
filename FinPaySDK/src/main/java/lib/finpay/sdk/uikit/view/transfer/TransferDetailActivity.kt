package lib.finpay.sdk.uikit.view.transfer

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
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.uikit.constant.PaymentType
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.TextUtils
import lib.finpay.sdk.uikit.utilities.widget.CurrencyEditText
import lib.finpay.sdk.uikit.view.payment.PaymentActivity


class TransferDetailActivity : AppCompatActivity() {
    lateinit var billName: TextView
    lateinit var txtAmount: EditText
    lateinit var txtNote: EditText
    lateinit var btnBayar: Button
    lateinit var btnBack: ImageView
    lateinit var progressDialog: ProgressDialog

    lateinit var _tagihan: String
    lateinit var _biayaLayanan: String
    lateinit var _totalBayar: String
    lateinit var _saldo: String
    lateinit var _reffFlag: String

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { intent.getStringExtra("transNumber")}
    val name: String? by lazy { intent.getStringExtra("billName")}
    val type: String? by lazy { intent.getStringExtra("type")}
    val phoneDest: String? by lazy { intent.getStringExtra("phoneDest")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_detail)
        supportActionBar!!.hide()

        billName = findViewById(R.id.billName)
        txtAmount = findViewById(R.id.txtAmount)
        txtNote = findViewById(R.id.txtNote)
        btnBayar = findViewById(R.id.btnBayar)
        btnBack = findViewById(R.id.btnBack)

        progressDialog = ProgressDialog(this@TransferDetailActivity)
        _tagihan = "0"
        _biayaLayanan = "0"
        _totalBayar = "0"
        _saldo = "0"
        _reffFlag = ""

        billName.setText(name)

        FinpaySDK.getUserBallance(transNumber!!, this@TransferDetailActivity, {
            _saldo = TextUtils.formatRupiah(it.amount!!.toDouble())
        },{
            DialogUtils.showDialogError(this@TransferDetailActivity, "", it)
        })

        txtAmount.doOnTextChanged { text, start, before, count ->
            _totalBayar = txtAmount.text.toString()
            _tagihan = txtAmount.text.toString()
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
            progressDialog.setTitle("Mohon Menunggu")
            progressDialog.setMessage("Sedang Memuat ...")
            progressDialog.setCancelable(false) // blocks UI interaction
            progressDialog.show()
            FinpaySDK.authPin(
                transNumber!!,
                this@TransferDetailActivity,
                txtAmount.text.toString(), ProductCode.TRANSFER_OTHER,{
                    progressDialog.dismiss()
                    val intent = Intent(this@TransferDetailActivity, PaymentActivity::class.java)
                    intent.putExtra("transNumber", transNumber)
                    intent.putExtra("paymentType", PaymentType.transferToOther)
                    intent.putExtra("amount", totalBayar.replace("Rp", "").replace(",",""))
                    intent.putExtra("desc", txtNote.text.toString())
                    intent.putExtra("desc", txtNote.text.toString())
                    intent.putExtra("phoneNumberDest", phoneDest)
                    intent.putExtra("reffTrx", "")
                    intent.putExtra("category", "")
                    intent.putExtra("reffFlag", "")
                    intent.putExtra("widgetURL", it.widgetURL)
                    startActivity(intent)
                }, {
                    progressDialog.dismiss()
                    println(it)
                    DialogUtils.showDialogError(this@TransferDetailActivity, "", it)
                }
            )
        }
        dialog.show()
    }
}