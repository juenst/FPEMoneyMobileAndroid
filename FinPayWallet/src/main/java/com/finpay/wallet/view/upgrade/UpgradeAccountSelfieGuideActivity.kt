package com.finpay.wallet.view.upgrade

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R


class UpgradeAccountSelfieGuideActivity : AppCompatActivity() {
    private lateinit var btnBack: ImageView
    private lateinit var btnNext: Button
    var activity: Activity? = null

    val imgResultIdentity: String? by lazy {
        intent.getStringExtra("imgResultIdentity")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_account_selfie_guide)
        supportActionBar!!.hide()

        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)
        activity = this@UpgradeAccountSelfieGuideActivity

        btnBack.setOnClickListener {
            onBackPressed()
        }

        btnNext.setOnClickListener{
            val intent = Intent(this, UpgradeAccountSelfieCameraActivity::class.java)
            intent.putExtra("imgResultIdentity", imgResultIdentity)
            startActivity(intent)
        }

    }


}