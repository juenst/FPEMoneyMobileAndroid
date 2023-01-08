package lib.finpay.sdk.uikit.view.qris

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
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


class QRISResultActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    val stringQris: String? by lazy {
        intent.getStringExtra("resultQR")
    }

    lateinit var merchantName: TextView
    lateinit var txtTotalBayar: TextView
    lateinit var txtSaldo: TextView
    lateinit var txtAmount: CurrencyEditText
    lateinit var btnBayar: Button
    lateinit var btnBack: ImageView
    lateinit var progressDialog: ProgressDialog
    lateinit var _tagihan: String
    lateinit var _biayaLayanan: String
    lateinit var _totalBayar: String
    lateinit var _saldo: String
    lateinit var _reffFlag: String

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qris_result)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        merchantName = findViewById(R.id.merchantName)
        txtTotalBayar = findViewById(R.id.txtTotalBayar)
        txtSaldo = findViewById(R.id.txtSaldo)
        txtAmount = findViewById(R.id.txtAmount)
        btnBayar = findViewById(R.id.btnBayar)
        btnBack = findViewById(R.id.btnBack)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBayar.setBackgroundColor(if(btnBayar.isEnabled()) if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!! else Color.parseColor("#d5d5d5"))

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
            java.util.UUID.randomUUID().toString(),
            this@QRISResultActivity,
            stringQris!!, {
                var fee: String = "0"
                for(data in it.fee) {
                    if(data.sof == "mc") {
                        fee = data.fee.toString()
                    }
                }
                merchantName.text = it.bit61Parse!!.merchantName
                txtTotalBayar.text = TextUtils.formatRupiah(it.tagihan!!.toDouble())
                _tagihan = TextUtils.formatRupiah(it.tagihan!!.toDouble())
                _biayaLayanan = TextUtils.formatRupiah(fee.toDouble())
                _totalBayar = TextUtils.formatRupiah((fee.toInt() + it.tagihan!!.toInt()).toDouble())
                _reffFlag = it.conf.toString()
                progressDialog.dismiss()
            }, {
                progressDialog.dismiss()
                DialogUtils.showDialogError(this@QRISResultActivity, "", it)
            }
        )

        FinpaySDK.getUserBallance(java.util.UUID.randomUUID().toString(), this@QRISResultActivity, {
            txtSaldo.text = TextUtils.formatRupiah(it.amount!!.toDouble())
            _saldo = TextUtils.formatRupiah(it.amount!!.toDouble())
        },{
            DialogUtils.showDialogError(this@QRISResultActivity, "", it)
        })

        txtAmount.doOnTextChanged { text, start, before, count ->
            txtTotalBayar.text = txtAmount.text
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
                java.util.UUID.randomUUID().toString(),
                this@QRISResultActivity,
                txtAmount.text.toString(), ProductCode.QRIS,{
                    progressDialog.dismiss()
                    val intent = Intent(this@QRISResultActivity, PaymentActivity::class.java)
                    intent.putExtra("paymentType", PaymentType.paymentQRIS)
                    intent.putExtra("sof", "mc")
                    intent.putExtra("amount", totalBayar.replace("Rp", "").replace(",",""))
                    intent.putExtra("amountTips", "0")
                    intent.putExtra("reffFlag", _reffFlag)
                    intent.putExtra("widgetURL", it.widgetURL)
                    intent.putExtra("transNumber", transNumber!!)
                    intent.putExtra("theme", finpayTheme)
                    startActivity(intent)
                }, {
                    progressDialog.dismiss()
                    println(it)
                    DialogUtils.showDialogError(this@QRISResultActivity, "", it)
                }
            )
        }
        dialog.show()
    }
}