package lib.finpay.sdk.uikit.view.ppob.pln

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
import lib.finpay.sdk.corekit.model.DataSubProduct
import lib.finpay.sdk.corekit.model.PpobInquiry
import lib.finpay.sdk.uikit.constant.PaymentType
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.TextUtils
import lib.finpay.sdk.uikit.utilities.Utils
import lib.finpay.sdk.uikit.view.payment.PaymentActivity
import lib.finpay.sdk.uikit.view.ppob.pulsa_data.adapter.PulsaDataAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class PLNResultActivity : AppCompatActivity() {
    val inquiry = intent.getSerializableExtra("result") as? PpobInquiry
    val noPelanggan: String? by lazy { intent.getStringExtra("noPelanggan") }
    lateinit var txtCustName: TextView
    lateinit var txtCustPhoneNumber: TextView
    lateinit var txtCustId: TextView
    lateinit var txtDaya: TextView
    lateinit var txtTagihan: TextView
    lateinit var btnBack: ImageView
    lateinit var btnConfirm: ImageView
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

        txtCustName.text = inquiry?.bit61Parse?.customerName
        txtCustPhoneNumber.text = noPelanggan
        txtCustId.text = inquiry?.bit61Parse?.customerId
        txtDaya.text = "-"
        txtTagihan.text = TextUtils.formatRupiah(inquiry?.tagihan!!.toDouble())

        btnBack.setOnClickListener {
            onBackPressed()
        }

        btnConfirm.setOnClickListener {
            showDialogConfirmPayment()
        }

    }

    fun getUserBallance() {
        FinpaySDK.getUserBallance(this@PLNResultActivity, {
            saldo = it.amount!!
        },{
            Toast.makeText(this@PLNResultActivity, it, Toast.LENGTH_LONG)
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

        val reffId: String = inquiry?.bit61Parse?.billInfo1!!.nomorReferensi!!
        var fee: String = "0"
        for (data in inquiry.fee) {
            if (data.sof == "mc") {
                fee = data.fee.toString()
            }
        }


        if(saldo.toInt() < (inquiry.tagihan!!.toInt() + fee.toInt())){
            cardWarning!!.visibility = View.VISIBLE
            txtBtnPay!!.text = "Isi Saldo"
        }

        txtDenom!!.text = "Token PLN "+TextUtils.formatRupiah(inquiry.tagihan!!.toDouble())
        txtPrice!!.text = TextUtils.formatRupiah(inquiry.tagihan!!.toDouble())
        txtBiayaLayanan!!.text = TextUtils.formatRupiah(fee.toDouble())
        txtTotalBayar!!.text = TextUtils.formatRupiah((inquiry.tagihan!!.toInt() + fee.toInt()).toDouble())
        txtSaldo!!.text = TextUtils.formatRupiah(saldo.toDouble())

        btnPay?.setOnClickListener {
            if(saldo.toInt() < (inquiry.tagihan!!.toInt() + fee.toInt())){
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
                    inquiry.tagihan!!.toString(), ProductCode.PLN_POSTPAID,{
                        progressDialog.dismiss()
                        val intent = Intent(this@PLNResultActivity, PaymentActivity::class.java)
                        intent.putExtra("paymentType", PaymentType.paymentPPOB)
                        intent.putExtra("sof", "mc")
                        intent.putExtra("amount", (inquiry.tagihan!!.toInt() + fee.toInt()).toString())
                        intent.putExtra("denom", inquiry.tagihan!!)
                        intent.putExtra("reffFlag", reffId)
                        intent.putExtra("billingId", noPelanggan)
                        intent.putExtra("productCode", ProductCode.PLN_POSTPAID)
                        intent.putExtra("activationDate", currentDate)
                        intent.putExtra("payType", "billpayment")
                        intent.putExtra("widgetURL", it.widgetURL)
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