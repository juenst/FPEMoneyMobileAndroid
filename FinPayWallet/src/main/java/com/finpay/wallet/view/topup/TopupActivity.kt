package com.finpay.wallet.view.topup

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R
import com.finpay.wallet.view.transaction.history.adapter.TransactionHistoryAdapter
import lib.finpay.sdk.model.DetailHistoryTransactionModel

class TopupActivity : AppCompatActivity()  {
    lateinit var btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topup)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener{
            finish()
        }
    }

}