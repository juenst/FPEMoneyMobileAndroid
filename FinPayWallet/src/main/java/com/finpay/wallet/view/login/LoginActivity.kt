package com.finpay.wallet.view.login

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.widget.doOnTextChanged
import com.finpay.wallet.R
import com.finpay.wallet.view.AppActivity
import com.google.android.material.textfield.TextInputEditText
import com.midtrans.sdk.corekit.utilities.Utils
import kotlinx.android.synthetic.main.activity_change_pin.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnMasuk = findViewById<Button>(R.id.buttonMasuk)
        val backButton = findViewById<ImageView>(R.id.backButtonLogin)
        val mainparent = findViewById<LinearLayout>(R.id.mainParentLogin)

        val numberField = findViewById<TextInputEditText>(R.id.phoneNumberField)
        checkButtonState(btnMasuk)
        btnMasuk.setOnClickListener{
            val intent = Intent(this, AppActivity::class.java)
            startActivity(intent)
            finish()
        }

        backButton.setOnClickListener{
            onBackPressed()
        }

        numberField.doOnTextChanged { text, start, before, count ->
            btnMasuk.isEnabled = (!text.isNullOrBlank() && text.length>=9)
            checkButtonState(btnMasuk)
        }

        mainparent.setOnClickListener {
            if(numberField.hasFocus()){
                Utils.hideKeyboard(this,numberField)
            }
        }

    }

    fun checkButtonState(button:Button){
        // Create a color state list programmatically
        val states = arrayOf(
            intArrayOf(android.R.attr.state_enabled), // enabled
            intArrayOf(-android.R.attr.state_enabled) // disabled
        )
        val bgColors = intArrayOf(
            Color.parseColor("#00ACBA"), // enabled color
            Color.parseColor("#d5d5d5") // disabled color
        )
        val textColors = intArrayOf(
            Color.parseColor("#ffffff"), // enabled color
            Color.parseColor("#a5a5a5")// disabled color
        )
        val bgColorStates = ColorStateList(states,bgColors)
        val textColorStates = ColorStateList(states,textColors)

        // Set button background tint
        button.backgroundTintList = bgColorStates
        button.setTextColor(textColorStates)
    }
}