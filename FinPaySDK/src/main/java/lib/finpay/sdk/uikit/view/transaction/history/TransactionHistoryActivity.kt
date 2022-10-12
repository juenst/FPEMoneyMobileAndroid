package lib.finpay.sdk.uikit.view.transaction.history

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.DetailHistoryTransactionModel
import lib.finpay.sdk.uikit.view.transaction.history.adapter.TransactionHistoryAdapter

class TransactionHistoryActivity : AppCompatActivity() {
    lateinit var listHistoryTransaction: ListView
    lateinit var emptyState: LinearLayout
    lateinit var btnBack: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_transaction)
        supportActionBar!!.hide()

        listHistoryTransaction = findViewById(R.id.list_history_transaction)
        emptyState = findViewById(R.id.emptyState)
        btnBack = findViewById(R.id.btnBack)

        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener{
            finish()
        }

        var list = mutableListOf<DetailHistoryTransactionModel>()
//        DetailHistoryTransactionModel().setDateTime("15-09-2022 18:25:25")
//        DetailHistoryTransactionModel().setTypes("pay")
//        DetailHistoryTransactionModel().setDesc("Pembelian Pulsa")
//        DetailHistoryTransactionModel().setValues("50000")

        list.add(DetailHistoryTransactionModel())

        if(list.isEmpty() || list.count() == 0) {
            listHistoryTransaction.visibility = View.GONE
            emptyState.visibility = View.VISIBLE
        } else {
            listHistoryTransaction.visibility = View.VISIBLE
            emptyState.visibility = View.GONE
        }
        listHistoryTransaction.adapter = TransactionHistoryAdapter(this, R.layout.item_history_transaction, list)

    }
}