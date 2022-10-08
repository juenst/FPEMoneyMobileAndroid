package com.finpay.wallet.view.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.finpay.wallet.R

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val backButton = findViewById(R.id.backButton) as ImageView
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}