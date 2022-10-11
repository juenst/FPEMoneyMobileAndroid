package com.finpay.wallet.view.more

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R
import com.finpay.wallet.view.alfamart.AlfamartActivity
import com.finpay.wallet.view.best.telkomsel.`package`.BestTelkomselPackageActivity
import com.finpay.wallet.view.state.revenue.StateRevenueActivity
import com.finpay.wallet.view.internet.tv.cable.*
import com.finpay.wallet.view.pdam.PDAMActivity
import com.finpay.wallet.view.voucher.VoucherDealsActivity

class MoreActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    private lateinit var btnAlfamart: LinearLayout
    private lateinit var btnInternetTvCable: LinearLayout
    private lateinit var btnStateRevenue: LinearLayout
    private lateinit var btnBestTelkomselPackage: LinearLayout
    private lateinit var btnPDAM: LinearLayout
    private lateinit var btnVoucherDeals: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        btnAlfamart = findViewById(R.id.btn_alfamart)
        btnInternetTvCable = findViewById(R.id.btn_internet_tv_cable)
        btnBestTelkomselPackage = findViewById(R.id.btn_best_telkomsel_package)
        btnPDAM = findViewById(R.id.btn_pdam)
        btnStateRevenue = findViewById(R.id.btn_state_revenue)
        btnVoucherDeals = findViewById(R.id.btn_voucher_deals)

        btnBack.setOnClickListener {
            finish()
        }

        btnAlfamart.setOnClickListener {
            val intent = Intent(this, AlfamartActivity::class.java)
            this.startActivity(intent)
        }

        btnBestTelkomselPackage.setOnClickListener {
            val intent = Intent(this, BestTelkomselPackageActivity::class.java)
            this.startActivity(intent)
        }

        btnInternetTvCable.setOnClickListener {
            val intent = Intent(this, InternetTvCableActivity::class.java)
            this.startActivity(intent)
        }

        btnPDAM.setOnClickListener {
            val intent = Intent(this, PDAMActivity::class.java)
            this.startActivity(intent)
        }

        btnStateRevenue.setOnClickListener {
            val intent = Intent(this, StateRevenueActivity::class.java)
            this.startActivity(intent)
        }

        btnVoucherDeals.setOnClickListener {
            val intent = Intent(this, VoucherDealsActivity::class.java)
            this.startActivity(intent)
        }

    }

}