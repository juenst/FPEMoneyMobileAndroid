package com.finpay.wallet.view.upgrade_acc

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.finpay.wallet.R


class UpgradeInformationActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var appbar: Toolbar
    private lateinit var tvUpgradeDesc: TextView
    private lateinit var btnContinueUpgrade: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_information)
        appbar = findViewById(R.id.appbar_main)
        btnContinueUpgrade = findViewById(R.id.btn_continue)
        tvUpgradeDesc = findViewById(R.id.tv_inf_premium_title)

        appbarSettings(appbar)

        btnContinueUpgrade.setOnClickListener(this)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item)
    }



    private fun appbarSettings(appbar: Toolbar) {
        appbar.setTitleTextColor(Color.parseColor("#182156"))
        appbar.setBackgroundColor(Color.parseColor("#F3F4F9"))
        setSupportActionBar(appbar)
        supportActionBar?.title = "Hello World From Upgrade"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_continue -> {
                val moveIntent =
                    Intent(this@UpgradeInformationActivity, UpgradeAccountActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }

}