package lib.finpay.sdk.uikit.view.register

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.midtrans.sdk.corekit.utilities.Utils
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.view.pin.PinActivity

class RegisterActivity: AppCompatActivity() {
    lateinit var btnBack: ImageView
    lateinit var btnRegister: Button
    lateinit var mainParent: LinearLayout
    lateinit var txtName: EditText
    lateinit var txtPhoneNumber: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnBack = findViewById(R.id.btnBack)
        btnRegister = findViewById(R.id.btnRegister)
        mainParent = findViewById(R.id.mainParentRegister)
        txtName = findViewById(R.id.txtName)
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber)

        btnBack.setOnClickListener{
            finish()
        }

        btnRegister.setOnClickListener {
            val intent = Intent(this, PinActivity::class.java)
            startActivity(intent)
        }

        txtPhoneNumber.doOnTextChanged { text, start, before, count ->
            btnRegister.isEnabled = (!text.isNullOrBlank() && text.length>=9)
            checkButtonState(btnRegister)
        }

        mainParent.setOnClickListener {
            if(txtPhoneNumber.hasFocus()){
                Utils.hideKeyboard(this, txtPhoneNumber)
            }
        }
    }

    private fun checkButtonState(button:Button){
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