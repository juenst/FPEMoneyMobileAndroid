package lib.finpay.sdk.uikit.view.transaction

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.view.transaction.adapter.TransactionHistoryAdapter
import java.text.SimpleDateFormat
import java.util.*

class TransactionHistoryActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    lateinit var listHistoryTransaction: ListView
    lateinit var emptyState: LinearLayout
    lateinit var btnBack: ImageView
    lateinit var btn7days: CardView
    lateinit var btn30days: CardView
    lateinit var btn3motnh: CardView
    lateinit var progressDialog: ProgressDialog

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_transaction)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        listHistoryTransaction = findViewById(R.id.list_history_transaction)
        emptyState = findViewById(R.id.emptyState)
        btnBack = findViewById(R.id.btnBack)
        btn7days = findViewById(R.id.seven_days)
        btn30days = findViewById(R.id.one_month)
        btn3motnh = findViewById(R.id.thirty_month)
        progressDialog = ProgressDialog(this@TransactionHistoryActivity)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)

        btnBack.setOnClickListener{
            finish()
        }

        val format = SimpleDateFormat("yyyyMMdd")
        val calendar = Calendar.getInstance()
        val endDate = format.format(calendar.time)
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        val startDate = format.format(calendar.time)
        getHistoryTransaction(startDate.toString(), endDate.toString())


        btn7days.setBackgroundColor(Integer.parseUnsignedInt("FFFFFFFF", 16))
        btn30days.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))
        btn3motnh.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))

        btn7days.setOnClickListener {
            btn7days.setBackgroundColor(Integer.parseUnsignedInt("FFFFFFFF", 16))
            btn30days.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))
            btn3motnh.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))

            val calendar = Calendar.getInstance()
            val endDate = format.format(calendar.time)
            calendar.add(Calendar.DAY_OF_YEAR, -7)
            val startDate = format.format(calendar.time)
            getHistoryTransaction(startDate.toString(), endDate.toString())

        }

        btn30days.setOnClickListener {
            btn7days.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))
            btn30days.setBackgroundColor(Integer.parseUnsignedInt("FFFFFFFF", 16))
            btn3motnh.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))

            val calendar = Calendar.getInstance()
            val endDate = format.format(calendar.time)
            calendar.add(Calendar.MONTH, -1)
            val startDate = format.format(calendar.time)
            getHistoryTransaction(startDate.toString(), endDate.toString())
        }

        btn3motnh.setOnClickListener {
            btn7days.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))
            btn30days.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))
            btn3motnh.setBackgroundColor(Integer.parseUnsignedInt("FFFFFFFF", 16))

            val calendar = Calendar.getInstance()
            val endDate = format.format(calendar.time)
            calendar.add(Calendar.MONTH, -3)
            val startDate = format.format(calendar.time)
            getHistoryTransaction(startDate.toString(), endDate.toString())
        }
    }

    fun getHistoryTransaction(startDate: String, endDate: String) {
        progressDialog.setTitle("Mohon Menunggu")
        progressDialog.setMessage("Sedang Memuat ...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        FinpaySDK.getHistoryTransaction(java.util.UUID.randomUUID().toString(), this@TransactionHistoryActivity, startDate, endDate, {
            if(it.getListHistory().isEmpty() || it.getListHistory().count() == 0) {
                listHistoryTransaction.visibility = View.GONE
                emptyState.visibility = View.VISIBLE
            } else {
                listHistoryTransaction.visibility = View.VISIBLE
                emptyState.visibility = View.GONE
            }
            listHistoryTransaction.adapter = TransactionHistoryAdapter(this, R.layout.item_history_transaction, it.getListHistory())
            progressDialog.dismiss()
        },{
            progressDialog.dismiss()
            Toast.makeText(this, it, Toast.LENGTH_LONG)
        })
    }
}