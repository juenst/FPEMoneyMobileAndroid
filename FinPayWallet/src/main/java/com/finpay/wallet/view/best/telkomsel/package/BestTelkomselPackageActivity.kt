package com.finpay.wallet.view.best.telkomsel.`package`

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R

class BestTelkomselPackageActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_paket_terbaik_telkomsel)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }
}