package lib.finpay.sdk.uikit.view.ppob.pln

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.uikit.constant.PaymentType
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import lib.finpay.sdk.uikit.utilities.TextUtils
import lib.finpay.sdk.uikit.view.payment.PaymentActivity
import java.text.SimpleDateFormat
import java.util.*


class PLNResultActivity : AppCompatActivity() {
    //    val inquiry = intent.getSerializableExtra("result") as? PpobInquiry
    val noPelanggan: String? by lazy { intent.getStringExtra("noPelanggan") }
    val customerName: String? by lazy { intent.getStringExtra("customerName") }
    val customerId: String? by lazy { intent.getStringExtra("customerId") }
    val tagihan: String? by lazy { intent.getStringExtra("tagihan") }
    val nomorReferensi: String? by lazy { intent.getStringExtra("nomorReferensi") }
    val fee: String? by lazy { intent.getStringExtra("fee") }
    lateinit var txtCustName: TextView
    lateinit var txtCustPhoneNumber: TextView
    lateinit var txtCustId: TextView
    lateinit var txtDaya: TextView
    lateinit var txtTagihan: TextView
    lateinit var btnBack: ImageView
    lateinit var btnConfirm: Button
    lateinit var progressDialog: ProgressDialog

    var saldo: String = "0"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pln_result)
        supportActionBar!!.hide()

        txtCustName = findViewById(R.id.txtCustName)
        txtCustPhoneNumber = findViewById(R.id.txtCustPhoneNumber)
        txtCustId = findViewById(R.id.txtCustId)
        txtDaya = findViewById(R.id.txtDaya)
        txtTagihan = findViewById(R.id.txtTagihan)
        btnBack = findViewById(R.id.btnBack)
        btnConfirm = findViewById(R.id.btnConfirm)
        progressDialog = ProgressDialog(this@PLNResultActivity)

        FinpaySDK.init(this)

        txtCustName.text = customerName
        txtCustPhoneNumber.text =
            FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)
        txtCustId.text = customerId
        txtDaya.text = "-"
        txtTagihan.text = TextUtils.formatRupiah(tagihan!!.toDouble())

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
        FinpaySDK.getUserBallance(this@PLNResultActivity, {
            saldo = it.amount!!
            progressDialog.dismiss()
        }, {
            progressDialog.dismiss()
            DialogUtils.showDialogError(this@PLNResultActivity, "", it)
        })
    }

    fun showDialogConfirmPayment() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.dialog_confirm_payment_ppob)
        val btnPay = dialog.findViewById<CardView>(R.id.btnPay)
        val txtBtnPay = dialog.findViewById<TextView>(R.id.txtBtnPay)
        val txtDenom = dialog.findViewById<TextView>(R.id.txtDenom)
        val txtPrice = dialog.findViewById<TextView>(R.id.txtPrice)
        val txtBiayaLayanan = dialog.findViewById<TextView>(R.id.txtBiayaLayanan)
        val txtTotalBayar = dialog.findViewById<TextView>(R.id.txtTotalBayar)
        val txtSaldo = dialog.findViewById<TextView>(R.id.saldo)
        val cardWarning = dialog.findViewById<CardView>(R.id.cardWarning)
        val logoProvider = dialog.findViewById<ImageView>(R.id.logoProvider)


        if (saldo.toInt() < (tagihan!!.toInt() + fee!!.toInt())) {
            cardWarning!!.visibility = View.VISIBLE
            txtBtnPay!!.text = "Isi Saldo"
        }
        logoProvider!!.visibility = View.GONE
        txtDenom!!.text = "Token PLN " + TextUtils.formatRupiah(tagihan!!.toDouble())
        txtPrice!!.text = TextUtils.formatRupiah(tagihan!!.toDouble())
        txtBiayaLayanan!!.text = TextUtils.formatRupiah(fee!!.toDouble())
        txtTotalBayar!!.text =
            TextUtils.formatRupiah((tagihan!!.toInt() + fee!!.toInt()).toDouble())
        txtSaldo!!.text = TextUtils.formatRupiah(saldo.toDouble())

        btnPay?.setOnClickListener {
            if (saldo.toInt() < (tagihan!!.toInt() + fee!!.toInt())) {
                //open top up
            } else {
                progressDialog.setTitle("Mohon Menunggu")
                progressDialog.setMessage("Sedang Memuat ...")
                progressDialog.setCancelable(false) // blocks UI interaction
                progressDialog.show()
                val sdf = SimpleDateFormat("yyyyMMdHHmmss")
                val currentDate = sdf.format(Date())
                FinpaySDK.authPin(
                    this@PLNResultActivity,
                    tagihan!!.toString(), ProductCode.PLN_POSTPAID, {
                        progressDialog.dismiss()
                        val intent = Intent(this@PLNResultActivity, PaymentActivity::class.java)
                        intent.putExtra("paymentType", PaymentType.paymentPPOB)
                        intent.putExtra("sof", "mc")
                        intent.putExtra("amount", (tagihan!!.toInt() + fee!!.toInt()).toString())
                        intent.putExtra("denom", tagihan!!)
                        intent.putExtra("reffFlag", nomorReferensi)
                        intent.putExtra("billingId", noPelanggan)
                        intent.putExtra("productCode", ProductCode.PLN_PREPAID)
                        intent.putExtra("activationDate", currentDate)
                        intent.putExtra("payType", "billpayment")
                        intent.putExtra("widgetURL", it.widgetURL)
                        intent.putExtra("phoneNumber", txtCustPhoneNumber.text.toString())
                        startActivity(intent)
                    }, {
                        progressDialog.dismiss()
                        DialogUtils.showDialogError(this@PLNResultActivity, "", it)
                    }
                )
            }
        }
        dialog.show()
    }
}