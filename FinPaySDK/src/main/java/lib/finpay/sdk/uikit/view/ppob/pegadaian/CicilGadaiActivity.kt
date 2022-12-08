package lib.finpay.sdk.uikit.view.ppob.pegadaian

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.doOnTextChanged
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils

class CicilGadaiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cicil_gadai)
        supportActionBar!!.hide()
        val backButton = findViewById<ImageView>(R.id.btnBack)
        val txtNoKredit = findViewById<EditText>(R.id.noKreditField)
        val btnContact = findViewById<ImageView>(R.id.btnContact)
        val btnLanjut = findViewById<Button>(R.id.btnLanjut)
        val progressDialog = ProgressDialog(this)

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
                ProductCode.CICIL_GADAI,
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