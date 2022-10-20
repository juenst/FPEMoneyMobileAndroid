package lib.finpay.sdk.uikit.view.upgrade

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import java.io.File


class UpgradeAccountSelfieResultActivity : AppCompatActivity() {
    private lateinit var btnBack: ImageView
    private lateinit var btnNext: Button
    private lateinit var btnRetry: Button
    private lateinit var imgResult: ImageView
    var activity: Activity? = null

    val imgResultIdentity: String? by lazy { intent.getStringExtra("imgResultIdentity") }
    val imgResultSelfie: String? by lazy { intent.getStringExtra("imgResultSelfie") }
    val imgResultIdentityBase64: String? by lazy { intent.getStringExtra("imgResultIdentityBase64") }
    val imgResultSelfieBase64: String? by lazy { intent.getStringExtra("imgResultSelfieBase64") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_account_selfie_result)
        supportActionBar!!.hide()


        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)
        btnRetry = findViewById(R.id.btnRetry)
        imgResult = findViewById(R.id.imgResult)
        activity = this@UpgradeAccountSelfieResultActivity

        btnBack.setOnClickListener {
            onBackPressed()
        }

        btnNext.setOnClickListener{
            val intent = Intent(this, UpgradeAccountPersonalDataActivity::class.java)
            intent.putExtra("imgResultSelfie", imgResultSelfie)
            intent.putExtra("imgResultIdentity", imgResultIdentity)
            intent.putExtra("imgResultIdentityBase64", imgResultIdentityBase64)
            intent.putExtra("imgResultSelfieBase64", imgResultSelfieBase64)
            startActivity(intent)
        }

        btnRetry.setOnClickListener {
            val intent = Intent(this, UpgradeAccountIdentityCameraActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        val imgFile = File(imgResultSelfie!!.replace("file://", ""))
        if(imgFile.exists()) {
            val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            imgResult.setImageBitmap(myBitmap)
        }
    }
}