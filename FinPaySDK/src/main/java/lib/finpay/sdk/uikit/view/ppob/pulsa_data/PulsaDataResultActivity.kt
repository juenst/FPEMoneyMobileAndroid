package lib.finpay.sdk.uikit.view.ppob.pulsa_data

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
import lib.finpay.sdk.corekit.helper.DateHelper
import lib.finpay.sdk.corekit.model.DataSubProduct
import lib.finpay.sdk.corekit.model.SubProduct
import lib.finpay.sdk.uikit.constant.PaymentType
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.TextUtils
import lib.finpay.sdk.uikit.utilities.Utils
import lib.finpay.sdk.uikit.view.payment.PaymentActivity
import lib.finpay.sdk.uikit.view.ppob.pulsa_data.adapter.PulsaDataAdapter
import java.text.SimpleDateFormat
import java.util.*


class PulsaDataResultActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    lateinit var txtPhoneNumber: TextView
    lateinit var btnNext: Button
    lateinit var btnBack: ImageView
    lateinit var listDenom: GridView
    lateinit var cardPulsa: CardView
    lateinit var cardData: CardView
    lateinit var logo: ImageView
    lateinit var progressDialog: ProgressDialog
    lateinit var content: LinearLayout
    lateinit var emptyState: LinearLayout

    var dataSubProduct = mutableListOf<DataSubProduct>()
    var denom: String = "0"
    var price: String = "0"
    var saldo: String = "0"
    var fee: String = "0"
    var subProductCode: String = ""

    val phoneNumber: String? by lazy { intent.getStringExtra("phoneNumber") }
    val result: SubProduct? by lazy { if(intent.getSerializableExtra("result") == null) null else intent.getSerializableExtra("result") as SubProduct }
    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulsa_data_result)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber)
        btnNext = findViewById(R.id.btnNext)
        logo = findViewById(R.id.logoProvider)
        listDenom = findViewById(R.id.listDenom)
        cardPulsa = findViewById(R.id.cardPulsa)
        cardData = findViewById(R.id.cardData)
        content = findViewById(R.id.lnContent)
        btnBack = findViewById(R.id.btnBack)
        emptyState = findViewById(R.id.emptyState)
        progressDialog = ProgressDialog(this@PulsaDataResultActivity)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnNext.setBackgroundColor(if(btnNext.isEnabled()) if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!! else Color.parseColor("#d5d5d5"))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if(finpayTheme?.getAppBarBackgroundColor() == null) {
                window.setStatusBarColor(Color.parseColor("#333333"))
            } else {
                window.setStatusBarColor(finpayTheme?.getAppBarBackgroundColor()!!)
            }
        }

        cardPulsa.setBackgroundColor(Integer.parseUnsignedInt("FFFFFFFF", 16))
        cardData.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))

        cardPulsa.setOnClickListener {
            cardPulsa.setBackgroundColor(Integer.parseUnsignedInt("FFFFFFFF", 16))
            cardData.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))
        }

        cardData.setOnClickListener {
            DialogUtils.showDialogComingSoon(this, finpayTheme)
//            cardPulsa.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))
//            cardData.setBackgroundColor(Integer.parseUnsignedInt("FFFFFFFF", 16))
        }

        ButtonUtils.checkButtonState(btnNext, finpayTheme)

        val provider: String = Utils.getProviderMobile(phoneNumber!!)
        if(provider == "TELKOMSEL") {
            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.ic_logo_telkomsel))
        } else if(provider == "XL") {
            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.ic_logo_xl))
        } else if(provider == "AXIS") {
            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.ic_logo_axis))
        } else if(provider == "INDOSAT") {
            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.ic_logo_indosat))
        } else if(provider == "THREE") {
            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.ic_logo_three))
        } else {
            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.transparent))
        }

        listDenom.adapter = PulsaDataAdapter(this, R.layout.item_pulsa_data, result?.dataSubProduct!!)
        if(result?.dataSubProduct!!.isEmpty() || result?.dataSubProduct!!.count() == 0) {
            content.visibility = View.GONE
            emptyState.visibility = View.VISIBLE
        } else {
            content.visibility = View.VISIBLE
            emptyState.visibility = View.GONE
        }

        listDenom.setOnItemClickListener{adapter, view, position, id ->
            val selectedItem = adapter.getItemAtPosition(position) as DataSubProduct

            subProductCode = selectedItem.provider.split("/")[1]
            denom = selectedItem.denom.split("/")[0]
            price = selectedItem.denom.split("/")[1]

//            for ((index, value) in dataSubProduct.withIndex()) {
//                listDenom.getChildAt(index).setBackgroundColor(Color.TRANSPARENT)
//            }
            for (i in 0 until adapter.childCount) {
                listDenom.getChildAt(i).setBackgroundColor(Color.TRANSPARENT)
            }
            listDenom.getChildAt(position).setBackgroundColor(Color.parseColor("#EEEEEE"))

            btnNext.isEnabled = subProductCode != null
            ButtonUtils.checkButtonState(btnNext, finpayTheme)
        }

        btnNext.setOnClickListener {
            DialogUtils.showDialogConfirmPayment(
                this,
                transNumber!!,
                "Pembelian",
                "Pembelian Pulsa/Data",
                saldo,
                price,
                fee,
                {
                    progressDialog.setTitle("Mohon Menunggu")
                    progressDialog.setMessage("Sedang Memuat ...")
                    progressDialog.setCancelable(false) // blocks UI interaction
                    progressDialog.show()
//                    FinpaySDK.ppobInquiry(
//                        transNumber!!,
//                        this,
//                        phoneNumber!!,
//                        subProductCode,
//                        "",
//                        {
//                            progressDialog.dismiss()
//                            println("success")
//                        },
//                        {
//                            progressDialog.dismiss()
//                            println("failed")
//                        },
//                    )
                    FinpaySDK.authPin(
                        transNumber!!,
                        this@PulsaDataResultActivity,
                        price, subProductCode,{
                            progressDialog.dismiss()
                            val intent = Intent(this@PulsaDataResultActivity, PaymentActivity::class.java)
                            intent.putExtra("paymentType", PaymentType.paymentPPOB)
                            intent.putExtra("amount", (price.toInt() + fee.toInt()).toString())
                            intent.putExtra("denom", denom)
                            intent.putExtra("reffFlag", "")
                            intent.putExtra("billingId", phoneNumber)
                            intent.putExtra("productCode", subProductCode)
                            intent.putExtra("phoneNumber", phoneNumber)
                            intent.putExtra("price", price)
                            intent.putExtra("fee", fee)
                            intent.putExtra("pinResult", it)
                            intent.putExtra("result", result)
                            intent.putExtra("transactionType", "Pembelian Pulsa/Data")
                            intent.putExtra("transNumber", transNumber!!)
                            intent.putExtra("theme", finpayTheme)
                            startActivity(intent)
                        }, {
                            progressDialog.dismiss()
                            DialogUtils.showDialogError(this@PulsaDataResultActivity, "", it, finpayTheme)
                        }
                    )
                },
                finpayTheme
            )
        }

        txtPhoneNumber.text = "Topup ke nomor "+phoneNumber
        getUserBallance()
    }

    fun getUserBallance() {
        FinpaySDK.getUserBallance(transNumber!!,this@PulsaDataResultActivity, {
            saldo = it.amount!!
        },{
            Toast.makeText(this@PulsaDataResultActivity, it, Toast.LENGTH_LONG)
        })
    }
}