package com.finpay.wallet.view.instalment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.finpay.wallet.R

class InstalmentTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instalment_transaction)
        supportActionBar!!.hide()
    }
}