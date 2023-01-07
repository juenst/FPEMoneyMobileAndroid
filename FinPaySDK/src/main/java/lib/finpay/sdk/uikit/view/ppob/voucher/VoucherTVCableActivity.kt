package lib.finpay.sdk.uikit.view.ppob.voucher

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.view.AppActivity

class VoucherTVCableActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    lateinit var btnBack: ImageView
    lateinit var lnKvision: LinearLayout

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet_tv_cable)

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        btnBack = findViewById(R.id.btnBack)
        lnKvision = findViewById(R.id.lnKvision)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)

        btnBack.setOnClickListener {
            onBackPressed()
        }

        lnKvision.setOnClickListener {
            val intent = Intent(this, KvisionActivity::class.java)
            startActivity(intent)
        }
    }
}