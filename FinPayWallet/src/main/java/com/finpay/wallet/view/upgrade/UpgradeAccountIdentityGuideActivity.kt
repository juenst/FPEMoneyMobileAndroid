package com.finpay.wallet.view.upgrade

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R


class UpgradeAccountIdentityGuideActivity : AppCompatActivity() {
    private lateinit var btnBack: ImageView
    private lateinit var btnNext: Button
    var activity: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_account_identity_guide)
        supportActionBar!!.hide()


        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)
        activity = this

        btnBack.setOnClickListener {
            onBackPressed()
        }

        btnNext.setOnClickListener{
            val intent = Intent(this, UpgradeAccountIdentityCameraActivity::class.java)
            startActivity(intent)
        }

    }


}