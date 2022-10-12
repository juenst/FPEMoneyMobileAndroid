package com.finpay.wallet.view.login

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.widget.doOnTextChanged
import com.finpay.wallet.R
import com.finpay.wallet.utilities.ButtonUtils
import com.finpay.wallet.view.pin.PinActivity
import com.midtrans.sdk.corekit.utilities.Utils

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnMasuk = findViewById<Button>(R.id.buttonMasuk)
        val backButton = findViewById<ImageView>(R.id.backButtonLogin)
        val mainparent = findViewById<LinearLayout>(R.id.mainParentLogin)

        val numberField = findViewById<EditText>(R.id.phoneNumberField)
        ButtonUtils.checkButtonState(btnMasuk)
        btnMasuk.setOnClickListener{
            val intent = Intent(this, PinActivity::class.java)
            startActivity(intent)
        }

        backButton.setOnClickListener{
            onBackPressed()
        }

        numberField.doOnTextChanged { text, start, before, count ->
            btnMasuk.isEnabled = (!text.isNullOrBlank() && text.length>=9)
            ButtonUtils.checkButtonState(btnMasuk)
        }

        mainparent.setOnClickListener {
            if(numberField.hasFocus()){
                Utils.hideKeyboard(this,numberField)
            }
        }

    }
}