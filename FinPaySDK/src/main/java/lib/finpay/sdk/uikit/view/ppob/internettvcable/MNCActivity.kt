package lib.finpay.sdk.uikit.view.ppob.internettvcable

import android.app.ProgressDialog
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.*
import androidx.core.widget.doOnTextChanged
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils

class MNCActivity : AppCompatActivity() {
    lateinit var txtNomorPelanggan: EditText
    lateinit var btnContact: ImageView
    lateinit var btnNext: Button
    lateinit var btnBack: ImageView
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mnc)
        supportActionBar!!.hide()

        txtNomorPelanggan = findViewById(R.id.txtNomorPelanggan)
        btnContact = findViewById(R.id.btnContact)
        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)
        progressDialog = ProgressDialog(this@MNCActivity)

        ButtonUtils.checkButtonState(btnNext)
        txtNomorPelanggan.doOnTextChanged { text, start, before, count ->
            btnNext.isEnabled = (!text.isNullOrBlank() && text.length>=9)
            ButtonUtils.checkButtonState(btnNext)
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }

        btnContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(intent, 1)
        }

        btnNext.setOnClickListener {
            progressDialog.setTitle("Mohon Menunggu")
            progressDialog.setMessage("Sedang Memuat ...")
            progressDialog.setCancelable(false)
            progressDialog.show()
            FinpaySDK.ppobInquiry(
                this@MNCActivity,
                txtNomorPelanggan.text.toString(),
                ProductCode.INDIHOME,
                "", {
                    progressDialog.dismiss()
                }, {
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(this@MNCActivity, "", it)
                }
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            var cursor: Cursor? = null
            try {
                val uri: Uri? = data!!.data
                cursor = contentResolver.query(
                    uri!!,
                    arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
                    null,
                    null,
                    null
                )
                if (cursor != null && cursor.moveToNext()) {
                    val value: String = cursor.getString(0)
                    if(value.length>=9) {
                        txtNomorPelanggan.setText(value)
                        btnNext.isEnabled = (!value.isNullOrBlank() && value.length>=9)
                        ButtonUtils.checkButtonState(btnNext)
                    } else {
                        DialogUtils.showDialogError(this@MNCActivity, "", "Format Nomor tidak sesuai")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}