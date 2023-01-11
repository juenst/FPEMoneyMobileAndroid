package lib.finpay.sdk.uikit.view.topup

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.TopupInquiry
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.view.topup.adapter.TopupSubPaymentAdapter


class TopupGuidanceActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    lateinit var btnBack: ImageView
    lateinit var listOutlet: ListView
    lateinit var listAtm: ListView
    lateinit var listMbanking: ListView
    lateinit var listIbanking: ListView
    lateinit var paymentCodes: TextView
    lateinit var expiredTimes: TextView
    lateinit var outletName:TextView

    lateinit var cardOutlet:androidx.cardview.widget.CardView
    lateinit var cardAtm:androidx.cardview.widget.CardView
    lateinit var cardMbangking:androidx.cardview.widget.CardView
    lateinit var cardOIBanking:androidx.cardview.widget.CardView

    val result: TopupInquiry? by lazy { if(intent.getSerializableExtra("result") == null) null else intent.getSerializableExtra("result") as TopupInquiry }
    val transNumber: String? by lazy { intent.getStringExtra("transNumber")}
    val type: String? by lazy { intent.getStringExtra("type")}
    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topup_guidance)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        btnBack = findViewById(R.id.btnBack)
        paymentCodes = findViewById(R.id.payment_code)
        expiredTimes = findViewById(R.id.expired_time)

        cardAtm = findViewById(R.id.atm_dropdown)
        cardOutlet = findViewById(R.id.outlet_dropdown)
        cardMbangking = findViewById(R.id.mbanking_dropdown)
        cardOIBanking = findViewById(R.id.ibanking_dropdown)

        listOutlet = findViewById(R.id.list_sub_outlet)
        listAtm = findViewById(R.id.list_sub_atm)
        listMbanking = findViewById(R.id.list_sub_mbanking)
        listIbanking = findViewById(R.id.list_sub_ibanking)
        outletName = findViewById(R.id.outlet_title)
