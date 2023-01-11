package lib.finpay.sdk.uikit.view.ppob.insurance

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.corekit.model.PpobInquiry
import lib.finpay.sdk.uikit.constant.PaymentType
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import lib.finpay.sdk.uikit.utilities.TextUtils
import lib.finpay.sdk.uikit.view.payment.PaymentActivity

class InsuranceResultActivity : AppCompatActivity() {
    val result: PpobInquiry? by lazy { if(intent.getSerializableExtra("result") == null) null else intent.getSerializableExtra("result") as PpobInquiry }
    val transNumber: String? by lazy { intent.getStringExtra("transNumber")}
    val type: String? by lazy { intent.getStringExtra("type")}
    val periode: String? by lazy { intent.getStringExtra("periode")}
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
        setContentView(R.layout.activity_insurance_result)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        txtCustName = findViewById(R.id.txtCustName)
        txtCustPhoneNumber = findViewById(R.id.txtCustPhoneNumber)
        txtCustId = findViewById(R.id.txtCustId)
        txtTagihan = findViewById(R.id.txtTagihan)
        btnBack = findViewById(R.id.btnBack)
        btnConfirm = findViewById(R.id.btnConfirm)
        progressDialog = ProgressDialog(this@InsuranceResultActivity)

        FinpaySDK.init(this)
        txtCustName.text = result?.bit61Parse!!.customerName
        txtCustPhoneNumber.text = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)
        txtCustId.text = result?.bit61Parse!!.customerId
        txtTagihan.text = TextUtils.formatRupiah(result?.tagihan!!.toDouble())

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnConfirm.setBackgroundColor(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)
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
            var productCodes: String = ""
            var transactionTypes:String = ""

            if(type == "carlife") {
                productCodes = ProductCode.INSURANCE_CAR
                transactionTypes = "Pembayaran Tagihan Asuransi CarLife"
            } else {
                productCodes = ProductCode.INSURANCE_TOKIO
                transactionTypes = "Pembayaran Tagihan Asuransi Tokio"
            }

            println("productCodes at Insurance : $productCodes")
            println("transactionType at Insurance : $transactionTypes")

            DialogUtils.showDialogConfirmPayment(
                this,
                transNumber!!,
                "Tagihan",
                transactionTypes,
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
                        productCodes, {
                            progressDialog.dismiss()
                            val intent = Intent(this, PaymentActivity::class.java)
                            intent.putExtra("paymentType", PaymentType.paymentPPOB)
                            intent.putExtra(
                                "amount",
                                (result?.tagihan!!.toInt() + fee.toInt()).toString()
                            )
                            intent.putExtra("denom", result?.tagihan!!.toString())
                            intent.putExtra("reffFlag", result?.conf)
                            intent.putExtra("billingId", result?.bit61Parse?.customerId)
                            intent.putExtra("productCode", productCodes)
                            intent.putExtra("phoneNumber", txtCustPhoneNumber.text.toString())
                            intent.putExtra("price", result?.tagihan!!.toString())
                            intent.putExtra("fee", fee)
                            intent.putExtra("result", result)
                            intent.putExtra("pinResult", it)
                            intent.putExtra("transactionType", transactionTypes)
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
        FinpaySDK.getUserBallance(transNumber!!, this@InsuranceResultActivity, {
            saldo = it.amount!!
            progressDialog.dismiss()
        },{
            progressDialog.dismiss()
            DialogUtils.showDialogError(this@InsuranceResultActivity, "", it, finpayTheme)
        })
    }
}