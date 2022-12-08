package lib.finpay.sdk.uikit.view.ppob.voucher

import android.app.ProgressDialog
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.*
import androidx.core.widget.doOnTextChanged
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.view.ppob.bpjs.adapter.MonthAdapter
import lib.finpay.sdk.uikit.view.ppob.voucher.adapter.VoucherAdapter
import java.text.SimpleDateFormat
import java.util.*

class KvisionActivity : AppCompatActivity() {
    lateinit var txtNomorPelanggan: EditText
    lateinit var btnContact: ImageView
    lateinit var btnNext: Button
    lateinit var btnChooseVoucher: LinearLayout
    lateinit var txtVoucher:TextView
    lateinit var progressDialog: ProgressDialog
    var voucher:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voucher_tv_kabel_kvision)
        supportActionBar!!.hide()

        txtNomorPelanggan = findViewById(R.id.txtNomorPelanggan)
        btnContact = findViewById(R.id.btnContact)
        btnNext = findViewById(R.id.btnNext)
        btnChooseVoucher = findViewById(R.id.chooseVoucher)
        txtVoucher = findViewById(R.id.selectedVoucher)
        progressDialog = ProgressDialog(this@KvisionActivity)

        ButtonUtils.checkButtonState(btnNext)
        txtNomorPelanggan.doOnTextChanged { text, start, before, count ->
            btnNext.isEnabled = (!text.isNullOrBlank() && text.length>=9 && voucher != "")
            ButtonUtils.checkButtonState(btnNext)
        }

        btnContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(intent, 1)
        }

        btnChooseVoucher.setOnClickListener {
            showDialogVoucher()
        }

        btnNext.setOnClickListener {
            progressDialog.setTitle("Mohon Menunggu")
            progressDialog.setMessage("Sedang Memuat ...")
            progressDialog.setCancelable(false)
            progressDialog.show()
            FinpaySDK.ppobInquiry(
                java.util.UUID.randomUUID().toString(),
                this@KvisionActivity,
                txtNomorPelanggan.text.toString(),
                ProductCode.BPJS_KESEHATAN,
                "", {
                    progressDialog.dismiss()
                }, {
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(this@KvisionActivity, "", it)
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
                        btnNext.isEnabled = (!value.isNullOrBlank() && value.length>=9 && voucher != "")
                        ButtonUtils.checkButtonState(btnNext)
                    } else {
                        DialogUtils.showDialogError(this@KvisionActivity, "", "Format Nomor tidak sesuai")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun showDialogVoucher() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.dialog_choose_voucher)
        val listVoucher = dialog.findViewById<ListView>(R.id.listVoucher)

        val list = arrayListOf<String>()
        list.add("KVISION 50 (Rp50.000")
        list.add("KVISION 75 (Rp75.000")
        list.add("KVISION 100 (Rp100.000")
        listVoucher!!.adapter = VoucherAdapter(this@KvisionActivity, R.layout.item_voucher, list)
        listVoucher.setOnItemClickListener { adapter, view, position, id ->
            val selectedItem = adapter.getItemAtPosition(position) as String
            txtVoucher.text = selectedItem
            voucher = selectedItem
            btnNext.isEnabled = (!txtNomorPelanggan.text.isNullOrBlank() && txtNomorPelanggan.text.length>=9 && voucher != "")
            ButtonUtils.checkButtonState(btnNext)
            dialog.dismiss()
        }
        dialog.show()
    }
}