//        listPaymentMethods = findViewById(R.id.list_payment_method)

        if (type == "BNI"){

        }else if (type == "Pos Indonesia"){
            if (result!!.guidence!!.post!! != null){
                if (result!!.guidence!!.post!!.outlet!! != null){
                    if (result!!.guidence!!.post!!.outlet!!.isEmpty()){
                        println("OUTLET KOSONG")
                        cardOutlet.setVisibility(View.GONE)
                        listOutlet.setVisibility(View.GONE)
                    }else{
                        println("OUTLET TIDAK KOSONG")
                        cardOutlet.setVisibility(View.VISIBLE)
                        listOutlet.setVisibility(View.VISIBLE)
                        listOutlet.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.post!!.outlet!!)
                        justifyListViewHeightBasedOnChildren(listOutlet)
                        outletName.text = type
                    }
                }else{
                    println("OUTLET KOSONG")
                    cardOutlet.setVisibility(View.GONE)
                }
                if (result!!.guidence!!.post!!.atm!! != null){
                    if (result!!.guidence!!.post!!.atm!!.isEmpty()){
                        println("ATM  KOSONG")
                        cardAtm.setVisibility(View.GONE)
                    }else{
                        println("ATM TIDAK KOSONG")
                        cardAtm.setVisibility(View.VISIBLE)
                        listAtm.setVisibility(View.VISIBLE)
                        listAtm.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.post!!.atm!!)
                        justifyListViewHeightBasedOnChildren(listAtm)
                    }
                }else{
                    println("ATM KOSONG")
                    cardAtm.setVisibility(View.GONE)
                }
                if (result!!.guidence!!.post!!.mbanking!! != null){
                    if (result!!.guidence!!.post!!.mbanking!!.isEmpty()){
                        println("MBANKING KOSONG")
                        cardMbangking.setVisibility(View.GONE)
                    }else{
                        println("MBANKING TIDAK KOSONG")
                        cardMbangking.setVisibility(View.VISIBLE)
                        listMbanking.setVisibility(View.VISIBLE)
                        listMbanking.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.post!!.mbanking!!)
                        justifyListViewHeightBasedOnChildren(listMbanking)
                    }
                }else{
                    println("MBANKING KOSONG")
                    cardMbangking.setVisibility(View.GONE)
                }
                if (result!!.guidence!!.post!!.ibanking!! != null){
                    if (result!!.guidence!!.post!!.ibanking!!.isEmpty()){
                        println("IBANKING KOSONG")
                        cardOIBanking.setVisibility(View.GONE)
                    }else{
                        println("IBANKING TIDAK KOSONG")
                        cardOIBanking.setVisibility(View.VISIBLE)
                        listIbanking.setVisibility(View.VISIBLE)
                        listIbanking.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.post!!.ibanking!!)
                        justifyListViewHeightBasedOnChildren(listIbanking)
                    }
                }else{
                    println("IBANKING KOSONG")
                    cardOIBanking.setVisibility(View.GONE)
                }
            }
        }else if (type == "Alfamart"){
            if (result!!.guidence!!.alfamarts!! != null){
                if (result!!.guidence!!.alfamarts!!.outlet!! != null){
                    if (result!!.guidence!!.alfamarts!!.outlet!!.isEmpty()){
                        println("OUTLET KOSONG")
                        cardOutlet.setVisibility(View.GONE)
                    }else{
                        println("OUTLET TIDAK KOSONG")
                        cardOutlet.setVisibility(View.VISIBLE)
                        listOutlet.setVisibility(View.VISIBLE)
                        listOutlet.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.alfamarts!!.outlet!!)
                        justifyListViewHeightBasedOnChildren(listOutlet)
                        outletName.text = type

                        outletName.text = type
                    }
                }else{
                    println("OUTLET KOSONG")
                    cardOutlet.setVisibility(View.GONE)
                }
                if (result!!.guidence!!.alfamarts!!.atm!! != null){
                    if (result!!.guidence!!.alfamarts!!.atm!!.isEmpty()){
                        println("ATM  KOSONG")
                        cardAtm.setVisibility(View.GONE)
                    }else{
                        println("ATM TIDAK KOSONG")
                        cardAtm.setVisibility(View.VISIBLE)
                        listAtm.setVisibility(View.VISIBLE)
                        listAtm.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.alfamarts!!.atm!!)
                        justifyListViewHeightBasedOnChildren(listAtm)
                    }
                }else{
                    println("ATM KOSONG")
                    cardAtm.setVisibility(View.GONE)
                }
                if (result!!.guidence!!.alfamarts!!.mbanking!! != null){
                    if (result!!.guidence!!.alfamarts!!.mbanking!!.isEmpty()){
                        println("MBANKING KOSONG")
                        cardMbangking.setVisibility(View.GONE)
                    }else{
                        println("MBANKING TIDAK KOSONG")
                        cardMbangking.setVisibility(View.VISIBLE)
                        listMbanking.setVisibility(View.VISIBLE)
                        listMbanking.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.alfamarts!!.mbanking!!)
                        justifyListViewHeightBasedOnChildren(listMbanking)
                    }
                }else{
                    println("MBANKING KOSONG")
                    cardMbangking.setVisibility(View.GONE)
                }
                if (result!!.guidence!!.alfamarts!!.ibanking!! != null){
                    if (result!!.guidence!!.alfamarts!!.ibanking!!.isEmpty()){
                        println("IBANKING KOSONG")
                        cardOIBanking.setVisibility(View.GONE)
                    }else{
                        println("IBANKING TIDAK KOSONG")
                        cardOIBanking.setVisibility(View.VISIBLE)
                        listIbanking.setVisibility(View.VISIBLE)
                        listIbanking.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.alfamarts!!.ibanking!!)
                        justifyListViewHeightBasedOnChildren(listIbanking)
                    }
                }else{
                    println("IBANKING KOSONG")
                    cardOIBanking.setVisibility(View.GONE)
                }
            }
        }else if (type == "Mandiri"){
            if (result!!.guidence!!.vamandiris!! != null){
                cardOutlet.setVisibility(View.GONE)
                if (result!!.guidence!!.vamandiris!!.atm!! != null){
                    if (result!!.guidence!!.vamandiris!!.atm!!.isEmpty()){
                        println("ATM MANDIRI KOSONG 1")
                        cardAtm.setVisibility(View.GONE)
                    }else{
                        println("ATM TIDAK KOSONG")
                        cardAtm.setVisibility(View.VISIBLE)
                        listAtm.setVisibility(View.VISIBLE)
                        listAtm.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.vamandiris!!.atm!!)
                        justifyListViewHeightBasedOnChildren(listAtm)
                    }
                }else{
                    println("ATM MANDIRI KOSONG 2")
                    cardAtm.setVisibility(View.GONE)
                }
                if (result!!.guidence!!.vamandiris!!.mbanking!! != null){
                    if (result!!.guidence!!.vamandiris!!.mbanking!!.isEmpty()){
                        println("MBANKING KOSONG")
                        cardMbangking.setVisibility(View.GONE)
                    }else{
                        println("MBANKING TIDAK KOSONG")
                        cardMbangking.setVisibility(View.VISIBLE)
                        listMbanking.setVisibility(View.VISIBLE)
                        listMbanking.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.vamandiris!!.mbanking!!)
                        justifyListViewHeightBasedOnChildren(listMbanking)
                    }
                }else{
                    println("MBANKING KOSONG")
                    cardMbangking.setVisibility(View.GONE)
                }
                if (result!!.guidence!!.vamandiris!!.ibanking!! != null){
                    if (result!!.guidence!!.vamandiris!!.ibanking!!.isEmpty()){
                        println("IBANKING KOSONG")
                        cardOIBanking.setVisibility(View.GONE)
                    }else{
                        println("IBANKING TIDAK KOSONG")
                        cardOIBanking.setVisibility(View.VISIBLE)
                        listIbanking.setVisibility(View.VISIBLE)
                        listIbanking.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.vamandiris!!.ibanking!!)
                        justifyListViewHeightBasedOnChildren(listIbanking)
                    }
                }else{
                    println("IBANKING KOSONG")
                    cardOIBanking.setVisibility(View.GONE)
                }
            }
        }else if (type == "BRIVA"){
            if (result!!.guidence!!.brivas!! != null){
                cardOutlet.setVisibility(View.GONE)
                if (result!!.guidence!!.brivas!!.atm!! != null){
                    if (result!!.guidence!!.brivas!!.atm!!.isEmpty()){
                        println("ATM  KOSONG")
                        cardAtm.setVisibility(View.GONE)
                    }else{
                        println("ATM TIDAK KOSONG")
                        cardAtm.setVisibility(View.VISIBLE)
                        listAtm.setVisibility(View.VISIBLE)
                        listAtm.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.brivas!!.atm!!)
                        justifyListViewHeightBasedOnChildren(listAtm)
                    }
                }else{
                    println("ATM KOSONG")
                    cardAtm.setVisibility(View.GONE)
                }
                if (result!!.guidence!!.brivas!!.mbanking!! != null){
                    if (result!!.guidence!!.brivas!!.mbanking!!.isEmpty()){
                        println("MBANKING KOSONG")
                        cardMbangking.setVisibility(View.GONE)
                    }else{
                        println("MBANKING TIDAK KOSONG")
                        cardMbangking.setVisibility(View.VISIBLE)
                        listMbanking.setVisibility(View.VISIBLE)
                        listMbanking.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.brivas!!.mbanking!!)
                        justifyListViewHeightBasedOnChildren(listMbanking)
                    }
                }else{
                    println("MBANKING KOSONG")
                    cardMbangking.setVisibility(View.GONE)
                }
                if (result!!.guidence!!.brivas!!.ibanking!! != null){
                    if (result!!.guidence!!.brivas!!.ibanking!!.isEmpty()){
                        println("IBANKING KOSONG")
                        cardOIBanking.setVisibility(View.GONE)
                    }else{
                        println("IBANKING TIDAK KOSONG")
                        cardOIBanking.setVisibility(View.VISIBLE)
                        listIbanking.setVisibility(View.VISIBLE)
                        listIbanking.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.brivas!!.ibanking!!)
                        justifyListViewHeightBasedOnChildren(listIbanking)
                    }
                }else{
                    println("IBANKING KOSONG")
                    cardOIBanking.setVisibility(View.GONE)
                }
            }
        }else if (type == "Bank Central Asia"){
            if (result!!.guidence!!.vabcas!! != null){
                cardOutlet.setVisibility(View.GONE)
                if (result!!.guidence!!.vabcas!!.atm!! != null){
                    if (result!!.guidence!!.vabcas!!.atm!!.isEmpty()){
                        println("ATM  KOSONG")
                        cardAtm.setVisibility(View.GONE)
                    }else{
                        println("ATM TIDAK KOSONG")
                        cardAtm.setVisibility(View.VISIBLE)
                        listAtm.setVisibility(View.VISIBLE)
                        listAtm.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.vabcas!!.atm!!)
                        justifyListViewHeightBasedOnChildren(listAtm)
                    }
                }else{
                    println("ATM KOSONG")
                    cardAtm.setVisibility(View.GONE)
                }
                if (result!!.guidence!!.vabcas!!.mbanking!! != null){
                    if (result!!.guidence!!.vabcas!!.mbanking!!.isEmpty()){
                        println("MBANKING KOSONG")
                        cardMbangking.setVisibility(View.GONE)
                    }else{
                        println("MBANKING TIDAK KOSONG")
                        cardMbangking.setVisibility(View.VISIBLE)
                        listMbanking.setVisibility(View.VISIBLE)
                        listMbanking.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.vabcas!!.mbanking!!)
                        justifyListViewHeightBasedOnChildren(listMbanking)
                    }
                }else{
                    println("MBANKING KOSONG")
                    cardMbangking.setVisibility(View.GONE)
                }
                if (result!!.guidence!!.vabcas!!.ibanking!! != null){
                    if (result!!.guidence!!.vabcas!!.ibanking!!.isEmpty()){
                        println("IBANKING KOSONG")
                        cardOIBanking.setVisibility(View.GONE)
                    }else{
                        println("IBANKING TIDAK KOSONG")
                        cardOIBanking.setVisibility(View.VISIBLE)
                        listIbanking.setVisibility(View.VISIBLE)
                        listIbanking.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.vabcas!!.ibanking!!)
                        justifyListViewHeightBasedOnChildren(listIbanking)
                    }
                }else{
                    println("IBANKING KOSONG")
                    cardOIBanking.setVisibility(View.GONE)
                }
            }
        }else if (type == "Pegadaian"){
            if (result!!.guidence!!.pegadaians!! != null){
                if (result!!.guidence!!.pegadaians!!.outlet!! != null){
                    if (result!!.guidence!!.pegadaians!!.outlet!!.isEmpty()){
                        println("OUTLET KOSONG")
                        cardOutlet.setVisibility(View.GONE)
                    }else{
                        println("OUTLET TIDAK KOSONG")
                        cardOutlet.setVisibility(View.VISIBLE)
                        listOutlet.setVisibility(View.VISIBLE)
                        listOutlet.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.pegadaians!!.outlet!!)
                        justifyListViewHeightBasedOnChildren(listOutlet)
                        outletName.text = type
                    }
                }else{
                    println("OUTLET KOSONG")
                    cardOutlet.setVisibility(View.GONE)
                }
                if (result!!.guidence!!.pegadaians!!.atm!! != null){
                    if (result!!.guidence!!.pegadaians!!.atm!!.isEmpty()){
                        println("ATM  KOSONG")
                        cardAtm.setVisibility(View.GONE)
                    }else{
                        println("ATM TIDAK KOSONG")
                        cardAtm.setVisibility(View.VISIBLE)
                        listAtm.setVisibility(View.VISIBLE)
                        listAtm.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.pegadaians!!.atm!!)
                        justifyListViewHeightBasedOnChildren(listAtm)
                    }
                }else{
                    println("ATM KOSONG")
                    cardAtm.setVisibility(View.GONE)
                }
                if (result!!.guidence!!.pegadaians!!.mbanking!! != null){
                    if (result!!.guidence!!.pegadaians!!.mbanking!!.isEmpty()){
                        println("MBANKING KOSONG")
                        cardMbangking.setVisibility(View.GONE)
                    }else{
                        println("MBANKING TIDAK KOSONG")
                        cardMbangking.setVisibility(View.VISIBLE)
                        listMbanking.setVisibility(View.VISIBLE)
                        listMbanking.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.pegadaians!!.mbanking!!)
                        justifyListViewHeightBasedOnChildren(listMbanking)
                    }
                }else{
                    println("MBANKING KOSONG")
                    cardMbangking.setVisibility(View.GONE)
                }
                if (result!!.guidence!!.pegadaians!!.ibanking!! != null){
                    if (result!!.guidence!!.pegadaians!!.ibanking!!.isEmpty()){
                        println("IBANKING KOSONG")
                        cardOIBanking.setVisibility(View.GONE)
                    }else{
                        println("IBANKING TIDAK KOSONG")
                        cardOIBanking.setVisibility(View.VISIBLE)
                        listIbanking.setVisibility(View.VISIBLE)
                        listIbanking.adapter = TopupSubPaymentAdapter(this,R.layout.item_sub_payment_method,result!!.guidence!!.pegadaians!!.ibanking!!)
                        justifyListViewHeightBasedOnChildren(listIbanking)
                    }
                }else{
                    println("IBANKING KOSONG")
                    cardOIBanking.setVisibility(View.GONE)
                }
            }
        }else if (type == "Permata Bank"){

        }else if (type == "BNC Bank"){

        }


        appbarTitle.text = type
        paymentCodes.text = result!!.paymentCode!!
        expiredTimes.text = result!!.expiryDtime

        paymentCodes.setOnClickListener{
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", paymentCodes.text)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(this,"Berhasil disalin",Toast.LENGTH_SHORT).show()
        }


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

        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    fun justifyListViewHeightBasedOnChildren(listView: ListView,) {
        val adapter = listView.adapter ?: return
        val vg: ViewGroup = listView
        var totalHeight = 0
        for (i in 0 until adapter.count) {
            val listItem = adapter.getView(i, null, vg)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }
        val par = listView.layoutParams
        par.height = totalHeight + listView.dividerHeight * (adapter.count - 1)
        listView.layoutParams = par
        listView.requestLayout()
    }
}