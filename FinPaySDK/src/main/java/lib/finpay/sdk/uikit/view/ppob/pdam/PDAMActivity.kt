package lib.finpay.sdk.uikit.view.ppob.pdam

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.corekit.model.DetailProductModel
import lib.finpay.sdk.corekit.model.Product
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.view.upgrade.CitizenshipActivity
import java.util.*

class PDAMActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    lateinit var btnBack: ImageView
    private lateinit var txtNomorPelanggan: EditText
    private lateinit var btnNext: Button
    private lateinit var btnContact: ImageView
    private lateinit var chooseWilayah: LinearLayout
    private lateinit var selectedWilayah: TextView
    lateinit var progressDialog: ProgressDialog
    var region: String = ""

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdam)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        btnBack = findViewById(R.id.btnBack)
        btnNext = findViewById(R.id.btnNext)
        btnContact = findViewById(R.id.btnContact)
        txtNomorPelanggan = findViewById(R.id.txtNomorPelanggan)
        chooseWilayah = findViewById(R.id.chooseWilayah)
        selectedWilayah = findViewById(R.id.selectedWilayah)
        progressDialog = ProgressDialog(this@PDAMActivity)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnContact.setColorFilter(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)
        btnNext.setBackgroundColor(if(btnNext.isEnabled()) if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!! else Color.parseColor("#d5d5d5"))

        btnBack.setOnClickListener {
            onBackPressed()
        }

        chooseWilayah.setOnClickListener {
            val intent = Intent(this, PDAMSearchActivity::class.java)
            startActivityForResult(intent, 2)
        }

        ButtonUtils.checkButtonState(btnNext)
        txtNomorPelanggan.doOnTextChanged { text, start, before, count ->
            btnNext.isEnabled = (!text.isNullOrBlank() && text.length>=9 && region != "")
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
            FinpaySDK.ppobInquiry(
                java.util.UUID.randomUUID().toString(),
                this@PDAMActivity,
                txtNomorPelanggan.text.toString(),
                ProductCode.PDAM,
                "", {
                    progressDialog.dismiss()
                }, {
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(this@PDAMActivity, "", it)
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
                        btnNext.isEnabled = (!value.isNullOrBlank() && value.length>=9 && region != "")
                        ButtonUtils.checkButtonState(btnNext)
                    } else {
                        DialogUtils.showDialogError(this@PDAMActivity, "", "Format Nomor tidak sesuai")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else if(requestCode == 2 && resultCode == RESULT_OK) {
            data?.getStringExtra("pdamSelectedResult")?.let {
                selectedWilayah.setText(it.uppercase())
                region = it.uppercase()
                btnNext.isEnabled = (!txtNomorPelanggan.text.isNullOrBlank() && txtNomorPelanggan.text.length>=9 && region != "")
                ButtonUtils.checkButtonState(btnNext)
            }
        }
    }
}