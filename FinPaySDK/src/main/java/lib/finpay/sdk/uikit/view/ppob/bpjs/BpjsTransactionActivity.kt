package lib.finpay.sdk.uikit.view.ppob.bpjs

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.Utils

class BpjsTransactionActivity : AppCompatActivity() {
    lateinit var txtNomorPelanggan: EditText
    lateinit var btnContact: ImageView
    lateinit var btnNext: Button
    lateinit var btnChoosePeriodeTime: LinearLayout
    lateinit var periodeTime:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bpjs_transaction)
        supportActionBar!!.hide()

        txtNomorPelanggan = findViewById(R.id.txtNomorPelanggan)
        btnContact = findViewById(R.id.btnContact)
        btnNext = findViewById(R.id.btnNext)
        btnChoosePeriodeTime = findViewById(R.id.choosePeriodeTime)
        periodeTime = findViewById(R.id.selectedPeriodeMonth)

        btnContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(intent, 1)
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
                        DialogUtils.showDialogError(this@BpjsTransactionActivity, "", "Nomor telepon minimal 9 karakter")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}