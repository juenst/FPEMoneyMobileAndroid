package lib.finpay.sdk.uikit.view.ppob.pascabayar

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
import lib.finpay.sdk.corekit.model.PpobInquiry
import lib.finpay.sdk.uikit.FinpaySDKUI
import lib.finpay.sdk.uikit.constant.PaymentType
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.*
import lib.finpay.sdk.uikit.view.payment.PaymentActivity
import java.text.SimpleDateFormat
import java.util.*


class PascaBayarResultActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    lateinit var txtCustName: TextView
    lateinit var txtCustPhoneNumber: TextView
    lateinit var txtCustId: TextView
    lateinit var txtTagihan: TextView
    lateinit var btnBack: ImageView
    lateinit var btnConfirm: Button
    lateinit var progressDialog: ProgressDialog

    var saldo: String = "0"

//    val noPelanggan: String? by lazy { intent.getStringExtra("noPelanggan") }
//    val customerName: String? by lazy { intent.getStringExtra("customerName") }
//    val customerId: String? by lazy { intent.getStringExtra("customerId") }
//    val tagihan: String? by lazy { intent.getStringExtra("tagihan") }
//    val nomorReferensi: String? by lazy { intent.getStringExtra("nomorReferensi") }
//    val fee: String? by lazy { intent.getStringExtra("fee") }
    val result: PpobInquiry? by lazy { if(intent.getSerializableExtra("result") == null) null else intent.getSerializableExtra("result") as PpobInquiry }
    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pascabayar_result)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        txtCustName = findViewById(R.id.txtCustName)
        txtCustPhoneNumber = findViewById(R.id.txtCustPhoneNumber)
        txtCustId = findViewById(R.id.txtCustId)
        txtTagihan = findViewById(R.id.txtTagihan)
        btnBack = findViewById(R.id.btnBack)
        btnConfirm = findViewById(R.id.btnConfirm)
        progressDialog = ProgressDialog(this@PascaBayarResultActivity)

        FinpaySDK.init(this)
        txtCustName.text = result?.bit61Parse!!.customerName
        txtCustPhoneNumber.text = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)
        txtCustId.text = result?.bit61Parse!!.customerId
        txtTagihan.text = TextUtils.formatRupiah(result?.tagihan!!.toDouble())

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
            var fee: String = "0"
            for (data in result?.fee!!) {
                if (data.sof == "mc") {
                    fee = data.fee.toString()
                }
            }
            DialogUtils.showDialogConfirmPayment(
                this,
                transNumber!!,
                "Tagihan",
                "Tagihan HALO ${TextUtils.formatRupiah(result?.tagihan!!.toDouble())}",
                TextUtils.clearFormat(saldo),
                TextUtils.clearFormat(result?.tagihan!!.toString()),
                TextUtils.clearFormat(fee),
                {
                    progressDialog.setTitle("Mohon Menunggu")
                    progressDialog.setMessage("Sedang Memuat ...")
                    progressDialog.setCancelable(false)
                    progressDialog.show()
                    FinpaySDK.authPin(
                        transNumber!!,
                        this,
                        (result?.tagihan!!.toInt() + fee.toInt()).toString(),
                        ProductCode.PASCABAYAR,{
                            progressDialog.dismiss()
                            val intent = Intent(this, PaymentActivity::class.java)
                            intent.putExtra("paymentType", PaymentType.paymentPPOB)
                            intent.putExtra("amount", (result?.tagihan!!.toInt() + fee.toInt()).toString())
                            intent.putExtra("denom", result?.tagihan!!.toString())
                            intent.putExtra("reffFlag", result?.conf)
                            intent.putExtra("billingId", result?.bit61Parse!!.billRef)
                            intent.putExtra("productCode", ProductCode.PASCABAYAR)
                            intent.putExtra("phoneNumber", txtCustPhoneNumber.text.toString())
                            intent.putExtra("price", result?.tagihan!!.toString())
                            intent.putExtra("fee", fee)
                            intent.putExtra("result", result)
                            intent.putExtra("pinResult", it)
                            intent.putExtra("transactionType", "Tagihan HALO Pascabayar")
                            intent.putExtra("transNumber", result?.traxId)
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
            this@PascaBayarResultActivity, {
            saldo = it.amount!!
            progressDialog.dismiss()
        },{
            progressDialog.dismiss()
            DialogUtils.showDialogError(this@PascaBayarResultActivity, "", it, finpayTheme)
        })
    }

//    fun showDialogConfirmPayment() {
//        val dialog = BottomSheetDialog(this)
//        dialog.setContentView(R.layout.dialog_confirm_payment_ppob)
//        val btnPay = dialog.findViewById<CardView>(R.id.btnPay)
//        val txtBtnPay = dialog.findViewById<TextView>(R.id.txtBtnPay)
//        val txtDenom = dialog.findViewById<TextView>(R.id.txtDenom)
//        val txtPrice = dialog.findViewById<TextView>(R.id.txtPrice)
//        val txtBiayaLayanan= dialog.findViewById<TextView>(R.id.txtBiayaLayanan)
//        val txtTotalBayar= dialog.findViewById<TextView>(R.id.txtTotalBayar)
//        val txtSaldo= dialog.findViewById<TextView>(R.id.saldo)
//        val cardWarning= dialog.findViewById<CardView>(R.id.cardWarning)
//        val logoProvider= dialog.findViewById<ImageView>(R.id.logoProvider)
//
//
//        if(saldo.toInt() < (tagihan!!.toInt() + fee!!.toInt())){
//            cardWarning!!.visibility = View.VISIBLE
//            txtBtnPay!!.text = "Isi Saldo"
//        }
//        logoProvider!!.visibility = View.GONE
//        txtDenom!!.text = "Tagihan HALO "+TextUtils.formatRupiah(tagihan!!.toDouble())
//        txtPrice!!.text = TextUtils.formatRupiah(tagihan!!.toDouble())
//        txtBiayaLayanan!!.text = TextUtils.formatRupiah(fee!!.toDouble())
//        txtTotalBayar!!.text = TextUtils.formatRupiah((tagihan!!.toInt() + fee!!.toInt()).toDouble())
//        txtSaldo!!.text = TextUtils.formatRupiah(saldo.toDouble())
//
//        btnPay?.setOnClickListener {
//            if(saldo.toInt() < (tagihan!!.toInt() + fee!!.toInt())){
//                FinpaySDKUI.topupUIBuilder("", this, Credential())
//            } else {
//                progressDialog.setTitle("Mohon Menunggu")
//                progressDialog.setMessage("Sedang Memuat ...")
//                progressDialog.setCancelable(false) // blocks UI interaction
//                progressDialog.show()
//                val sdf = SimpleDateFormat("yyyyMMddHHmmss")
//                val currentDate = sdf.format(Date())
//                FinpaySDK.authPin(
//                    java.util.UUID.randomUUID().toString(),
//                    this@PascaBayarResultActivity,
//                    tagihan!!.toString(), ProductCode.PASCABAYAR,{
//                        progressDialog.dismiss()
//                        dialog.dismiss()
//                        val intent = Intent(this@PascaBayarResultActivity, PaymentActivity::class.java)
//                        intent.putExtra("paymentType", PaymentType.paymentPPOB)
//                        intent.putExtra("sof", "mc")
//                        intent.putExtra("amount", (tagihan!!.toInt() + fee!!.toInt()).toString())
//                        intent.putExtra("denom", tagihan!!)
//                        intent.putExtra("reffFlag", nomorReferensi)
//                        intent.putExtra("billingId", noPelanggan)
//                        intent.putExtra("productCode", ProductCode.TELKOM)
//                        intent.putExtra("activationDate", currentDate)
//                        intent.putExtra("payType", "billpayment")
//                        intent.putExtra("widgetURL", it.widgetURL)
//                        intent.putExtra("phoneNumber", txtCustPhoneNumber.text.toString())
//                        intent.putExtra("transNumber", transNumber!!)
//                        intent.putExtra("theme", finpayTheme)
//                        startActivity(intent)
//                    }, {
//                        progressDialog.dismiss()
//                        DialogUtils.showDialogError(this@PascaBayarResultActivity, "", it, finpayTheme)
//                    }
//                )
//            }
//        }
//        dialog.show()
//    }
}