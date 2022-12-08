package lib.finpay.sdk.uikit.view.upgrade

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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.Utils
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

    val imgResultSelfie: String? by lazy { intent.getStringExtra("imgResultSelfie") }
    val imgResultIdentity: String? by lazy { intent.getStringExtra("imgResultIdentity") }
    val imgResultIdentityBase64: String? by lazy { intent.getStringExtra("imgResultIdentityBase64") }
    val imgResultSelfieBase64: String? by lazy { intent.getStringExtra("imgResultSelfieBase64") }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.getStringExtra("countrySelectedResult")?.let {
                txtNationality.setText(it.uppercase())
            }
        }
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
        progressDialog = ProgressDialog(this@UpgradeAccountPersonalDataActivity)

        btnBack.setOnClickListener {
            onBackPressed()
        }

        checkButtonState(btnSubmit)
        btnSubmit.setOnClickListener {
            println("image identity => ${imgResultIdentity}")
            println("image selfie => ${imgResultSelfie}")
            println("mother name => ${txtMotherName.getText()}")
            println("no kk => ${txtKK.getText()}")
            println("nationality => ${txtNationality.getText()}")
            println("email => ${txtEmail.getText()}")

            progressDialog.setTitle("Mohon Menunggu")
            progressDialog.setMessage("Sedang Memuat ...")
            progressDialog.setCancelable(false)
            progressDialog.show()

            var imageIdentity: String = Utils.encodeImage(imgResultIdentity!!)!!
            var imageSelfie: String = Utils.encodeImage(imgResultSelfie!!)!!

            FinpaySDK.upgradeAccount(
                java.util.UUID.randomUUID().toString(),
                this@UpgradeAccountPersonalDataActivity,
                imageIdentity,//imgResultIdentityBase64!!,
                imageSelfie,//imgResultSelfieBase64!!,
                txtMotherName.text.toString(),
                txtKK.text.toString(),
                txtNationality.text.toString(),
                txtEmail.text.toString(), {
                    progressDialog.dismiss()
                    val intent = Intent(this, UpgradeAccountSuccessActivity::class.java)
                    startActivity(intent)
                }, {
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(
                        this@UpgradeAccountPersonalDataActivity,
                        "Error",
                        it
                    )
                }
            )
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

        txtNationality.setText("indonesia".uppercase())
        txtNationality.setOnClickListener {
            val intent = Intent(this, CitizenshipActivity::class.java)
            resultLauncher.launch(intent)
        }
    }


    fun checkForm() {
        if (
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

    fun checkButtonState(button: Button) {
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
        val bgColorStates = ColorStateList(states, bgColors)
        val textColorStates = ColorStateList(states, textColors)

        // Set button background tint
        button.backgroundTintList = bgColorStates
        button.setTextColor(textColorStates)
    }
}