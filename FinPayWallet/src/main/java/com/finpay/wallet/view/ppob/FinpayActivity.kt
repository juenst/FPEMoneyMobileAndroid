package com.finpay.wallet.view.ppob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.finpay.wallet.R

class FinpayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finpay)

        val backButton = findViewById<ImageView>(R.id.btnBack)

        backButton.setOnClickListener{
            onBackPressed()
        }
    }
}