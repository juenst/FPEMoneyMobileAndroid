package lib.finpay.sdk.uikit.view.wallet

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.uikit.utilities.TextUtils
import lib.finpay.sdk.uikit.view.topup.TopupActivity
import lib.finpay.sdk.uikit.view.transfer.TransferActivity

class WalletActivity : AppCompatActivity() {
    lateinit var textSaldo: TextView
    lateinit var btnVisible: ImageView
    lateinit var btnVisibleOff: ImageView
    lateinit var btnBack: ImageView
    lateinit var btnTopup: ImageView
    lateinit var btnTransfer: ImageView
    var _saldo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)
        supportActionBar!!.hide()

        //initialize
        textSaldo = findViewById(R.id.txt_saldo)
        btnVisible = findViewById(R.id.icon_visibility)
        btnVisibleOff = findViewById(R.id.icon_visibility_off)
        btnBack = findViewById(R.id.btnBack)
        btnTopup = findViewById(R.id.btnTopup)
        btnTransfer = findViewById(R.id.btnTransfer)
        btnVisible.visibility = View.GONE
        btnVisibleOff.visibility = View.VISIBLE

        getBalance()

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
            //FinPaySDK().openTopUp(requireContext())
            val intent = Intent(this, TopupActivity::class.java)
            this.startActivity(intent)
        }

        btnTransfer.setOnClickListener{
            //FinPaySDK().openHistoryTransaction(requireContext())
            val intent = Intent(this, TransferActivity::class.java)
            this.startActivity(intent)
        }
    }

    fun getBalance() {
        FinpaySDK.getUserBallance(this@WalletActivity, {
            _saldo = TextUtils.formatRupiah(it.amount!!.toDouble())
            textSaldo.text = TextUtils.formatRupiah(it.amount!!.toDouble())
        },{
            Toast.makeText(this, it, Toast.LENGTH_LONG)
        })
    }
}