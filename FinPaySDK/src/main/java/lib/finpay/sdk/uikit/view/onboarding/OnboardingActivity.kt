package lib.finpay.sdk.uikit.view.onboarding

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.view.login.LoginActivity
import lib.finpay.sdk.uikit.view.register.RegisterActivity

class OnboardingActivity : AppCompatActivity() {
    lateinit var btnLogin: Button
    lateinit var btnRegister: TextView

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)

        //theming
        btnRegister.setTextColor(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getPrimaryColor()!!)
        btnLogin.setBackgroundColor(if(btnLogin.isEnabled()) if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!! else Color.parseColor("#d5d5d5"))

        btnLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}