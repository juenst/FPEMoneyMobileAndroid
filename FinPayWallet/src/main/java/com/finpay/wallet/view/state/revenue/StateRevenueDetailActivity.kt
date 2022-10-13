package com.finpay.wallet.view.state.revenue

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R
import java.util.*

class StateRevenueDetailActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    private lateinit var menuTitle: TextView
    private lateinit var billingCode: EditText
    lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_state_revenue_detail)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        btnNext = findViewById(R.id.btn_next)

        val extras = intent.extras
        if (extras != null) {
            val value = extras.getString("type")
            if (value != null) {
                menuTitle.text = value.uppercase(Locale.getDefault())
            }
        }

        btnBack.setOnClickListener {
            finish()
        }

        billingCode.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                // not used
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
                // not used
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (count >= 7) {
                    btnNext.isEnabled = true
                    btnNext.isClickable = true
                } else {
                    btnNext.isEnabled = false
                    btnNext.isClickable = false
                }
            }
        })

        btnNext.setOnClickListener {

        }
    }
}