package com.finpay.wallet.view.alfamart

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R

class AlfamartActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
//    lateinit var btnPembayaranTagihan: LinearLayout
//    lateinit var btnAutoDebit: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_alfamart)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
//        btnPembayaranTagihan = findViewById(R.id.btn_pembayaran_tagihan)
//        btnAutoDebit = findViewById(R.id.btn_pendaftaran_pembayaran_autodebit)

        btnBack.setOnClickListener {
            finish()
        }

//        btnPembayaranTagihan.setOnClickListener {
//            val intent = Intent(this, BPJSDetailActivity::class.java)
//            this.startActivity(intent)
//        }
//
//        btnAutoDebit.setOnClickListener {
//            val intent = Intent(this, InternetTvCableDetailActivity::class.java)
//            this.startActivity(intent)
//        }
    }
}