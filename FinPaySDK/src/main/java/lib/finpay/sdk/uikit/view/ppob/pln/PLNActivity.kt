package lib.finpay.sdk.uikit.view.ppob.pln

import android.app.ProgressDialog
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.widget.doOnTextChanged
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.TextUtils
import lib.finpay.sdk.uikit.view.ppob.pln.adapter.NominalAdapter

class PLNActivity: AppCompatActivity() {
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
    lateinit var btnContactTagihan: ImageView
    lateinit var progressDialog: ProgressDialog
    var nominal: String = "0"

    //nomor testing token 512233350020

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pln)
        supportActionBar!!.hide()

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

        cardToken.setBackgroundColor(Integer.parseUnsignedInt("FFFFFFFF", 16))
        cardTagihan.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))
        contentToken.visibility = View.VISIBLE
        contentTagihan.visibility = View.GONE

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

        ButtonUtils.checkButtonState(btnNextToken)
        ButtonUtils.checkButtonState(btnNextTagihan)
        txtNomorPelangganToken.doOnTextChanged { text, start, before, count ->
            btnNextToken.isEnabled = (!text.isNullOrBlank() && text.length>=9 && nominal != "0")
            ButtonUtils.checkButtonState(btnNextToken)
        }
        txtNomorPelangganTagihan.doOnTextChanged { text, start, before, count ->
            btnNextTagihan.isEnabled = (!text.isNullOrBlank() && text.length>=9)
            ButtonUtils.checkButtonState(btnNextTagihan)
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
                this@PLNActivity,
                txtNomorPelangganToken.text.toString(),
                ProductCode.PLN_PREPAID,
                nominal, {
                    val intent = Intent(this, PLNResultActivity::class.java)
//                    intent.putExtra("result", it)
                    intent.putExtra("noPelanggan", txtNomorPelangganToken.text)
                    intent.putExtra("customerName", it.bit61Parse?.customerName)
                    intent.putExtra("customerId", it.bit61Parse?.customerId)
                    intent.putExtra("tagihan", it.tagihan)
                    intent.putExtra("nomorReferensi", it.bit61Parse?.billInfo1?.nomorReferensi)
                    var fee: String = "0"
                    for (data in it.fee) {
                        if (data.sof == "mc") {
                            fee = data.fee.toString()
                        }
                    }
                    intent.putExtra("fee", it.fee)
                    startActivity(intent)
                    progressDialog.dismiss()
                }, {
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(this@PLNActivity, "", it)
                }
            )
        }

        btnNextTagihan.setOnClickListener {
            progressDialog.setTitle("Mohon Menunggu")
            progressDialog.setMessage("Sedang Memuat ...")
            progressDialog.setCancelable(false)
            progressDialog.show()
            FinpaySDK.ppobInquiry(
                this@PLNActivity,
                txtNomorPelangganTagihan.text.toString(),
                ProductCode.PLN_POSTPAID,
                "", {
                    progressDialog.dismiss()
                }, {
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(this@PLNActivity, "", it)
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
                        txtNomorPelangganToken.setText(value)
                        btnNextToken.isEnabled = (!value.isNullOrBlank() && value.length>=9 && nominal != "0")
                        ButtonUtils.checkButtonState(btnNextToken)
                    } else {
                        DialogUtils.showDialogError(this, "", "Format nomor tidak sesuai")
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
                    if(value.length>=9) {
                        txtNomorPelangganTagihan.setText(value)
                        btnNextTagihan.isEnabled = (!value.isNullOrBlank() && value.length>=9)
                        ButtonUtils.checkButtonState(btnNextTagihan)
                    } else {
                        DialogUtils.showDialogError(this, "", "Format nomor tidak sesuai")
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
            btnNextToken.isEnabled = (!txtNomorPelangganToken.text.isNullOrBlank() && txtNomorPelangganToken.text.length>=9 && nominal != "")
            ButtonUtils.checkButtonState(btnNextToken)
            dialog.dismiss()
        }
        dialog.show()
    }
}