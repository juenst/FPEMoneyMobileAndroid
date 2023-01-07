package lib.finpay.sdk.uikit.view.ppob.pegadaian

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils

class UlangGadaiActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ulang_gadai)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        val backButton = findViewById<ImageView>(R.id.btnBack)
        val txtNoKredit = findViewById<EditText>(R.id.noKreditField)
        val btnContact = findViewById<ImageView>(R.id.btnContact)
        val btnLanjut = findViewById<Button>(R.id.btnLanjut)
        val progressDialog = ProgressDialog(this)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        backButton.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnContact.setColorFilter(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)
        btnLanjut.setBackgroundColor(if(btnLanjut.isEnabled()) if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!! else Color.parseColor("#d5d5d5"))

        backButton.setOnClickListener{
            onBackPressed()
        }

        ButtonUtils.checkButtonState(btnLanjut)

        txtNoKredit.doOnTextChanged { text, start, before, count ->
            btnLanjut.isEnabled = (!text.isNullOrBlank() && text.length>=7)
            ButtonUtils.checkButtonState(btnLanjut)
        }

        btnContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(intent, 1)
        }

        btnLanjut.setOnClickListener {
            progressDialog.setTitle("Mohon Menunggu")
            progressDialog.setMessage("Sedang Memuat ...")
            progressDialog.setCancelable(false)
            progressDialog.show()
            FinpaySDK.ppobInquiry(
                java.util.UUID.randomUUID().toString(),
                this,
                txtNoKredit.text.toString(),
                ProductCode.ULANG_GADAI,
                "", {
                    progressDialog.dismiss()
                }, {
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(this, "", it)
                }
            )
        }
    }
}