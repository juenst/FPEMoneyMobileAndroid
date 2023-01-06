package lib.finpay.sdk.uikit.view.upgrade

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.helper.FinpayTheme


class UpgradeAccountSelfieGuideActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    private lateinit var btnBack: ImageView
    private lateinit var btnNext: Button

    val imgResultIdentity: String? by lazy { intent.getStringExtra("imgResultIdentity") }
    val imgResultIdentityBase64: String? by lazy { intent.getStringExtra("imgResultIdentityBase64") }
    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_account_selfie_guide)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnNext.setBackgroundColor(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)

        btnBack.setOnClickListener {
            onBackPressed()
        }

        btnNext.setOnClickListener{
            val intent = Intent(this, UpgradeAccountSelfieCameraActivity::class.java)
            intent.putExtra("imgResultIdentity", imgResultIdentity)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            startActivity(intent)
            finish()
        }

    }


}