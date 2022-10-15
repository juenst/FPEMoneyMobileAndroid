package com.finpay.wallet.view.ppob.voucher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.ImageView
import com.finpay.wallet.R
import com.finpay.wallet.view.ppob.voucher.adapter.VoucherAdapter
import lib.finpay.sdk.model.VoucherModel

class VoucherDealsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voucher_deals)

        val listVoucher = findViewById<GridView>(R.id.idGRV)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        btnBack.setOnClickListener{
            onBackPressed()
        }

        var list = mutableListOf<VoucherModel>(
            VoucherModel(imageSrc = "ic_bi", voucherName="Tes1"),
            VoucherModel(imageSrc = "ic_bpjs", voucherName="Tes2"),
            VoucherModel(imageSrc = "ic_indihome", voucherName="Tes3"),
            VoucherModel(imageSrc = "ic_pegadaian", voucherName="Tes4"),
        )


        listVoucher.adapter = VoucherAdapter(this, R.layout.item_voucher, list)
    }
}