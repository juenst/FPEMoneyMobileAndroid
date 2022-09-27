package com.finpay.wallet.view.app.ui.history

import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.finpay.wallet.R
import com.google.gson.Gson
import lib.finpay.sdk.model.DetailHistoryTransactionModel
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter


class DetailHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_history)
        supportActionBar!!.hide()



        val closeButton = findViewById<TextView>(R.id.closeDetailTransaction)
        val imageStatus = findViewById<ImageView>(R.id.transactionStatusImage)
        val date = findViewById<TextView>(R.id.transactionTime)
        val amountTransaction = findViewById<TextView>(R.id.transactionAmount)
        val receiver = findViewById<TextView>(R.id.transactionCreditReceiver)
        val trxNumber = findViewById<TextView>(R.id.transactionNumber)
        val trxSerialNumber = findViewById<TextView>(R.id.transactionSerialNumber)
        val trxType = findViewById<TextView>(R.id.transactionType)
        val gson = Gson()
        val ob: DetailHistoryTransactionModel = gson.fromJson(intent.getStringExtra("dataDetailHistory"), DetailHistoryTransactionModel::class.java)
        println("Data in DetailHistoryActivity " + ob.getDateTime())

        val str = ob.getDateTime()
        val pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val localDateTime = LocalDateTime.parse(str, pattern)
        val dates: String = ""+localDateTime.dayOfMonth + " " + localDateTime.month.toString().lowercase().replaceFirstChar {
            it.uppercase()
        }+ " " + localDateTime.year
        val times: String = ""+localDateTime.hour + ":" + localDateTime.minute + ":" + localDateTime.second + " WIB"





        closeButton.setOnClickListener {
            onBackPressed()
        }


        trxType.text = ob.getDesc()
        trxSerialNumber.text = ob.getBillingNo()
        trxNumber.text= ob.getTraxId()
        receiver.text = ob.getDestination()
        date.text = "$dates $times"
        amountTransaction.text = "Rp "+ob.getValues().toString()
        imageStatus.setImageResource(R.drawable.ic_struk_success_small)

        if(ob.getBillingNo()==null || ob.getBillingNo()==""){
            trxSerialNumber.visibility= View.GONE
        }

    }
}