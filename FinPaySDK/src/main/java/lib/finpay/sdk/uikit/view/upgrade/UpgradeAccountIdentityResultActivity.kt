package lib.finpay.sdk.uikit.view.upgrade

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.helper.FinpayTheme
import java.io.File


class UpgradeAccountIdentityResultActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    private lateinit var btnBack: ImageView
    private lateinit var btnNext: Button
    private lateinit var btnRetry: Button
    private lateinit var imgResult: ImageView

    val imgResultIdentity: String? by lazy { intent.getStringExtra("imgResultIdentity") }
    val imgResultIdentityBase64: String? by lazy { intent.getStringExtra("imgResultIdentityBase64") }
    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_account_identity_result)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)
        btnRetry = findViewById(R.id.btnRetry)
        imgResult = findViewById(R.id.imgResult)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnNext.setBackgroundColor(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)
        btnRetry.setBackgroundColor(if(finpayTheme?.getSecondaryColor() == null)  Color.parseColor("#CCEEF1") else finpayTheme?.getSecondaryColor()!!)
        btnRetry.setTextColor(if(finpayTheme?.getSecondaryColor() == null)  Color.parseColor("#00ACBA") else Color.parseColor("#FFFFFF"))

        btnBack.setOnClickListener {
            onBackPressed()
        }

        btnNext.setOnClickListener{
            val intent = Intent(this, UpgradeAccountSelfieGuideActivity::class.java)
            intent.putExtra("imgResultIdentity", imgResultIdentity)
            intent.putExtra("imgResultIdentityBase64", imgResultIdentityBase64)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            startActivity(intent)
            finish()
        }

        btnRetry.setOnClickListener {
            val intent = Intent(this, UpgradeAccountIdentityCameraActivity::class.java)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            startActivity(intent)
            this.finish()
        }

        val imgFile = File(imgResultIdentity!!.replace("file://", ""))
        if(imgFile.exists()) {
            val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            imgResult.setImageBitmap(myBitmap)
        }
    }
}