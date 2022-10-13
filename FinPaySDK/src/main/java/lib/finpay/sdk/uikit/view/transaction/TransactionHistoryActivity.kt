package lib.finpay.sdk.uikit.view.transaction

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.uikit.view.transaction.adapter.TransactionHistoryAdapter

class TransactionHistoryActivity : AppCompatActivity() {
    lateinit var listHistoryTransaction: ListView
    lateinit var emptyState: LinearLayout
    lateinit var btnBack: ImageView
    lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_transaction)
        supportActionBar!!.hide()

        listHistoryTransaction = findViewById(R.id.list_history_transaction)
        emptyState = findViewById(R.id.emptyState)
        btnBack = findViewById(R.id.btnBack)
        progressDialog = ProgressDialog(this@TransactionHistoryActivity)

        btnBack.setOnClickListener{
            finish()
        }

        getHistoryTransaction()
    }

    fun getHistoryTransaction() {
        progressDialog.setTitle("Mohon Menunggu")
        progressDialog.setMessage("Sedang Memuat ...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        FinpaySDK.getHistoryTransaction(this@TransactionHistoryActivity, {
            println("total list data")
            println(it.listData)
            if(it.listData!!.isEmpty() || it.listData!!.count() == 0) {
                listHistoryTransaction.visibility = View.GONE
                emptyState.visibility = View.VISIBLE
            } else {
                listHistoryTransaction.visibility = View.VISIBLE
                emptyState.visibility = View.GONE
            }
            listHistoryTransaction.adapter = TransactionHistoryAdapter(this, R.layout.item_history_transaction, it.listData!!)
            progressDialog.dismiss()
        },{
            progressDialog.dismiss()
            Toast.makeText(this, it, Toast.LENGTH_LONG)
        })
    }
}