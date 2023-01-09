package lib.finpay.sdk.uikit.view.qris

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
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
    val stringQris: String? by lazy { intent.getStringExtra("resultQR") }
    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    lateinit var merchantName: TextView
    lateinit var txtTotalBayar: TextView
    lateinit var txtSaldo: TextView
    lateinit var txtAmount: CurrencyEditText
    lateinit var btnBayar: Button
    lateinit var btnBack: ImageView
    lateinit var imgMerchant: ImageView

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

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        merchantName = findViewById(R.id.merchantName)
        txtTotalBayar = findViewById(R.id.txtTotalBayar)
        txtSaldo = findViewById(R.id.txtSaldo)
        txtAmount = findViewById(R.id.txtAmount)
        btnBayar = findViewById(R.id.btnBayar)
        btnBack = findViewById(R.id.btnBack)
        imgMerchant = findViewById(R.id.imgMerchant)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        imgMerchant.setColorFilter(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)
        btnBayar.setBackgroundColor(if(btnBayar.isEnabled()) if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!! else Color.parseColor("#d5d5d5"))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if(finpayTheme?.getAppBarBackgroundColor() == null) {
                window.setStatusBarColor(Color.parseColor("#333333"))
            } else {
                window.setStatusBarColor(finpayTheme?.getAppBarBackgroundColor()!!)
            }
        }

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
            transNumber!!,
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
                DialogUtils.showDialogError(this@QRISResultActivity, "", it, finpayTheme)
            }
        )

        FinpaySDK.getUserBallance(
            transNumber!!,
            this@QRISResultActivity,
            {
                txtSaldo.text = TextUtils.formatRupiah(it.amount!!.toDouble())
                _saldo = TextUtils.formatRupiah(it.amount!!.toDouble())
            },{
                DialogUtils.showDialogError(this@QRISResultActivity, "", it, finpayTheme)
            }
        )

        txtAmount.doOnTextChanged { text, start, before, count ->
            txtTotalBayar.text = txtAmount.text
            _totalBayar = txtAmount.text.toString()
            _tagihan = txtAmount.text.toString()
            btnBayar.isEnabled = (!text.isNullOrBlank() && text.length>=1 && text != "0")
            ButtonUtils.checkButtonState(btnBayar, finpayTheme)
        }

        ButtonUtils.checkButtonState(btnBayar, finpayTheme)
        btnBayar.setOnClickListener {
            DialogUtils.showDialogConfirmPayment(
                this,
                transNumber!!,
                "Transaksi",
                "Pembayaran QRIS",
                TextUtils.clearFormat(_saldo),
                TextUtils.clearFormat(_tagihan),
                TextUtils.clearFormat(_biayaLayanan),
                {
                    progressDialog.setTitle("Mohon Menunggu")
                    progressDialog.setMessage("Sedang Memuat ...")
                    progressDialog.setCancelable(false)
                    progressDialog.show()
                    FinpaySDK.authPin(
                        transNumber!!,
                        this@QRISResultActivity,
                        TextUtils.clearFormat(_totalBayar),
                        ProductCode.QRIS,{
                            progressDialog.dismiss()
                            val intent = Intent(this@QRISResultActivity, PaymentActivity::class.java)
                            intent.putExtra("paymentType", PaymentType.paymentQRIS)
                            intent.putExtra("sof", "mc")
                            intent.putExtra("amount",TextUtils.clearFormat(_totalBayar))
                            intent.putExtra("amountTips", "0")
                            intent.putExtra("reffFlag", _reffFlag)
                            intent.putExtra("widgetURL", it.widgetURL)
                            intent.putExtra("price",TextUtils.clearFormat(_tagihan))
                            intent.putExtra("fee",TextUtils.clearFormat(_biayaLayanan))
                            intent.putExtra("transNumber", transNumber!!)
                            intent.putExtra("theme", finpayTheme)
                            startActivity(intent)
                        }, {
                            progressDialog.dismiss()
                            DialogUtils.showDialogError(this@QRISResultActivity, "", it, finpayTheme)
                        }
                    )
                },
                finpayTheme
            )
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}