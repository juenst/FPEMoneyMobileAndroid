package lib.finpay.sdk.uikit.view.ppob.telkom

import android.app.ProgressDialog
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.core.widget.doOnTextChanged
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.Utils
import lib.finpay.sdk.uikit.view.ppob.pln.PLNResultActivity

class TelkomActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    lateinit var txtNomorPelanggan: EditText
    lateinit var btnContact: ImageView
    lateinit var btnNext: Button
    lateinit var btnBack: ImageView
    lateinit var progressDialog: ProgressDialog

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme}
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    //nomor test 0122527200001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telkom)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        txtNomorPelanggan = findViewById(R.id.txtNomorPelanggan)
        btnContact = findViewById(R.id.btnContact)
        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)
        progressDialog = ProgressDialog(this@TelkomActivity)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnContact.setColorFilter(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)
        btnNext.setBackgroundColor(if(btnNext.isEnabled()) if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!! else Color.parseColor("#d5d5d5"))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if(finpayTheme?.getAppBarBackgroundColor() == null) {
                window.setStatusBarColor(Color.parseColor("#333333"))
            } else {
                window.setStatusBarColor(finpayTheme?.getAppBarBackgroundColor()!!)
            }
        }

        ButtonUtils.checkButtonState(btnNext, finpayTheme)
        txtNomorPelanggan.doOnTextChanged { text, start, before, count ->
            btnNext.isEnabled = (!text.isNullOrBlank() && text.length>=9)
            ButtonUtils.checkButtonState(btnNext, finpayTheme)
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
                transNumber!!,
                this@TelkomActivity,
                txtNomorPelanggan.text.toString(),
                ProductCode.TELKOM,
                "", {
                    val intent = Intent(this, TelkomResultActivity::class.java)
                    intent.putExtra("noPelanggan", txtNomorPelanggan.text.toString())
                    intent.putExtra("customerName", it.bit61Parse?.customerName)
                    intent.putExtra("customerId", it.bit61Parse?.customerId)
                    intent.putExtra("tagihan", it.bit61Parse?.billInfo1?.nilaiTagihan)
                    intent.putExtra("nomorReferensi", it.bit61Parse?.billInfo1?.nomorReferensi)
                    var fee: String = "0"
                    for (data in it.fee) {
                        if (data.sof == "mc") {
                            fee = data.fee.toString()
                        }
                    }
                    intent.putExtra("fee", fee)
                    intent.putExtra("transNumber", transNumber!!)
                    intent.putExtra("theme", finpayTheme)
                    startActivity(intent)
                    progressDialog.dismiss()
                }, {
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(this@TelkomActivity, "", it, finpayTheme)
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
                        txtNomorPelanggan.setText(Utils.validateMobileNumber(value))
                        btnNext.isEnabled = (!value.isNullOrBlank() && value.length>=9)
                        ButtonUtils.checkButtonState(btnNext, finpayTheme)
                    } else {
                        DialogUtils.showDialogError(this@TelkomActivity, "", "Format Nomor tidak sesuai", finpayTheme)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}