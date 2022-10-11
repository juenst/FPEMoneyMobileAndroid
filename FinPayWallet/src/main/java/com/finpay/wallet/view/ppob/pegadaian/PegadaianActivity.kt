package com.finpay.wallet.view.ppob.pegadaian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.finpay.wallet.R

class PegadaianActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pegadaian)

        val backButton = findViewById<ImageView>(R.id.btnBack)

        backButton.setOnClickListener{
            onBackPressed()
        }
    }
}