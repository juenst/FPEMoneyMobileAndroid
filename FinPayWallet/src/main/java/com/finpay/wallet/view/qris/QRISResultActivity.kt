package com.finpay.wallet.view.qris

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R
import com.finpay.wallet.databinding.ActivityQrisResultBinding
import kotlinx.android.synthetic.main.activity_camera_result.*
import java.io.File


class QRISResultActivity : AppCompatActivity() {
    val imgResultIdentity: String? by lazy {
        intent.getStringExtra("resultQR")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qris_result)
        supportActionBar!!.hide()

    }


}