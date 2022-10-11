package com.finpay.wallet.view.upgrade

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R
import java.io.File


class UpgradeAccountIdentityResultActivity : AppCompatActivity() {
    private lateinit var btnBack: ImageView
    private lateinit var btnNext: Button
    private lateinit var btnRetry: Button
    private lateinit var imgResult: ImageView
    var activity: Activity? = null

    val imgResultIdentity: String? by lazy {
        intent.getStringExtra("imgResultIdentity")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_account_identity_result)
        supportActionBar!!.hide()


        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)
        btnRetry = findViewById(R.id.btnRetry)
        imgResult = findViewById(R.id.imgResult)
        activity = this@UpgradeAccountIdentityResultActivity

        btnBack.setOnClickListener {
            onBackPressed()
        }

        btnNext.setOnClickListener{
            val intent = Intent(this, UpgradeAccountSelfieGuideActivity::class.java)
            intent.putExtra("imgResultIdentity", imgResultIdentity)
            startActivity(intent)
        }

        btnRetry.setOnClickListener {
            val intent = Intent(this, UpgradeAccountIdentityCameraActivity::class.java)
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