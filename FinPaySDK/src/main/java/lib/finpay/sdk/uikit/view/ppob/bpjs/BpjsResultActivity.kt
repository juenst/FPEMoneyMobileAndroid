package lib.finpay.sdk.uikit.view.ppob.bpjs

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.corekit.model.Credential
import lib.finpay.sdk.uikit.FinpaySDKUI
import lib.finpay.sdk.uikit.constant.PaymentType
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.*
import lib.finpay.sdk.uikit.view.payment.PaymentActivity
import java.text.SimpleDateFormat
import java.util.*


class BpjsResultActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    lateinit var txtCustName: TextView
    lateinit var txtCustPhoneNumber: TextView
    lateinit var txtCustId: TextView
    lateinit var txtTagihan: TextView
    lateinit var txtPeriode: TextView
    lateinit var btnBack: ImageView
    lateinit var btnConfirm: Button
    lateinit var progressDialog: ProgressDialog

    val noPelanggan: String? by lazy { intent.getStringExtra("noPelanggan") }
    val customerName: String? by lazy { intent.getStringExtra("customerName") }
    val customerId: String? by lazy { intent.getStringExtra("customerId") }
    val tagihan: String? by lazy { intent.getStringExtra("tagihan") }
    val nomorReferensi: String? by lazy { intent.getStringExtra("nomorReferensi") }
    val fee: String? by lazy { intent.getStringExtra("fee") }
    val periode: String? by lazy { intent.getStringExtra("periode") }
    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    var saldo: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bpjs_result)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        txtCustName = findViewById(R.id.txtCustName)
        txtCustPhoneNumber = findViewById(R.id.txtCustPhoneNumber)
        txtCustId = findViewById(R.id.txtCustId)
        txtTagihan = findViewById(R.id.txtTagihan)
        btnBack = findViewById(R.id.btnBack)
        btnConfirm = findViewById(R.id.btnConfirm)
        txtPeriode = findViewById(R.id.txtPeriode)
        progressDialog = ProgressDialog(this@BpjsResultActivity)

        FinpaySDK.init(this)
        txtCustName.text = customerName
        txtCustPhoneNumber.text = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)
        txtCustId.text = customerId
        txtTagihan.text = TextUtils.formatRupiah(tagihan!!.toDouble())
        txtPeriode.text = periode

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnConfirm.setBackgroundColor(if(btnConfirm.isEnabled()) if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!! else Color.parseColor("#d5d5d5"))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if(finpayTheme?.getAppBarBackgroundColor() == null) {
                window.setStatusBarColor(Color.parseColor("#333333"))
            } else {
                window.setStatusBarColor(finpayTheme?.getAppBarBackgroundColor()!!)
            }
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }

        btnConfirm.setOnClickListener {
            DialogUtils.showDialogConfirmPayment(
                this,
                transNumber!!,
                "Tagihan",
                "Tagihan BPJS",
                TextUtils.clearFormat(saldo),
                TextUtils.clearFormat(tagihan!!),
                TextUtils.clearFormat(fee!!),
                {
                    progressDialog.setTitle("Mohon Menunggu")
                    progressDialog.setMessage("Sedang Memuat ...")
                    progressDialog.setCancelable(false)
                    progressDialog.show()
                    FinpaySDK.authPin(
                        transNumber!!,
                        this,
                        (tagihan!!.toInt() + fee!!.toInt()).toString(),
                        ProductCode.BPJS_KESEHATAN,{
                            progressDialog.dismiss()
                            val intent = Intent(this, PaymentActivity::class.java)
                            intent.putExtra("paymentType", PaymentType.paymentPPOB)
                            intent.putExtra("sof", "mc")
                            intent.putExtra("amount", (tagihan!!.toInt() + fee!!.toInt()).toString())
                            intent.putExtra("denom", tagihan!!)
                            intent.putExtra("reffFlag", nomorReferensi)
                            intent.putExtra("billingId", noPelanggan)
                            intent.putExtra("productCode", ProductCode.TELKOM)
                            intent.putExtra("activationDate", "")
                            intent.putExtra("payType", "billpayment")
                            intent.putExtra("widgetURL", it.widgetURL)
                            intent.putExtra("phoneNumber", txtCustPhoneNumber.text.toString())
                            intent.putExtra("price", tagihan!!)
                            intent.putExtra("fee", fee!!)
                            intent.putExtra("transactionType", "Tagihan BPJS")
                            intent.putExtra("transNumber", transNumber!!)
                            intent.putExtra("theme", finpayTheme)
                            startActivity(intent)
                        }, {
                            progressDialog.dismiss()
                            DialogUtils.showDialogError(this, "", it, finpayTheme)
                        }
                    )
                },
                finpayTheme
            )
        }

        getUserBallance()
    }

    fun getUserBallance() {
        progressDialog.setTitle("Mohon Menunggu")
        progressDialog.setMessage("Sedang Memuat ...")
        progressDialog.setCancelable(false) // blocks UI interaction
        progressDialog.show()
        FinpaySDK.getUserBallance(
            transNumber!!,
            this@BpjsResultActivity, {
            saldo = it.amount!!
            progressDialog.dismiss()
        },{
            progressDialog.dismiss()
            DialogUtils.showDialogError(this@BpjsResultActivity, "", it, finpayTheme)
        })
    }
}