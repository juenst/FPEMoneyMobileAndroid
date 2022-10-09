package com.finpay.wallet.view.wallet

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R
import lib.finpay.sdk.FinPaySDK
import com.finpay.wallet.utilities.TextUtils
import com.finpay.wallet.view.topup.TopupActivity
import com.finpay.wallet.view.transfer.TransferActivity

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
        FinPaySDK().getUserBallance(this, "083815613839", {
            _saldo = TextUtils().formatRupiah(it.getCustBalance()!!.toDouble())
            textSaldo.text = TextUtils().formatRupiah(it.getCustBalance()!!.toDouble())
        },{
            Toast.makeText(this, it, Toast.LENGTH_LONG)
        })
    }
}