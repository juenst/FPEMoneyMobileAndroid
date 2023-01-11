package lib.finpay.sdk.uikit.view.pin

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.uikit.FinpaySDKUI
import lib.finpay.sdk.uikit.constant.Credential
import lib.finpay.sdk.uikit.constant.PaymentType
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import lib.finpay.sdk.uikit.view.AppActivity
import lib.finpay.sdk.uikit.view.transaction.TransactionDetailQrisActivity

class PinActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}
    val pinType: String? by lazy {
        intent.getStringExtra("pinType")
    }
    lateinit var progressDialog: ProgressDialog

    val pin = mutableListOf("", "", "", "", "", "", "")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_pin)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        progressDialog = ProgressDialog(this@PinActivity)
        val backButton = findViewById<ImageView>(R.id.backButton)

        val firstPin = findViewById(R.id.firstPinInput) as TextView
        val secondPin = findViewById(R.id.secondPinInput) as TextView
        val thirdPin = findViewById(R.id.thirdPinInput) as TextView
        val fourthPin = findViewById(R.id.fourthPinInput) as TextView
        val fifthPin = findViewById(R.id.fifthPinInput) as TextView
        val sixthPin = findViewById(R.id.sixthPinInput) as TextView

        val pinButton0 = findViewById(R.id.pinButton0Input) as TextView
        val pinButton1 = findViewById(R.id.pinButton1Input) as TextView
        val pinButton2 = findViewById(R.id.pinButton2Input) as TextView
        val pinButton3 = findViewById(R.id.pinButton3Input) as TextView
        val pinButton4 = findViewById(R.id.pinButton4Input) as TextView
        val pinButton5 = findViewById(R.id.pinButton5Input) as TextView
        val pinButton6 = findViewById(R.id.pinButton6Input) as TextView
        val pinButton7 = findViewById(R.id.pinButton7Input) as TextView
        val pinButton8 = findViewById(R.id.pinButton8Input) as TextView
        val pinButton9 = findViewById(R.id.pinButton9Input) as TextView
        val pinButtonDel = findViewById(R.id.pinButtonDelInput) as ImageView

        val texts = listOf(firstPin, secondPin, thirdPin, fourthPin, fifthPin, sixthPin)
        val pins = listOf(pinButton0,pinButton1,pinButton2,pinButton3,pinButton4,pinButton5,pinButton6,pinButton7,pinButton8,pinButton9)

        for(i in 0..9){
            pins[i].setOnClickListener {
                onPinPressed(i.toString(), pin, texts)
                if(pin[5]!=""){
                    if(pinType == "login") {
                        val intent = Intent(this, AppActivity::class.java)
                        intent.putExtra("transNumber", transNumber!!)
                        intent.putExtra("theme", finpayTheme)
                        startActivity(intent)
                    } else if(pinType == PaymentType.paymentQRIS) {
                        paymenQris()
                    } else if(pinType == "otp_connect") {
                        otpConnectAccount()
                    }
                }
            }
        }

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        backButton.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if(finpayTheme?.getAppBarBackgroundColor() == null) {
                window.setStatusBarColor(Color.parseColor("#333333"))
            } else {
                window.setStatusBarColor(finpayTheme?.getAppBarBackgroundColor()!!)
            }
        }

        pinButtonDel.setOnClickListener {
            onDeleted(pin, texts)

        }

        backButton.setOnClickListener{
            onBackPressed()
        }
    }

    fun onDeleted(array:MutableList<String>, text:List<TextView>){
        if(array[0]!=""){
            array[array.indexOfLast { it!="" }] = ""
            text[0].text = if (array[0]!="") "•" else ""
            text[1].text=if (array[1]!="") "•" else ""
            text[2].text=if (array[2]!="") "•" else ""
            text[3].text=if (array[3]!="") "•" else ""
            text[4].text=if (array[4]!="") "•" else ""
            text[5].text=if (array[5]!="") "•" else ""
        }
    }

    fun onPinPressed(value:String, array:MutableList<String>, text:List<TextView>){
        if(array[5]==""){
            array[array.indexOfFirst { it=="" }] = value
            text[0].text = if (array[0]!="") "•" else ""
            text[1].text=if (array[1]!="") "•" else ""
            text[2].text=if (array[2]!="") "•" else ""
            text[3].text=if (array[3]!="") "•" else ""
            text[4].text=if (array[4]!="") "•" else ""
            text[5].text=if (array[5]!="") "•" else ""
        }
    }

    fun paymenQris() {
        val sof: String? by lazy { intent.getStringExtra("sof") }
        val amount: String? by lazy { intent.getStringExtra("amount") }
        val amountTips: String? by lazy { intent.getStringExtra("amountTips") }
        val reffFlag: String? by lazy { intent.getStringExtra("reffFlag") }
//        val pinToken: String? by lazy { intent.getStringExtra("pinToken") }
        progressDialog.setTitle("Mohon Menunggu")
        progressDialog.setMessage("Sedang Memuat ...")
        progressDialog.setCancelable(false) // blocks UI interaction
        progressDialog.show()
//        FinpaySDK.authPin(
//    this@PinActivity,
//            amount!!, ProductCode.QRIS,{
//                    val intent = Intent(this@PinActivity, PaymentActivity::class.java)
//                    intent.putExtra("sof", "mc")
//                    intent.putExtra("amount", "0")
//                    intent.putExtra("amountTips", "0")
//                    intent.putExtra("reffFlag", reffFlag)
//                    intent.putExtra("widgetUrl", it.widgetURL)
//                    startActivity(intent)
//                }, {
//                    progressDialog.dismiss()
//                    println(it)
//                    Toast.makeText(this@PinActivity, it, Toast.LENGTH_LONG)
//                }
//            )
            FinpaySDK.qrisPayment(
                java.util.UUID.randomUUID().toString(),
                this@PinActivity,
                sof!!,
                amount!!,
                amountTips!!,
                reffFlag!!,
                pin[0] + pin[1] + pin[2] + pin[3] + pin[4] + pin[5], {
                    progressDialog.dismiss()
                    val intent = Intent(this@PinActivity, TransactionDetailQrisActivity::class.java)
                    intent.putExtra("transNumber", transNumber!!)
                    intent.putExtra("theme", finpayTheme)
                    startActivity(intent)
                }, {
                    progressDialog.dismiss()
                    println(it)
                    Toast.makeText(this@PinActivity, it, Toast.LENGTH_LONG)
                }
            )
    }

    fun otpConnectAccount() {
        val phoneNumber: String? by lazy { intent.getStringExtra("phoneNumber") }
        val custName: String? by lazy { intent.getStringExtra("custName") }
        val custStatusCode: String? by lazy { intent.getStringExtra("custStatusCode") }
        val transNumber: String? by lazy { intent.getStringExtra("transNumber") }

        progressDialog.setTitle("Mohon Menunggu")
        progressDialog.setMessage("Sedang Memuat ...")
        progressDialog.setCancelable(false) // blocks UI interaction
        progressDialog.show()
        FinpaySDK.reqConfirmation(
            this@PinActivity,
            phoneNumber!!,
            transNumber!!,
            custName!!,
            pin[0]+pin[1]+pin[2]+pin[3]+pin[4]+pin[5],
            custStatusCode!!, {
                DialogUtils.showDialogSuccess(
                    this@PinActivity, "Pairing Successful",
                    "Pairing account successful",
                    {
                        progressDialog.dismiss()
                        this@PinActivity.finish()
                        FinpaySDK.prefHelper.setBooleanToShared(SharedPrefKeys.IS_CONNECT, true)
                    },
                    finpayTheme
                )
            }, {
                DialogUtils.showDialogError(this, "", it, finpayTheme)
                progressDialog.dismiss()
            }
        )
    }
}