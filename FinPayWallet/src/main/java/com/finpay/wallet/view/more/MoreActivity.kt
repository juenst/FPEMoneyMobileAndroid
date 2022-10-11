package com.finpay.wallet.view.more

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R
import com.finpay.wallet.view.ppob.AsuransiActivity
import com.finpay.wallet.view.ppob.FinpayActivity

class MoreActivity : AppCompatActivity()  {
    lateinit var btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        val btnFinpay = findViewById<LinearLayout>(R.id.btnFinpay)
        val btnAsuransi = findViewById<LinearLayout>(R.id.btnAsuransi)

        btnFinpay.setOnClickListener {
            val intent = Intent(this, FinpayActivity::class.java)
            this.startActivity(intent)
        }

        btnAsuransi.setOnClickListener {
            val intent = Intent(this, AsuransiActivity::class.java)
            this.startActivity(intent)
        }

        btnBack.setOnClickListener{
            finish()
        }
    }

}