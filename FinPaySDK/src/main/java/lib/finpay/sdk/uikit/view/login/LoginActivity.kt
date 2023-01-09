package lib.finpay.sdk.uikit.view.login

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
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.view.pin.PinActivity

class LoginActivity : AppCompatActivity() {

    lateinit var appbar: androidx.appcompat.widget.Toolbar

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        appbar = findViewById(R.id.appbar)
        val btnMasuk = findViewById<Button>(R.id.buttonMasuk)
        val backButton = findViewById<ImageView>(R.id.backButtonLogin)
        val mainparent = findViewById<LinearLayout>(R.id.mainParentLogin)

        val numberField = findViewById<EditText>(R.id.phoneNumberField)
        ButtonUtils.checkButtonState(btnMasuk, finpayTheme)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        backButton.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)

        btnMasuk.setOnClickListener{
            val intent = Intent(this, PinActivity::class.java)
            startActivity(intent)
        }

        backButton.setOnClickListener{
            onBackPressed()
        }

        numberField.doOnTextChanged { text, start, before, count ->
            btnMasuk.isEnabled = (!text.isNullOrBlank() && text.length>=9)
            ButtonUtils.checkButtonState(btnMasuk, finpayTheme)
        }

        mainparent.setOnClickListener {
            if(numberField.hasFocus()){
                //Utils.hideKeyboard(this,numberField)
            }
        }

    }
}