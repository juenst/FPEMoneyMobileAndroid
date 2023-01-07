package lib.finpay.sdk.uikit.view.ppob.telkomsel

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.helper.FinpayTheme

class BestTelkomselPackageActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    lateinit var btnBack: ImageView
    private lateinit var btnPaymentCodePay: LinearLayout

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_paket_terbaik_telkomsel)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        btnBack = findViewById(R.id.btnBack)
        btnPaymentCodePay = findViewById(R.id.btn_payment_code_pay)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)

        btnBack.setOnClickListener {
            finish()
        }

        btnPaymentCodePay.setOnClickListener {
            val intent = Intent(this, BestTelkomselPackageDetailActivity::class.java)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            this.startActivity(intent)
        }
    }
}