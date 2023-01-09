package lib.finpay.sdk.uikit.view.ppob.pln

import android.app.ProgressDialog
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.widget.doOnTextChanged
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.TextUtils
import lib.finpay.sdk.uikit.view.ppob.pln.adapter.NominalAdapter

class PLNActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    lateinit var txtNomorPelangganToken: EditText
    lateinit var txtNomorPelangganTagihan: EditText
    lateinit var btnNextToken: Button
    lateinit var btnNextTagihan: Button
    lateinit var chooseNominal: LinearLayout
    lateinit var selectedNominal: TextView
    lateinit var cardToken: CardView
    lateinit var cardTagihan: CardView
    lateinit var contentToken: LinearLayout
    lateinit var contentTagihan: LinearLayout
    lateinit var btnContact: ImageView
    lateinit var icDropdown: ImageView
    lateinit var btnContactTagihan: ImageView
    lateinit var progressDialog: ProgressDialog
    var nominal: String = "0"
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    //nomor testing token 512233350020
    //nomor testing tagihan 512233350072

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pln)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        txtNomorPelangganToken = findViewById(R.id.txtNomorPelangganToken)
        txtNomorPelangganTagihan = findViewById(R.id.txtNomorPelangganTagihan)
        btnNextToken = findViewById(R.id.btnNext)
        btnNextTagihan = findViewById(R.id.btnNextTagihan)
        chooseNominal = findViewById(R.id.chooseNominal)
        selectedNominal = findViewById(R.id.selectedNominal)
        cardToken = findViewById(R.id.cardToken)
        cardTagihan = findViewById(R.id.cardTagihan)
        contentToken = findViewById(R.id.contentToken)
        contentTagihan = findViewById(R.id.contentTagihan)
        btnContact = findViewById(R.id.btnContact)
        btnContactTagihan = findViewById(R.id.btnContactTagihan)
        progressDialog = ProgressDialog(this@PLNActivity)
        btnBack = findViewById(R.id.btnBack)
        icDropdown = findViewById(R.id.icDropdown)

        cardToken.setBackgroundColor(Integer.parseUnsignedInt("FFFFFFFF", 16))
        cardTagihan.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))
        contentToken.visibility = View.VISIBLE
        contentTagihan.visibility = View.GONE

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        icDropdown.setColorFilter(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)
        btnContact.setColorFilter(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)
        btnContactTagihan.setColorFilter(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)
        btnNextToken.setBackgroundColor(if(btnNextToken.isEnabled()) if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!! else Color.parseColor("#d5d5d5"))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if(finpayTheme?.getAppBarBackgroundColor() == null) {
                window.setStatusBarColor(Color.parseColor("#333333"))
            } else {
                window.setStatusBarColor(finpayTheme?.getAppBarBackgroundColor()!!)
            }
        }

        btnBack.setOnClickListener {
            finish()
        }

        cardToken.setOnClickListener {
            cardToken.setBackgroundColor(Integer.parseUnsignedInt("FFFFFFFF", 16))
            cardTagihan.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))
            contentToken.visibility = View.VISIBLE
            contentTagihan.visibility = View.GONE
        }

        cardTagihan.setOnClickListener {
            cardToken.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))
            cardTagihan.setBackgroundColor(Integer.parseUnsignedInt("FFFFFFFF", 16))
            contentToken.visibility = View.GONE
            contentTagihan.visibility = View.VISIBLE
        }

        ButtonUtils.checkButtonState(btnNextToken, finpayTheme)
        ButtonUtils.checkButtonState(btnNextTagihan, finpayTheme)
        txtNomorPelangganToken.doOnTextChanged { text, start, before, count ->
            btnNextToken.isEnabled = (!text.isNullOrBlank() && text.length >= 9 && nominal != "0")
            ButtonUtils.checkButtonState(btnNextToken, finpayTheme)
        }
        txtNomorPelangganTagihan.doOnTextChanged { text, start, before, count ->
            btnNextTagihan.isEnabled = (!text.isNullOrBlank() && text.length >= 9)
            ButtonUtils.checkButtonState(btnNextTagihan, finpayTheme)
        }

        chooseNominal.setOnClickListener {
            showDialogMonth()
        }

        btnContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(intent, 1)
        }

        btnContactTagihan.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(intent, 2)
        }


        btnNextToken.setOnClickListener {
            progressDialog.setTitle("Mohon Menunggu")
            progressDialog.setMessage("Sedang Memuat ...")
            progressDialog.setCancelable(false)
            progressDialog.show()
            FinpaySDK.ppobInquiry(
                transNumber!!,
                this@PLNActivity,
                txtNomorPelangganToken.text.toString(),
                ProductCode.PLN_PREPAID,
                nominal, {
                    val intent = Intent(this, PLNResultActivity::class.java)
                    intent.putExtra("result", it)
                    intent.putExtra("transNumber", transNumber)
                    intent.putExtra("theme", finpayTheme)
                    intent.putExtra("productCode", ProductCode.PLN_PREPAID)
                    startActivity(intent)
                    progressDialog.dismiss()
                }, {
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(this@PLNActivity, "", it, finpayTheme)
                }
            )
        }

        btnNextTagihan.setOnClickListener {
            progressDialog.setTitle("Mohon Menunggu")
            progressDialog.setMessage("Sedang Memuat ...")
            progressDialog.setCancelable(false)
            progressDialog.show()
            FinpaySDK.ppobInquiry(
                transNumber!!,
                this@PLNActivity,
                txtNomorPelangganTagihan.text.toString(),
                ProductCode.PLN_POSTPAID,
                "", {
                    val intent = Intent(this, PLNResultActivity::class.java)
                    intent.putExtra("noPelanggan", txtNomorPelangganToken.text.toString())
                    intent.putExtra("result", it)
                    intent.putExtra("transNumber", transNumber!!)
                    intent.putExtra("theme", finpayTheme)
                    var fee: String = "0"
                    for (data in it.fee) {
                        if (data.sof == "mc") {
                            fee = data.fee.toString()
                        }
                    }
                    intent.putExtra("fee", fee)
                    intent.putExtra("transNumber", transNumber)
                    intent.putExtra("theme", finpayTheme)
                    startActivity(intent)
                    progressDialog.dismiss()
                }, {
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(this@PLNActivity, "", it, finpayTheme)
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
                    if (value.length >= 9) {
                        txtNomorPelangganToken.setText(value)
                        btnNextToken.isEnabled =
                            (!value.isNullOrBlank() && value.length >= 9 && nominal != "0")
                        ButtonUtils.checkButtonState(btnNextToken, finpayTheme)
                    } else {
                        DialogUtils.showDialogError(this, "", "Format nomor tidak sesuai", finpayTheme)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
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
                    if (value.length >= 9) {
                        txtNomorPelangganTagihan.setText(value)
                        btnNextTagihan.isEnabled = (!value.isNullOrBlank() && value.length >= 9)
                        ButtonUtils.checkButtonState(btnNextTagihan, finpayTheme)
                    } else {
                        DialogUtils.showDialogError(this, "", "Format nomor tidak sesuai", finpayTheme)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun showDialogMonth() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.dialog_choose_nominal_token)
        val listNominal = dialog.findViewById<ListView>(R.id.listNominal)

        var list = mutableListOf<String>()
        list.add("20000")
        list.add("50000")
        list.add("100000")
        list.add("200000")
        list.add("500000")
        list.add("1000000")
        list.add("5000000")
        listNominal!!.adapter = NominalAdapter(this@PLNActivity, R.layout.item_nominal_token, list)
        listNominal.setOnItemClickListener { adapter, view, position, id ->
            val selectedItem = adapter.getItemAtPosition(position) as String
            selectedNominal.text = TextUtils.formatRupiah(selectedItem.toDouble()).replace("Rp", "")
            nominal = selectedItem
            btnNextToken.isEnabled =
                (!txtNomorPelangganToken.text.isNullOrBlank() && txtNomorPelangganToken.text.length >= 9 && nominal != "")
            ButtonUtils.checkButtonState(btnNextToken, finpayTheme)
            dialog.dismiss()
        }
        dialog.show()
    }
}