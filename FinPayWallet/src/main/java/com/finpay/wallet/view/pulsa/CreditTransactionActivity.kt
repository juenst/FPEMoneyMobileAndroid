package com.finpay.wallet.view.pulsa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.finpay.wallet.R

class CreditTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit_transaction)
        supportActionBar!!.hide()
    }
}