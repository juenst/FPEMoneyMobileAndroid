package com.finpay.wallet.view.ppob.pegadaian

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.doOnTextChanged
import com.finpay.wallet.R

class UlangGadaiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ulang_gadai)

        val backButton = findViewById<ImageView>(R.id.btnBack)
        val noKreditField = findViewById<EditText>(R.id.noKreditField)
        val btnLanjut = findViewById<Button>(R.id.btnLanjut)

        checkButtonState(btnLanjut)

        noKreditField.doOnTextChanged { text, start, before, count ->
            btnLanjut.isEnabled = text!!.isNotEmpty()
            checkButtonState(btnLanjut)
        }

        backButton.setOnClickListener{
            onBackPressed()
        }
    }

    fun checkButtonState(button: Button){
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