package com.finpay.wallet.view.app.ui.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R
import com.google.gson.Gson
import lib.finpay.sdk.model.DetailHistoryTransactionModel


class DetailHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_history)

        val gson = Gson()
        val ob: DetailHistoryTransactionModel = gson.fromJson(intent.getStringExtra("dataDetailHistory"), DetailHistoryTransactionModel::class.java)
        println("Data in DetailHistoryActivity " + ob.getDateTime())
    }
}