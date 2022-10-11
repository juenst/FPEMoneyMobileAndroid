package com.finpay.wallet.view.upgrade

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R


class UpgradeAccountSuccessActivity : AppCompatActivity() {
    private lateinit var btnClose: ImageView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_account_success)
        supportActionBar!!.hide()

        btnClose = findViewById(R.id.btnClose)
        btnBack = findViewById(R.id.btnBack)

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