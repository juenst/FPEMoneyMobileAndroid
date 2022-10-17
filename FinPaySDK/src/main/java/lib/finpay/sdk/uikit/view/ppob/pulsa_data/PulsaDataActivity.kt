package lib.finpay.sdk.uikit.view.ppob.pulsa_data

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
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils


class PulsaDataActivity : AppCompatActivity() {
    lateinit var txtPhoneNumber: EditText
    lateinit var btnNext: Button
    lateinit var btnContact: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulsa_data)
        supportActionBar!!.hide()

        txtPhoneNumber = findViewById(R.id.txt_phone_no)
        btnNext = findViewById(R.id.btnNext)
        btnContact = findViewById(R.id.btnContact)

        val listOpr: ArrayList<String> = arrayListOf()
        ButtonUtils.checkButtonState(btnNext)
        txtPhoneNumber.doOnTextChanged { text, start, before, count ->
            btnNext.isEnabled = (!text.isNullOrBlank() && text.length>=9)
            ButtonUtils.checkButtonState(btnNext)
        }

        btnNext.setOnClickListener {
            if(txtPhoneNumber.text.toString() != "") {
                getDenom()
            } else {
                DialogUtils.showDialogError(this, "", "Masukan nomor telepon terlebih dahulu")
            }
        }

        btnContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI)
            intent.type = CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(intent, 1)
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
//        println("masuk sini 2")
//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            var cursor: Cursor? = null
//            try {
//                val uri: Uri? = data.data
//                cursor = contentResolver.query(
//                    uri!!,
//                    arrayOf(CommonDataKinds.Phone.NUMBER),
//                    null,
//                    null,
//                    null
//                )
//                if (cursor != null && cursor.moveToNext()) {
//                    val phone: String = cursor.getString(0)
//                    // Do something with phone
//                    println("masuk sini")
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }

    fun getDenom() {
        FinpaySDK.getListSubProduct(
            this@PulsaDataActivity,
            txtPhoneNumber.text.toString(), {
                //next activity send list denom
            }, {

            }
        )
    }
}