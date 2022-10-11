package com.finpay.wallet.view.upgrade

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.finpay.wallet.R
import lib.finpay.sdk.FinPaySDK
import java.io.ByteArrayOutputStream
import java.io.File


class UpgradeAccountPersonalDataActivity : AppCompatActivity() {
    private lateinit var btnBack: ImageView
    private lateinit var txtMotherName: EditText
    private lateinit var txtKK: EditText
    private lateinit var txtNationality: EditText
    private lateinit var txtEmail: EditText
    private lateinit var btnSubmit: Button
    var activity: Activity? = null
    lateinit var progressDialog: ProgressDialog

    val imgResultSelfie: String? by lazy {
        intent.getStringExtra("imgResultSelfie")
    }

    val imgResultIdentity: String? by lazy {
        intent.getStringExtra("imgResultIdentity")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_account_personal_data)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        btnSubmit = findViewById(R.id.btnSubmit)
        txtMotherName = findViewById(R.id.txtMotherName)
        txtKK = findViewById(R.id.txtKK)
        txtNationality = findViewById(R.id.txtNationality)
        txtEmail = findViewById(R.id.txtEmail)
        activity = this@UpgradeAccountPersonalDataActivity
        progressDialog = ProgressDialog(this)

        btnBack.setOnClickListener {
            onBackPressed()
        }

        checkButtonState(btnSubmit)
        btnSubmit.setOnClickListener{
            println("image identity => ${imgResultIdentity}")
            println("image selfie => ${imgResultSelfie}")
            println("mother name => ${txtMotherName.getText()}")
            println("no kk => ${txtKK.getText()}")
            println("nationality => ${txtNationality.getText()}")
            println("email => ${txtEmail.getText()}")

            var imageIdentity: String = encodeImage(imgResultIdentity!!)!!
            var imageSelfie: String = encodeImage(imgResultSelfie!!)!!

            progressDialog.setTitle("Mohon Menunggu")
            progressDialog.setMessage("Sedang Memuat ...")
            progressDialog.setCancelable(false) // blocks UI interaction
            progressDialog.show()

            FinPaySDK().upgradeAccount(
                "083815613839,",
                imageIdentity,
                imageSelfie,
                txtMotherName.text.toString(),
                txtKK.text.toString(),
                txtNationality.text.toString(),
                txtEmail.text.toString(),
                {
                    val intent = Intent(this, UpgradeAccountSuccessActivity::class.java)
                    startActivity(intent)
                    progressDialog.dismiss()
                },
                {
                    Toast.makeText(this@UpgradeAccountPersonalDataActivity, it, Toast.LENGTH_LONG)
                    progressDialog.dismiss()
                }
            )
            progressDialog.dismiss()
        }

        txtMotherName.doOnTextChanged { text, start, before, count ->
            checkForm()
        }

        txtKK.doOnTextChanged { text, start, before, count ->
            checkForm()
        }

        txtNationality.doOnTextChanged { text, start, before, count ->
            checkForm()
        }

        txtEmail.doOnTextChanged { text, start, before, count ->
            checkForm()
        }
    }

    private fun encodeImage(path: String): String? {
        val imgFile = File(path.replace("file://", ""))
        if(imgFile.exists()) {
            val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            val byteArrayOutputStream = ByteArrayOutputStream()
            myBitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream)
            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
            val encoded = Base64.encodeToString(byteArray, Base64.DEFAULT)
            return encoded
        }
        return ""
    }

    fun checkForm() {
        if(
            txtMotherName.text.trim().length == 0 ||
            txtNationality.text.trim().length == 0 ||
            txtNationality.text.trim().length == 0 ||
            txtEmail.text.trim().length == 0
        ) {
            btnSubmit.isEnabled = false
        } else {
            btnSubmit.isEnabled = true
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