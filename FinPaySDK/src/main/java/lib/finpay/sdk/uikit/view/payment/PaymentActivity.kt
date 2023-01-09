package lib.finpay.sdk.uikit.view.payment

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.helper.DateHelper
import lib.finpay.sdk.corekit.model.PinAuth
import lib.finpay.sdk.corekit.model.PpobInquiry
import lib.finpay.sdk.uikit.constant.PaymentType
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import lib.finpay.sdk.uikit.view.transaction.TransactionDetailPpobActivity
import lib.finpay.sdk.uikit.view.transaction.TransactionDetailQrisActivity


class PaymentActivity : AppCompatActivity() {
    lateinit var webview: WebView
    lateinit var btnBack: ImageView
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
//    val widgetUrl: String? by lazy { intent.getStringExtra("widgetURL") }
//    val sof: String? by lazy { intent.getStringExtra("sof") }
    val amount: String? by lazy { intent.getStringExtra("amount") }
//    val amountTips: String? by lazy { intent.getStringExtra("amountTips") }
    val reffFlag: String? by lazy { intent.getStringExtra("reffFlag") }
    val denom: String? by lazy { if(intent.getStringExtra("denom") == null) "0" else intent.getStringExtra("denom") }
    val productCode: String? by lazy { intent.getStringExtra("productCode") }
    val billingId: String? by lazy { if(intent.getStringExtra("billingId") == null) "" else intent.getStringExtra("billingId") }
//    val activationDate: String? by lazy { intent.getStringExtra("activationDate") }
//    val payType: String? by lazy { intent.getStringExtra("payType") }
    val transactionType: String? by lazy { if(intent.getStringExtra("transactionType") == null) "" else intent.getStringExtra("transactionType")}
//    val phoneNumberDest: String? by lazy { intent.getStringExtra("phoneNumberDest") }
//    val reffTrx: String? by lazy { intent.getStringExtra("reffTrx") }
//    val category: String? by lazy { intent.getStringExtra("category") }
//    val desc: String? by lazy { intent.getStringExtra("desc") }
    val price: String? by lazy { intent.getStringExtra("price") }
    val fee: String? by lazy { intent.getStringExtra("fee") }

