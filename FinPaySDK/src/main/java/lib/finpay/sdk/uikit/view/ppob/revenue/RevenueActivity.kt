package lib.finpay.sdk.uikit.view.ppob.revenue

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.view.ppob.instalment.BussanAutoFinanceActivity
import lib.finpay.sdk.uikit.view.ppob.instalment.SmartFinanceActivity

class RevenueActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    private lateinit var btnBack: ImageView
    private lateinit var lnPnbp: LinearLayout
    private lateinit var lnBeaCukai: LinearLayout
    private lateinit var lnPajakOnline: LinearLayout

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revenue)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        btnBack = findViewById(R.id.btnBack)
        lnPnbp = findViewById(R.id.lnPnbp)
        lnBeaCukai = findViewById(R.id.lnBeacukai)
        lnPajakOnline = findViewById(R.id.lnPajakOnline)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)

        btnBack.setOnClickListener {
            onBackPressed()
        }

        lnPnbp.setOnClickListener {
            val intent = Intent(this, PnbpActivity::class.java)
            startActivity(intent)
        }

        lnBeaCukai.setOnClickListener {
            val intent = Intent(this, BeaCukaiActivity::class.java)
            startActivity(intent)
        }

        lnPajakOnline.setOnClickListener {
            val intent = Intent(this, PajakOnlineActivity::class.java)
            startActivity(intent)
        }
    }
}