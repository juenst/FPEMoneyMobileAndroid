package lib.finpay.sdk.uikit.view.upgrade

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.helper.FinpayTheme


class UpgradeAccountSuccessActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    private lateinit var btnClose: ImageView
    private lateinit var btnBack: Button
    private lateinit var imgSuccess: ImageView

    val transNumber: String? by lazy { intent.getStringExtra("transNumber")}
    val base64String: String? by lazy { intent.getStringExtra("base64String")}
    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_account_success)
        supportActionBar!!.hide()

        btnClose = findViewById(R.id.btnClose)
        btnBack = findViewById(R.id.btnBack)
        imgSuccess = findViewById(R.id.imgSuccess)
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
//            UpgradeAccountActivity().activity!!.finish()
//            UpgradeAccountIdentityGuideActivity().activity!!.finish()
//            UpgradeAccountIdentityResultActivity().activity!!.finish()
//            UpgradeAccountSelfieGuideActivity().activity!!.finish()
//            UpgradeAccountSelfieResultActivity().activity!!.finish()
//            UpgradeAccountPersonalDataActivity().activity!!.finish()
            this@UpgradeAccountSuccessActivity.finish()

        }

        btnClose.setOnClickListener{

        }

    }
}