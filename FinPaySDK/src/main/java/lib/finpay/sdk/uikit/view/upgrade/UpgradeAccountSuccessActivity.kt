package lib.finpay.sdk.uikit.view.upgrade

import android.content.Intent
import android.graphics.BitmapFactory
import android.media.Image
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R


class UpgradeAccountSuccessActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    private lateinit var btnClose: ImageView
    private lateinit var btnBack: Button
    private lateinit var imgSuccess: ImageView

    val transNumber: String? by lazy { intent.getStringExtra("transNumber")}
    val base64String: String? by lazy { intent.getStringExtra("base64String")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_account_success)
        supportActionBar!!.hide()

        btnClose = findViewById(R.id.btnClose)
        btnBack = findViewById(R.id.btnBack)
        imgSuccess = findViewById(R.id.imgSuccess)

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