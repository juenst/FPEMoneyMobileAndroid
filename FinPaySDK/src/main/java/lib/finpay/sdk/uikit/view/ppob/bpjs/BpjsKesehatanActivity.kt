package lib.finpay.sdk.uikit.view.ppob.bpjs

import android.app.ProgressDialog
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.corekit.model.DataSubProduct
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.view.ppob.bpjs.adapter.BPJSAdapter
import lib.finpay.sdk.uikit.view.ppob.bpjs.adapter.MonthAdapter
import lib.finpay.sdk.uikit.view.ppob.pascabayar.PascaBayarResultActivity
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.time.ZoneId
import java.util.Calendar

class BpjsKesehatanActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    lateinit var txtNomorPelanggan: EditText
    lateinit var btnContact: ImageView
    lateinit var btnNext: Button
    lateinit var btnChoosePeriodeTime: LinearLayout
    lateinit var txtPeriode:TextView
    lateinit var btnBack: ImageView
    lateinit var icDropdown: ImageView
    lateinit var progressDialog: ProgressDialog
    var periodeTime:String = ""

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bpjs_kesehatan)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)

        txtNomorPelanggan = findViewById(R.id.txtNomorPelanggan)
        btnContact = findViewById(R.id.btnContact)
        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)
        btnChoosePeriodeTime = findViewById(R.id.choosePeriodeTime)
        txtPeriode = findViewById(R.id.selectedPeriodeMonth)
        progressDialog = ProgressDialog(this@BpjsKesehatanActivity)
        icDropdown = findViewById(R.id.icDropdown)

        ButtonUtils.checkButtonState(btnNext, finpayTheme)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        icDropdown.setColorFilter(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)
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

        txtNomorPelanggan.doOnTextChanged { text, start, before, count ->
            btnNext.isEnabled = (!text.isNullOrBlank() && text.length>=9 && periodeTime != "")
            ButtonUtils.checkButtonState(btnNext, finpayTheme)
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
                transNumber!!,
                this@BpjsKesehatanActivity,
                txtNomorPelanggan.text.toString(),
                ProductCode.BPJS_KESEHATAN,
                "", {
                    val intent = Intent(this, BpjsResultActivity::class.java)
                    intent.putExtra("noPelanggan", txtNomorPelanggan.text.toString())
                    intent.putExtra("customerName", it.bit61Parse?.customerName)
                    intent.putExtra("customerId", it.bit61Parse?.customerId)
                    intent.putExtra("tagihan", it.tagihan.toString())
                    intent.putExtra("nomorReferensi", it.conf)
                    var fee: String = "0"
                    for (data in it.fee) {
                        if (data.sof == "mc") {
                            fee = data.fee.toString()
                        }
                    }
                    intent.putExtra("fee", fee)
                    intent.putExtra("periode", txtPeriode.text.toString())
                    intent.putExtra("transNumber", transNumber!!)
                    intent.putExtra("theme", finpayTheme)
                    startActivity(intent)
                    progressDialog.dismiss()
                }, {
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(this@BpjsKesehatanActivity, "", it, finpayTheme)
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
                        btnNext.isEnabled = (!value.isNullOrBlank() && value.length>=9 && periodeTime != "")
                        ButtonUtils.checkButtonState(btnNext, finpayTheme)
                    } else {
                        DialogUtils.showDialogError(this@BpjsKesehatanActivity, "", "Format Nomor tidak sesuai", finpayTheme)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun showDialogMonth() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.dialog_choose_month)
        val listMonth = dialog.findViewById<ListView>(R.id.listMonth)

        val formatter = SimpleDateFormat("MMMM yyyy")
        val list = arrayListOf<String>()
        Calendar.getInstance().let { calendar ->
            calendar.add(Calendar.MONTH, 0)
            for (i in 0 until 12) {
                list.add(formatter.format(calendar.timeInMillis))
                calendar.add(Calendar.MONTH, 1)
            }
        }
        listMonth!!.adapter = MonthAdapter(this@BpjsKesehatanActivity, R.layout.item_month, list)
        listMonth.setOnItemClickListener { adapter, view, position, id ->
            val selectedItem = adapter.getItemAtPosition(position) as String
            txtPeriode.text = selectedItem
            periodeTime = selectedItem
            btnNext.isEnabled = (!txtNomorPelanggan.text.isNullOrBlank() && txtNomorPelanggan.text.length>=9 && periodeTime != "")
            ButtonUtils.checkButtonState(btnNext, finpayTheme)
            dialog.dismiss()
        }
        dialog.show()
    }
}