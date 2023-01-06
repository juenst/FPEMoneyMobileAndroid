package lib.finpay.sdk.uikit.view.ppob.telkom

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.corekit.model.Credential
import lib.finpay.sdk.corekit.model.DataSubProduct
import lib.finpay.sdk.corekit.model.PpobInquiry
import lib.finpay.sdk.uikit.FinpaySDKUI
import lib.finpay.sdk.uikit.constant.PaymentType
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.*
import lib.finpay.sdk.uikit.view.payment.PaymentActivity
import lib.finpay.sdk.uikit.view.ppob.pulsa_data.adapter.PulsaDataAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class TelkomResultActivity : AppCompatActivity() {
    val noPelanggan: String? by lazy { intent.getStringExtra("noPelanggan") }
    val customerName: String? by lazy { intent.getStringExtra("customerName") }
    val customerId: String? by lazy { intent.getStringExtra("customerId") }
    val tagihan: String? by lazy { intent.getStringExtra("tagihan") }
    val nomorReferensi: String? by lazy { intent.getStringExtra("nomorReferensi") }
    val fee: String? by lazy { intent.getStringExtra("fee") }
    val transNumber: String? by lazy { intent.getStringExtra("transNumber")}
    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telkom_result)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        txtCustName = findViewById(R.id.txtCustName)
        txtCustPhoneNumber = findViewById(R.id.txtCustPhoneNumber)
        txtCustId = findViewById(R.id.txtCustId)
        txtTagihan = findViewById(R.id.txtTagihan)
        btnBack = findViewById(R.id.btnBack)
        btnConfirm = findViewById(R.id.btnConfirm)
        progressDialog = ProgressDialog(this@TelkomResultActivity)

        FinpaySDK.init(this)
        txtCustName.text = customerName
        txtCustPhoneNumber.text = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)
        txtCustId.text = customerId
        txtTagihan.text = TextUtils.formatRupiah(tagihan!!.toDouble())

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnConfirm.setBackgroundColor(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)

        btnBack.setOnClickListener {
            onBackPressed()
        }

        btnConfirm.setOnClickListener {
            showDialogConfirmPayment()
        }

        getUserBallance()
    }

    fun getUserBallance() {
        progressDialog.setTitle("Mohon Menunggu")
        progressDialog.setMessage("Sedang Memuat ...")
        progressDialog.setCancelable(false) // blocks UI interaction
        progressDialog.show()
        FinpaySDK.getUserBallance(transNumber!!, this@TelkomResultActivity, {
            saldo = it.amount!!
            progressDialog.dismiss()
        },{
            progressDialog.dismiss()
            DialogUtils.showDialogError(this@TelkomResultActivity, "", it)
        })
    }

    fun showDialogConfirmPayment() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.dialog_confirm_payment_ppob)
        val btnPay = dialog.findViewById<CardView>(R.id.btnPay)
        val txtBtnPay = dialog.findViewById<TextView>(R.id.txtBtnPay)
        val txtDenom = dialog.findViewById<TextView>(R.id.txtDenom)
        val txtPrice = dialog.findViewById<TextView>(R.id.txtPrice)
        val txtBiayaLayanan= dialog.findViewById<TextView>(R.id.txtBiayaLayanan)
        val txtTotalBayar= dialog.findViewById<TextView>(R.id.txtTotalBayar)
        val txtSaldo= dialog.findViewById<TextView>(R.id.saldo)
        val cardWarning= dialog.findViewById<CardView>(R.id.cardWarning)
        val logoProvider= dialog.findViewById<ImageView>(R.id.logoProvider)


        if(saldo.toInt() < (tagihan!!.toInt() + fee!!.toInt())){
            cardWarning!!.visibility = View.VISIBLE
            txtBtnPay!!.text = "Isi Saldo"
        }
        logoProvider!!.visibility = View.GONE
        txtDenom!!.text = "Tagihan Telkom "+TextUtils.formatRupiah(tagihan!!.toDouble())
        txtPrice!!.text = TextUtils.formatRupiah(tagihan!!.toDouble())
        txtBiayaLayanan!!.text = TextUtils.formatRupiah(fee!!.toDouble())
        txtTotalBayar!!.text = TextUtils.formatRupiah((tagihan!!.toInt() + fee!!.toInt()).toDouble())
        txtSaldo!!.text = TextUtils.formatRupiah(saldo.toDouble())

        btnPay?.setBackgroundColor(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)

        btnPay?.setOnClickListener {
            if(saldo.toInt() < (tagihan!!.toInt() + fee!!.toInt())){
                FinpaySDKUI.topupUIBuilder("", this, Credential())
            } else {
                progressDialog.setTitle("Mohon Menunggu")
                progressDialog.setMessage("Sedang Memuat ...")
                progressDialog.setCancelable(false) // blocks UI interaction
                progressDialog.show()
                val sdf = SimpleDateFormat("yyyyMMddHHmmss")
                val currentDate = sdf.format(Date())
                FinpaySDK.authPin(
                    java.util.UUID.randomUUID().toString(),
                    this@TelkomResultActivity,
                    tagihan!!.toString(), ProductCode.TELKOM,{
                        progressDialog.dismiss()
                        dialog.dismiss()
                        val intent = Intent(this@TelkomResultActivity, PaymentActivity::class.java)
                        intent.putExtra("paymentType", PaymentType.paymentPPOB)
                        intent.putExtra("sof", "mc")
                        intent.putExtra("amount", (tagihan!!.toInt() + fee!!.toInt()).toString())
                        intent.putExtra("denom", tagihan!!)
                        intent.putExtra("reffFlag", nomorReferensi)
                        intent.putExtra("billingId", noPelanggan)
                        intent.putExtra("productCode", ProductCode.TELKOM)
                        intent.putExtra("activationDate", currentDate)
                        intent.putExtra("payType", "billpayment")
                        intent.putExtra("widgetURL", it.widgetURL)
                        intent.putExtra("phoneNumber", txtCustPhoneNumber.text.toString())
                        startActivity(intent)
                    }, {
                        progressDialog.dismiss()
                        DialogUtils.showDialogError(this@TelkomResultActivity, "", it)
                    }
                )
            }
        }
        dialog.show()
    }
}