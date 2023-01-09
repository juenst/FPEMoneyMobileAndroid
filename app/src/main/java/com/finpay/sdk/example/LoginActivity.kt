package com.finpay.sdk.example

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.widget.doOnTextChanged
import com.finpay.sdk.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.corekit.model.Credential
import lib.finpay.sdk.uikit.FinpaySDKUI
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils


class LoginActivity : AppCompatActivity() {
    var _username: String = "";
    var _password: String = "";
    var _secretKey: String = "";
    var _phoneNumber: String = "";

    lateinit private var userName: EditText
    lateinit private var password: EditText
    lateinit private var secretKey: EditText

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar!!.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(Color.parseColor("#e60000"))
        }

        val btnSettings = findViewById<ImageView>(R.id.btnSettings)
        val btnMasuk = findViewById<Button>(R.id.buttonMasuk)
        val phoneNumber = findViewById<EditText>(R.id.phoneNumber)
        ButtonUtils.checkButtonState(btnMasuk)

        btnMasuk.setOnClickListener{
            if(_username == "" || _username == null || _password == "" || _password == null || _secretKey == "" || _secretKey == null) {
                DialogUtils.showDialogError(this, "", "Harap masukkan credential Anda pada tombol pengaturan diatas", theme())
            } else {
                FinpaySDKUI.logout(this, {
                    FinpaySDKUI.connectAccount("",this, credential(), theme(), {
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.putExtra("userName", _username)
                        intent.putExtra("password", _password)
                        intent.putExtra("secretKey", _secretKey)
                        intent.putExtra("phoneNo", _phoneNumber)
                        intent.putExtra("name", "Widiyanto Ramadhan")
                        startActivity(intent)
                        this.finish()
                    })
                })
            }
        }

        phoneNumber.doOnTextChanged { text, start, before, count ->
            _phoneNumber = phoneNumber.text.toString()
            btnMasuk.isEnabled = (!text.isNullOrBlank() && text.length>=9)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ButtonUtils.checkButtonState(btnMasuk, theme())
            } else {
                ButtonUtils.checkButtonState(btnMasuk)
            }
        }

        btnSettings.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(R.layout.dialog_settings)
            val userName = dialog.findViewById<EditText>(R.id.txtUsername)
            val password = dialog.findViewById<EditText>(R.id.txtPassword)
            val secretKey = dialog.findViewById<EditText>(R.id.txtSecretKey)
            val btnNext = dialog.findViewById<CardView>(R.id.btnNext)
            val btnDefault = dialog.findViewById<CardView>(R.id.btnDefault)

            userName!!.setText(_username)
            password!!.setText(_password)
            secretKey!!.setText(_secretKey)

            btnNext!!.setOnClickListener {
                _username = userName.text.toString()
                _password = password.text.toString()
                _secretKey = secretKey.text.toString()
                userName.setText(userName.text.toString())
                password.setText(password.text.toString())
                secretKey.setText(secretKey.text.toString())
                dialog.dismiss()
            }

            btnDefault!!.setOnClickListener {
                _username = "MT77764DKM83N"
                _password = "YJV3AM0y"
                _secretKey = "daYumnMb"
                userName.setText("MT77764DKM83N")
                password.setText("YJV3AM0y")
                secretKey.setText("daYumnMb")

                _phoneNumber = "083815613839"
                phoneNumber.setText("083815613839")
            }
            dialog.show()
        }
    }

    fun credential(): Credential {
        val cd = Credential()
        cd.setUsername(_username)
        cd.setPassword(_password)
        cd.setSecretKey(_secretKey)
        cd.setPhoneNumber(_phoneNumber)
        cd.setCustName("Widiyanto Ramadhan")
        return cd
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun theme(): FinpayTheme {
        val theme = FinpayTheme()
        theme.setPrimaryColor(Color.parseColor("#e60000"))
        theme.setSecondaryColor(Color.parseColor("#e60000"))
        theme.setAppBarBackgroundColor(Color.parseColor("#e60000"))
        theme.setAppBarTextColor(Color.parseColor("#FFFFFF"))
        return theme
    }
}