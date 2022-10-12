package com.finpay.wallet.view.transfer

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R

class TransferActivity : AppCompatActivity()  {
    lateinit var btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener{
            finish()
        }
    }
}