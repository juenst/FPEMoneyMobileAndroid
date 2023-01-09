package lib.finpay.sdk.uikit.view.ppob.insurance

import android.app.ProgressDialog
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.view.ppob.insurance.adapter.PeriodAdapter

class TokioMarineActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    private lateinit var txtNomorPelanggan: EditText
    private lateinit var btnContact: ImageView
    private lateinit var btnNext: Button
    private lateinit var btnBack: ImageView
    private lateinit var icDropdown: ImageView
    private lateinit var progressDialog: ProgressDialog

    lateinit var txtPeriode: TextView
    private lateinit var btnChoosePeriodeTime: LinearLayout
    var periodeTime: String = ""

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tokio_marine)

        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        txtNomorPelanggan = findViewById(R.id.txtNomorPelanggan)
        btnContact = findViewById(R.id.btnContact)
        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)
        icDropdown = findViewById(R.id.icDropdown)

        btnChoosePeriodeTime = findViewById(R.id.choosePeriodeTime)
        txtPeriode = findViewById(R.id.selectedPeriodeMonth)


        progressDialog = ProgressDialog(this@TokioMarineActivity)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        icDropdown.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnContact.setColorFilter(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)
        btnNext.setBackgroundColor(if(btnNext.isEnabled()) if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!! else Color.parseColor("#d5d5d5"))

        ButtonUtils.checkButtonState(btnNext, finpayTheme)
        txtNomorPelanggan.doOnTextChanged { text, start, before, count ->
            btnNext.isEnabled = (!text.isNullOrBlank() && text.length >= 9 && periodeTime != "")
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

        btnChoosePeriodeTime.setOnClickListener {
            showDialogMonth()
        }

        btnNext.setOnClickListener {
            progressDialog.setTitle("Mohon Menunggu")
            progressDialog.setMessage("Sedang Memuat ...")
            progressDialog.setCancelable(false)
            progressDialog.show()
            FinpaySDK.ppobInquiry(
                java.util.UUID.randomUUID().toString(),
                this@TokioMarineActivity,
                txtNomorPelanggan.text.toString(),
                ProductCode.INSURANCE_TOKIO,
                "", {
                    progressDialog.dismiss()
                }, {
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(this@TokioMarineActivity, "", it, finpayTheme)
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
                        txtNomorPelanggan.setText(value)
                        btnNext.isEnabled = (!value.isNullOrBlank() && value.length >= 9)
                        ButtonUtils.checkButtonState(btnNext, finpayTheme)
                    } else {
                        DialogUtils.showDialogError(
                            this@TokioMarineActivity,
                            "",
                            "Format Nomor tidak sesuai", finpayTheme
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showDialogMonth() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.dialog_choose_period)
        val listNominal = dialog.findViewById<ListView>(R.id.listPeriod)

        val list = mutableListOf<String>()
        list.add("1 Bulan")
        list.add("1 Tahun")

        listNominal!!.adapter = PeriodAdapter(this@TokioMarineActivity, R.layout.item_period, list)

        listNominal.setOnItemClickListener { adapter, view, position, id ->
            val selectedItem = adapter.getItemAtPosition(position) as String
            txtPeriode.text = selectedItem
            periodeTime = selectedItem
            btnNext.isEnabled =
                (!txtNomorPelanggan.text.isNullOrBlank() && txtNomorPelanggan.text.length >= 9 && periodeTime != "")
            ButtonUtils.checkButtonState(btnNext, finpayTheme)
            dialog.dismiss()
        }

        dialog.show()
    }
}