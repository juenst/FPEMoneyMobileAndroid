package lib.finpay.sdk.uikit.view.wallet

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.uikit.FinpaySDKUI
import lib.finpay.sdk.uikit.constant.Credential
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import lib.finpay.sdk.uikit.utilities.TextUtils
import lib.finpay.sdk.uikit.view.topup.TopupActivity
import lib.finpay.sdk.uikit.view.transaction.adapter.TransactionHistoryAdapter
import lib.finpay.sdk.uikit.view.transfer.TransferActivity
import java.text.SimpleDateFormat
import java.util.*

class WalletActivity : AppCompatActivity() {
    lateinit var textSaldo: TextView
    lateinit var txtId: TextView
    lateinit var txtSeeAll: TextView
    lateinit var btnVisible: ImageView
    lateinit var btnVisibleOff: ImageView
    lateinit var btnBack: ImageView
    lateinit var btnTopup: ImageView
    lateinit var btnTransfer: ImageView
    lateinit var listHistoryTransaction: ListView
    lateinit var emptyState: LinearLayout
    lateinit var progressDialog: ProgressDialog
    var _saldo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)
        supportActionBar!!.hide()

        //initialize
        textSaldo = findViewById(R.id.txt_saldo)
        txtId = findViewById(R.id.txt_id)
        txtSeeAll = findViewById(R.id.txtSeeAll)
        btnVisible = findViewById(R.id.icon_visibility)
        btnVisibleOff = findViewById(R.id.icon_visibility_off)
        btnBack = findViewById(R.id.btnBack)
        btnTopup = findViewById(R.id.btnTopup)
        btnTransfer = findViewById(R.id.btnTransfer)
        btnVisible.visibility = View.GONE
        btnVisibleOff.visibility = View.VISIBLE
        listHistoryTransaction = findViewById(R.id.list_history_transaction)
        emptyState = findViewById(R.id.emptyState)
        progressDialog = ProgressDialog(this@WalletActivity)
        FinpaySDK.init(this@WalletActivity)
        txtId.text = "ID: "+FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)!!

        getBalance()

        val format = SimpleDateFormat("yyyyMMdd")
        val calendar = Calendar.getInstance()
        val endDate = format.format(calendar.time)
        calendar.add(Calendar.MONTH, -1)
        val startDate = format.format(calendar.time)
        getHistoryTransaction(startDate.toString(), endDate.toString())

        btnBack.setOnClickListener{
            finish()
        }

        btnVisibleOff.setOnClickListener {
            textSaldo.text = "*******"
            btnVisible.visibility = View.VISIBLE
            btnVisibleOff.visibility = View.GONE
        }

        btnVisible.setOnClickListener {
            textSaldo.text = _saldo
            btnVisible.visibility = View.GONE
            btnVisibleOff.visibility = View.VISIBLE
        }

        btnTopup.setOnClickListener{
            val intent = Intent(this, TopupActivity::class.java)
            this.startActivity(intent)
        }

        btnTransfer.setOnClickListener{
            val intent = Intent(this, TransferActivity::class.java)
            this.startActivity(intent)
        }

        txtSeeAll.setOnClickListener {
            FinpaySDKUI.historyTransactionUIBuilder(java.util.UUID.randomUUID().toString(), this@WalletActivity, Credential.credential(this@WalletActivity))
        }
    }

    fun getBalance() {
        FinpaySDK.getUserBallance(java.util.UUID.randomUUID().toString(), this@WalletActivity, {
            _saldo = TextUtils.formatRupiah(it.amount!!.toDouble())
            textSaldo.text = TextUtils.formatRupiah(it.amount!!.toDouble())
        },{
            Toast.makeText(this, it, Toast.LENGTH_LONG)
        })
    }

    fun getHistoryTransaction(startDate: String, endDate: String) {
        FinpaySDK.getHistoryTransaction(java.util.UUID.randomUUID().toString(), this@WalletActivity, startDate, endDate, {
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