    val phoneNumber: String? by lazy { if(intent.getStringExtra("phoneNumber") == null) FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER) else intent.getStringExtra("phoneNumber")}
    val paymentType: String? by lazy { intent.getStringExtra("paymentType") }
    val result: PpobInquiry? by lazy { if(intent.getSerializableExtra("result") == null) null else intent.getSerializableExtra("result") as PpobInquiry }
    val pinResult: PinAuth? by lazy { if(intent.getSerializableExtra("pinResult") == null) null else intent.getSerializableExtra("pinResult") as PinAuth }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}
    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }

    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        progressDialog = ProgressDialog(this)
        btnBack = findViewById(R.id.btnBack)
        webview = findViewById(R.id.webview)
        webview.loadUrl(pinResult?.widgetURL!!)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if(finpayTheme?.getAppBarBackgroundColor() == null) {
                window.setStatusBarColor(Color.parseColor("#333333"))
            } else {
                window.setStatusBarColor(finpayTheme?.getAppBarBackgroundColor()!!)
            }
        }

        // Enable Javascript
        val webSettings: WebSettings = webview.getSettings()
        webSettings.javaScriptEnabled = true
        webview.setWebViewClient(object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                // runs when a page starts loading
                progressDialog.setTitle("Mohon Menunggu")
                progressDialog.setMessage("Sedang Memuat ...")
                progressDialog.setCancelable(false) // blocks UI interaction
                progressDialog.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                // page finishes loading
                progressDialog.dismiss()
                val webUrl: String = webview.getUrl()!!
                if (webUrl.contains("sukses")) {
                    appbar.visibility = View.GONE
                    val pinToken = webUrl.split("/").toTypedArray().last()
                    progressDialog.setTitle("Mohon Menunggu")
                    progressDialog.setMessage("Sedang Memuat ...")
                    progressDialog.setCancelable(false) // blocks UI interaction
                    progressDialog.show()
                    if (paymentType == PaymentType.paymentQRIS) {
                        FinpaySDK.qrisPayment(
                            transNumber!!,
                            this@PaymentActivity,
                            "mc",
                            "",
                            "",
                            "",
                            pinToken, {
                                progressDialog.dismiss()
                                val intent = Intent(
                                    this@PaymentActivity,
                                    TransactionDetailQrisActivity::class.java
                                )
//                                intent.putExtra("result", it)
                                intent.putExtra("merchantName", it.bit61Parse!!.merchantName)
                                intent.putExtra("merchantId", it.bit61Parse!!.merchantId)
                                intent.putExtra("nevaNumber", it.bit61Parse!!.nevaNumber)
                                intent.putExtra("amount", "0")//it.bit61Parse!!.amount)
                                intent.putExtra("price", "0")
                                intent.putExtra("fee", "0")
                                intent.putExtra("paymentCode", it.bit61Parse!!.paymentCode)
                                intent.putExtra("pointOfMethod", it.bit61Parse!!.pointOfMethod)
                                intent.putExtra("tipsType", it.bit61Parse!!.tipsType)
                                intent.putExtra("tipsAmount", it.bit61Parse!!.tipsAmount)
                                intent.putExtra("tipsPercentage", it.bit61Parse!!.tipsPercentage)
                                intent.putExtra("acquirerName", it.bit61Parse!!.acquirerName)
                                intent.putExtra("merchantLocation", it.bit61Parse!!.merchantLocation)
                                intent.putExtra("merchantPAN", it.bit61Parse!!.merchantPAN)
                                intent.putExtra("terminalID", it.bit61Parse!!.terminalID)
                                intent.putExtra("isOnUs", it.bit61Parse!!.isOnUs)
                                intent.putExtra("customerPAN", it.bit61Parse!!.customerPAN)
                                intent.putExtra("invoice", it.bit61Parse!!.invoice)
                                intent.putExtra("reffID", "")
                                intent.putExtra("statusDesc", it.statusDesc)
                                intent.putExtra("transactionDate", DateHelper.getCurrentDateTransaction())
                                intent.putExtra("theme", finpayTheme!!)
                                startActivity(intent)
                                this@PaymentActivity.finish()
                            }, {
                                progressDialog.dismiss()
                                DialogUtils.showDialogError(this@PaymentActivity, "", it, finpayTheme)
                            }
                        )
                    } else if (paymentType == PaymentType.paymentPPOB) {
                        FinpaySDK.ppobPayment(
                            transNumber!!,
                            this@PaymentActivity,
                            phoneNumber!!,
                            "mc",
                            "billpayment",
                            denom!!,
                            amount!!,
                            billingId!!,
                            productCode!!,
                            reffFlag!!,
                            "",
                            pinToken,
                            {
                                progressDialog.dismiss()
                                val intent = Intent(this@PaymentActivity, TransactionDetailPpobActivity::class.java)
                                intent.putExtra("amount", amount)
                                intent.putExtra("price", price)
                                intent.putExtra("fee", fee)
                                intent.putExtra("result", it)
                                intent.putExtra("transNumber", transNumber!!)
                                intent.putExtra("transactionType", transactionType!!)
                                intent.putExtra("transactionDate", DateHelper.getCurrentDateTransaction())
                                intent.putExtra("theme", finpayTheme!!)

                                startActivity(intent)
                                this@PaymentActivity.finish()
                            }, {
                                progressDialog.dismiss()
                                DialogUtils.showDialogError(this@PaymentActivity, "", it, finpayTheme)
                            }
                        )
                    } else if (paymentType == PaymentType.transferToOther) {
//                        FinpaySDK.transferToBankPayment(
//                            transNumber!!,
//                            this@PaymentActivity,
//                            phoneNumberDest!!,
//                            reffFlag!!,
//                            reffTrx!!,
//                            category!!,
//                            pinToken,
//                            desc!!,
//                            {
//                                progressDialog.dismiss()
//                                DialogUtils.showDialogSuccess(this@PaymentActivity, "Transfer Berhasil", "Anda berhasil transfer ke ${phoneNumberDest}", {
//                                    this@PaymentActivity.finish()
//                                })
//
//                            }, {
//                                progressDialog.dismiss()
//                                DialogUtils.showDialogError(this@PaymentActivity, "", it, finpayTheme)
//                            }
//                        )
                    } else if (paymentType == PaymentType.transferToBank) {

                    } else {
                        progressDialog.dismiss()
                    }
                }
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)

                // runs when there's a failure in loading page
                progressDialog.dismiss()
                Toast.makeText(
                    this@PaymentActivity,
                    "Failure on loading web page",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}