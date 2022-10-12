package com.finpay.wallet.view.transaction

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R

class TransactionDetailActivity : AppCompatActivity()  {
    lateinit var btnBack: ImageView
    lateinit var txtTanggal: TextView
    lateinit var txtTotalBayar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_detail)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        txtTanggal = findViewById(R.id.txtTanggal)
        txtTotalBayar = findViewById(R.id.txtTotalBayar)

        btnBack.setOnClickListener{
            finish()
        }
    }

}