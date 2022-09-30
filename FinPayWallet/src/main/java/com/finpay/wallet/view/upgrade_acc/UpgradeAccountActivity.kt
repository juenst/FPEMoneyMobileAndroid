package com.finpay.wallet.view.upgrade_acc

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.finpay.wallet.R
import com.shuhart.stepview.StepView

class UpgradeAccountActivity : AppCompatActivity() {
    private lateinit var appbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_account)
        appbar = findViewById(R.id.appbar_main)
        appbarSettings(appbar)
    }

    private fun appbarSettings(appbar: Toolbar) {
        appbar.setTitleTextColor(Color.parseColor("#182156"))
        appbar.setBackgroundColor(Color.parseColor("#F3F4F9"))
        setSupportActionBar(appbar)
        supportActionBar?.title = "Hello World From Upgrade"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
    }
}