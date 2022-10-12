package com.finpay.wallet.view.telkom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.finpay.wallet.R

class TelkomTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telkom_transaction)
        supportActionBar!!.hide()
    }
}