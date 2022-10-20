package lib.finpay.sdk.uikit.view.ppob.pulsa_data

import android.app.ProgressDialog
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds
import android.provider.ContactsContract.Contacts
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.DataSubProduct
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.Utils
import lib.finpay.sdk.uikit.view.upgrade.UpgradeAccountSuccessActivity


class PulsaDataActivity : AppCompatActivity() {
    lateinit var txtPhoneNumber: EditText
    lateinit var btnNext: Button
    lateinit var btnContact: ImageView
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulsa_data)
        supportActionBar!!.hide()

        txtPhoneNumber = findViewById(R.id.txt_phone_no)
        btnNext = findViewById(R.id.btnNext)
        btnContact = findViewById(R.id.btnContact)
        progressDialog = ProgressDialog(this@PulsaDataActivity)

        ButtonUtils.checkButtonState(btnNext)
        txtPhoneNumber.doOnTextChanged { text, start, before, count ->
            btnNext.isEnabled = (!text.isNullOrBlank() && text.length>=9)
            ButtonUtils.checkButtonState(btnNext)
        }

        btnContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI)
            intent.type = CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(intent, 1)
        }

        btnNext.setOnClickListener {
            val intent = Intent(this, PulsaDataResultActivity::class.java)
            intent.putExtra("phoneNumber", txtPhoneNumber.text.toString())
            startActivity(intent)
        }
    }

    //0000073482748 --> nomor bpjs widi

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            var cursor: Cursor? = null
            try {
                val uri: Uri? = data!!.data
                cursor = contentResolver.query(
                    uri!!,
                    arrayOf(CommonDataKinds.Phone.NUMBER),
                    null,
                    null,
                    null
                )
                if (cursor != null && cursor.moveToNext()) {
                    val phone: String = cursor.getString(0)
                    if(phone.length>=9) {
                        txtPhoneNumber.setText(Utils.validateMobileNumber(phone))
                        btnNext.isEnabled = (!phone.isNullOrBlank() && phone.length>=9)
                        ButtonUtils.checkButtonState(btnNext)
                    } else {
                        DialogUtils.showDialogError(this@PulsaDataActivity, "", "Nomor telepon minimal 9 karakter")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}