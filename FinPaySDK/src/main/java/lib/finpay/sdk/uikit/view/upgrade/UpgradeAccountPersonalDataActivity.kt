package lib.finpay.sdk.uikit.view.upgrade

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.Utils
import java.io.ByteArrayOutputStream
import java.io.File


class UpgradeAccountPersonalDataActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
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
    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { intent.getStringExtra("transNumber")}

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

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        btnBack = findViewById(R.id.btnBack)
        btnSubmit = findViewById(R.id.btnSubmit)
        txtMotherName = findViewById(R.id.txtMotherName)
        txtKK = findViewById(R.id.txtKK)
        txtNationality = findViewById(R.id.txtNationality)
        txtEmail = findViewById(R.id.txtEmail)
        activity = this@UpgradeAccountPersonalDataActivity
        progressDialog = ProgressDialog(this@UpgradeAccountPersonalDataActivity)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnSubmit.setBackgroundColor(if(btnSubmit.isEnabled()) if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!! else Color.parseColor("#d5d5d5"))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if(finpayTheme?.getAppBarBackgroundColor() == null) {
                window.setStatusBarColor(Color.parseColor("#333333"))
            } else {
                window.setStatusBarColor(finpayTheme?.getAppBarBackgroundColor()!!)
            }
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }

        ButtonUtils.checkButtonState(btnSubmit, finpayTheme)
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

            var imageIdentity: String = Utils.getFileToByte(imgResultIdentity!!)!!
            var imageSelfie: String = Utils.getFileToByte(imgResultSelfie!!)!!

            FinpaySDK.upgradeAccount(
                transNumber!!,
                this@UpgradeAccountPersonalDataActivity,
//                imageIdentity,//imgResultIdentityBase64!!,
//                imageSelfie,//imgResultSelfieBase64!!,
                "testing",
                "testing",
                txtMotherName.text.toString(),
                txtKK.text.toString(),
                txtNationality.text.toString(),
                txtEmail.text.toString(), {
                    progressDialog.dismiss()
                    val intent = Intent(this, UpgradeAccountSuccessActivity::class.java)
                    intent.putExtra("theme", finpayTheme)
                    startActivity(intent)
                    finish()
                }, {
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(
                        this@UpgradeAccountPersonalDataActivity,
                        "Error",
                        it, finpayTheme
                    )
                }
            )
//            progressDialog.dismiss()
//            val intent = Intent(this, UpgradeAccountSuccessActivity::class.java)
//            intent.putExtra("theme", finpayTheme)
//            startActivity(intent)
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
            intent.putExtra("theme", finpayTheme)
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
}