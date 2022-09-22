package com.finpay.wallet.view.app.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finpay.wallet.R
import lib.finpay.sdk.FinPaySDK

class HistoryTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_transaction)

        val userName: String = "MT77764DKM83N"
        val password: String = "YJV3AM0y"
        val secretKey: String = "daYumnMb"

//        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerViewHistory)
        val loadingBar = findViewById<ProgressBar>(R.id.loadingBar)
        val historyContent = findViewById<LinearLayout>(R.id.historyContent)
        val emptyHistory = findViewById<LinearLayout>(R.id.emptyHistory)

//
//        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)


        FinPaySDK().getHistoryTransaction(
            userName,
            password,
            secretKey,
            "TRX1234567890",
            "083815613839",
            onSuccess = {
                    listData ->
                println("List Data : " + listData.toString())
                val adapter = HistoryAdapter(listData)
                if(adapter!=null){
                    loadingBar.visibility = View.GONE
                }
                if(listData.isNotEmpty()){
                    historyContent.visibility=View.VISIBLE
                    emptyHistory.visibility=View.GONE
                    recyclerview.adapter = adapter
                }else{
                    emptyHistory.visibility=View.VISIBLE
                    historyContent.visibility=View.GONE
                }
            }
        )
    }
}