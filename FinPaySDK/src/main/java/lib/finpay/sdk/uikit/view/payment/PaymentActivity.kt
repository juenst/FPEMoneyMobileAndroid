package lib.finpay.sdk.uikit.view.payment

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.uikit.constant.PaymentType
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.view.transaction.TransactionDetailPpobActivity
import lib.finpay.sdk.uikit.view.transaction.TransactionDetailQrisActivity


class PaymentActivity : AppCompatActivity() {
    lateinit var webview: WebView
    lateinit var btnBack: ImageView
    lateinit var toolbar: Toolbar
    val widgetUrl: String? by lazy { intent.getStringExtra("widgetURL") }
    val paymentType: String? by lazy { intent.getStringExtra("paymentType") }
    val sof: String? by lazy { intent.getStringExtra("sof") }
    val amount: String? by lazy { intent.getStringExtra("amount") }
    val amountTips: String? by lazy { intent.getStringExtra("amountTips") }
    val reffFlag: String? by lazy { intent.getStringExtra("reffFlag") }
    val denom: String? by lazy { intent.getStringExtra("denom") }
    val productCode: String? by lazy { intent.getStringExtra("productCode") }
    val billingId: String? by lazy { intent.getStringExtra("billingId") }
    val activationDate: String? by lazy { intent.getStringExtra("activationDate") }
    val payType: String? by lazy { intent.getStringExtra("payType") }
    val phoneNumber: String? by lazy { intent.getStringExtra("phoneNumber") }

    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        supportActionBar!!.hide()

        progressDialog = ProgressDialog(this)
        btnBack = findViewById(R.id.btnBack)
        toolbar = findViewById(R.id.toolbar)
        webview = findViewById(R.id.webview)
        webview.loadUrl(widgetUrl!!)

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
                    toolbar.visibility = View.GONE
                    val pinToken = webUrl.split("/").toTypedArray().last()
                    progressDialog.setTitle("Mohon Menunggu")
                    progressDialog.setMessage("Sedang Memuat ...")
                    progressDialog.setCancelable(false) // blocks UI interaction
                    progressDialog.show()
                    if (paymentType == PaymentType.paymentQRIS) {
                        FinpaySDK.qrisPayment(
                            this@PaymentActivity,
                            sof!!,
                            amount!!,
                            amountTips!!,
                            reffFlag!!,
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
                                intent.putExtra("amount", it.bit61Parse!!.amount)
                                intent.putExtra("paymentCode", it.bit61Parse!!.paymentCode)
                                intent.putExtra("pointOfMethod", it.bit61Parse!!.pointOfMethod)
                                intent.putExtra("tipsType", it.bit61Parse!!.tipsType)
                                intent.putExtra("tipsAmount", it.bit61Parse!!.tipsAmount)
                                intent.putExtra("tipsPercentage", it.bit61Parse!!.tipsPercentage)
                                intent.putExtra("acquirerName", it.bit61Parse!!.acquirerName)
                                intent.putExtra(
                                    "merchantLocation",
                                    it.bit61Parse!!.merchantLocation
                                )
                                intent.putExtra("merchantPAN", it.bit61Parse!!.merchantPAN)
                                intent.putExtra("terminalID", it.bit61Parse!!.terminalID)
                                intent.putExtra("isOnUs", it.bit61Parse!!.isOnUs)
                                intent.putExtra("customerPAN", it.bit61Parse!!.customerPAN)
                                intent.putExtra("invoice", it.bit61Parse!!.invoice)
                                intent.putExtra("reffID", reffFlag)
                                intent.putExtra("statusDesc", it.statusDesc)
                                startActivity(intent)
                                this@PaymentActivity.finish()
                            }, {
                                progressDialog.dismiss()
                                Toast.makeText(this@PaymentActivity, it, Toast.LENGTH_LONG)
                                DialogUtils.showDialogError(this@PaymentActivity, "", it)
                            }
                        )
                    } else if (paymentType == PaymentType.paymentPPOB) {
                        FinpaySDK.ppobPayment(
                            this@PaymentActivity,
                            phoneNumber!!,
                            sof!!,
                            payType!!,
                            denom!!,
                            amount!!,
                            billingId!!,
                            productCode!!,
                            reffFlag!!,
                            activationDate!!, pinToken, {
                                progressDialog.dismiss()
                                val intent = Intent(
                                    this@PaymentActivity,
                                    TransactionDetailPpobActivity::class.java
                                )
                                intent.putExtra("amountDpp", "0")
                                intent.putExtra("amountPpn", "0")
                                intent.putExtra("ppn", "0%")
                                intent.putExtra(
                                    "nomorReferensi",
                                    it.bit61Parse!!.billInfo1!!.nomorReferensi
                                )
                                intent.putExtra(
                                    "nilaiTagihan",
                                    it.bit61Parse!!.billInfo1!!.nilaiTagihan
                                )
                                intent.putExtra("customerId", it.bit61Parse!!.customerId)
                                intent.putExtra("customerName", it.bit61Parse!!.customerName)
                                intent.putExtra("npwp", it.bit61Parse!!.npwp)
                                intent.putExtra("kodeDivre", it.bit61Parse!!.kodeDivre)
                                intent.putExtra("kodeDatel", it.bit61Parse!!.kodeDatel)
                                intent.putExtra("jumlahBill", it.bit61Parse!!.jumlahBill)
                                intent.putExtra("statusDesc", it.statusDesc)

                                startActivity(intent)
                                this@PaymentActivity.finish()
                            }, {
                                progressDialog.dismiss()
                                DialogUtils.showDialogError(this@PaymentActivity, "", it)
                            }
                        )
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