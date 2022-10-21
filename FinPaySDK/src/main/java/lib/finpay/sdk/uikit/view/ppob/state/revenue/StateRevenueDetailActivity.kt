package lib.finpay.sdk.uikit.view.ppob.state.revenue

import android.app.ProgressDialog
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.corekit.model.DetailProductModel
import lib.finpay.sdk.corekit.model.Product
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import java.util.*

class StateRevenueDetailActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    private lateinit var menuTitle: TextView
    private lateinit var billingCode: EditText
    private lateinit var btnNext: Button
    private lateinit var btnContact: ImageView
    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_state_revenue_detail)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        btnContact = findViewById(R.id.btn_contact)
        menuTitle = findViewById(R.id.state_revenue_menu_title)
        billingCode = findViewById(R.id.txt_billing_code)
        btnNext = findViewById(R.id.btn_next)
        progressDialog = ProgressDialog(this@StateRevenueDetailActivity)

        ButtonUtils.checkButtonState(btnNext)

        val extras = intent.extras
        if (extras != null) {
            val value = extras.getString("type")
            if (value != null) {
                menuTitle.text = value.toUpperCase()
            }
        }
        btnBack.setOnClickListener {
            finish()
        }

        billingCode.doOnTextChanged { text, start, before, count ->
            btnNext.isEnabled = (!text.isNullOrBlank() && text.length >= 7)
            ButtonUtils.checkButtonState(btnNext)
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
            FinpaySDK.ppobInquiry(this@StateRevenueDetailActivity,
                billingCode.text.toString(),
                if (menuTitle.text.toString() == "PNBP") ProductCode.PNBP else if (menuTitle.text.toString() == "BEA CUKAI") ProductCode.BEA_CUKAI else ProductCode.PAJAK_ONLINE,
                "",
                {
                    progressDialog.dismiss()
                },
                {
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(this@StateRevenueDetailActivity, "", it)
                })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            var cursor: Cursor? = null
            try {
                val uri: Uri? = data!!.data
                cursor = contentResolver.query(
                    uri!!, arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER), null, null, null
                )
                if (cursor != null && cursor.moveToNext()) {
                    val value: String = cursor.getString(0)
                    if (value.length >= 7) {
                        billingCode.setText(value)
                        btnNext.isEnabled = (!value.isNullOrBlank() && value.length >= 7)
                        ButtonUtils.checkButtonState(btnNext)
                    } else {
                        DialogUtils.showDialogError(
                            this@StateRevenueDetailActivity, "", "Format Nomor tidak sesuai"
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}