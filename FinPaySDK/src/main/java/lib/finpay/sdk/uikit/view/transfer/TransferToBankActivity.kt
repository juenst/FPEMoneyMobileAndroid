package lib.finpay.sdk.uikit.view.transfer

import android.app.ProgressDialog
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.Utils
import lib.finpay.sdk.uikit.view.upgrade.CitizenshipActivity

class TransferToBankActivity : AppCompatActivity() {
    lateinit var txtNomorPenerima: EditText
    lateinit var btnContact: ImageView
    lateinit var btnNext: Button
    lateinit var btnBack: ImageView
    lateinit var btnChooseBank: LinearLayout
    lateinit var selectedBank:TextView
    lateinit var progressDialog: ProgressDialog

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { intent.getStringExtra("transNumber")}

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.getStringExtra("bankSelectedResult")?.let {
                selectedBank.setText(it.uppercase())
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_to_bank)
        supportActionBar!!.hide()

        txtNomorPenerima = findViewById(R.id.txtNomorPenerima)
        btnContact = findViewById(R.id.btnContact)
        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)
        selectedBank = findViewById(R.id.selectedBank)
        btnChooseBank = findViewById(R.id.chooseBank)
        progressDialog = ProgressDialog(this@TransferToBankActivity)

        ButtonUtils.checkButtonState(btnNext)
        txtNomorPenerima.doOnTextChanged { text, start, before, count ->
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
            FinpaySDK.transferToOtherInquiry(
                transNumber!!,
                this@TransferToBankActivity,
                txtNomorPenerima.text.toString(), {
                    progressDialog.dismiss()
                    println("call another activity")
                }, {
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(this@TransferToBankActivity, "", it)
                }
            )
        }

        btnChooseBank.setOnClickListener {
            val intent = Intent(this, BankActivity::class.java)
            intent.putExtra("theme", finpayTheme)
            resultLauncher.launch(intent)
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
                        txtNomorPenerima.setText(Utils.validateMobileNumber(value))
                        btnNext.isEnabled = (!value.isNullOrBlank() && value.length>=9)
                        ButtonUtils.checkButtonState(btnNext)
                    } else {
                        DialogUtils.showDialogError(this@TransferToBankActivity, "", "Format Nomor tidak sesuai")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}