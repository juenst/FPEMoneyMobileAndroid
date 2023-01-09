package lib.finpay.sdk.uikit.view.ppob.pulsa_data

import android.app.ProgressDialog
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds
import android.provider.ContactsContract.Contacts
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.model.DataSubProduct
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.Utils
import lib.finpay.sdk.uikit.view.ppob.pulsa_data.adapter.PulsaDataAdapter
import lib.finpay.sdk.uikit.view.upgrade.UpgradeAccountSuccessActivity
import java.util.ArrayList


class PulsaDataActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    lateinit var txtPhoneNumber: EditText
    lateinit var btnNext: Button
    lateinit var btnContact: ImageView
    lateinit var btnBack: ImageView
    lateinit var progressDialog: ProgressDialog

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulsa_data)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        txtPhoneNumber = findViewById(R.id.txt_phone_no)
        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)
        btnContact = findViewById(R.id.btnContact)
        progressDialog = ProgressDialog(this@PulsaDataActivity)

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

        ButtonUtils.checkButtonState(btnNext)
        txtPhoneNumber.doOnTextChanged { text, start, before, count ->
            btnNext.isEnabled = (!text.isNullOrBlank() && text.length>=9)
            ButtonUtils.checkButtonState(btnNext, finpayTheme)
        }

        btnContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI)
            intent.type = CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(intent, 1)
        }

        btnNext.setOnClickListener {
            progressDialog.setTitle("Mohon Menunggu")
            progressDialog.setMessage("Sedang Memuat ...")
            progressDialog.setCancelable(false) // blocks UI interaction
            progressDialog.show()

            val listOpr: ArrayList<String> = ArrayList<String>()
            val provider: String = Utils.getProviderMobile(txtPhoneNumber.text.toString())
            listOpr.add(provider)
            FinpaySDK.getListSubProduct(
                transNumber!!,
                this,
                txtPhoneNumber.text.toString(),
                listOpr, {
                    progressDialog.dismiss()
                    val intent = Intent(this, PulsaDataResultActivity::class.java)
                    intent.putExtra("result", it)
                    intent.putExtra("transNumber", transNumber!!)
                    intent.putExtra("theme", finpayTheme)
                    intent.putExtra("phoneNumber", txtPhoneNumber.text.toString())
                    startActivity(intent)
                }, {
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(this, "", it, finpayTheme)
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
                    arrayOf(CommonDataKinds.Phone.NUMBER),
                    null,
                    null,
                    null
                )
                if (cursor != null && cursor.moveToNext()) {
                    val phone: String = "0"+cursor.getString(0).trimStart('0')
                    if(phone.length>=9) {
                        txtPhoneNumber.setText(Utils.validateMobileNumber(phone))
                        btnNext.isEnabled = (!phone.isNullOrBlank() && phone.length>=9)
                        ButtonUtils.checkButtonState(btnNext, finpayTheme)
                    } else {
                        DialogUtils.showDialogError(this@PulsaDataActivity, "", "Nomor telepon minimal 9 karakter", finpayTheme)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
