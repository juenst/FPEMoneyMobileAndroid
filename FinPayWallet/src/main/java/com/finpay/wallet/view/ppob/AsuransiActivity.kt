package com.finpay.wallet.view.ppob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.finpay.wallet.R

class AsuransiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asuransi)

        val backButton = findViewById<ImageView>(R.id.btnBack)

        backButton.setOnClickListener{
            onBackPressed()
        }
    }
}