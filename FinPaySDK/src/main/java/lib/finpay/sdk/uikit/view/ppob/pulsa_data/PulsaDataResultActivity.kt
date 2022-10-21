package lib.finpay.sdk.uikit.view.ppob.pulsa_data

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.corekit.model.DataSubProduct
import lib.finpay.sdk.uikit.constant.PaymentType
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.TextUtils
import lib.finpay.sdk.uikit.utilities.Utils
import lib.finpay.sdk.uikit.view.payment.PaymentActivity
import lib.finpay.sdk.uikit.view.ppob.pulsa_data.adapter.PulsaDataAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class PulsaDataResultActivity : AppCompatActivity() {
    lateinit var txtPhoneNumber: TextView
    lateinit var btnNext: Button
    lateinit var listDenom: GridView
    lateinit var cardPulsa: CardView
    lateinit var cardData: CardView
    lateinit var logo: ImageView
    lateinit var progressDialog: ProgressDialog
    lateinit var content: LinearLayout
    lateinit var emptyState: LinearLayout
    var dataSubProduct = mutableListOf<DataSubProduct>()
    val phoneNumber: String? by lazy { intent.getStringExtra("phoneNumber") }
    var denom: String = "0"
    var price: String = "0"
    var saldo: String = "0"
    var subProductCode: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulsa_data_result)
        supportActionBar!!.hide()

        txtPhoneNumber = findViewById(R.id.txtPhoneNumber)
        btnNext = findViewById(R.id.btnNext)
        logo = findViewById(R.id.logoProvider)
        listDenom = findViewById(R.id.listDenom)
        cardPulsa = findViewById(R.id.cardPulsa)
        cardData = findViewById(R.id.cardData)
        content = findViewById(R.id.lnContent)
        emptyState = findViewById(R.id.emptyState)
        progressDialog = ProgressDialog(this@PulsaDataResultActivity)

        cardPulsa.setBackgroundColor(Integer.parseUnsignedInt("FFFFFFFF", 16))
        cardData.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))

        cardPulsa.setOnClickListener {
            cardPulsa.setBackgroundColor(Integer.parseUnsignedInt("FFFFFFFF", 16))
            cardData.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))
        }

        cardData.setOnClickListener {
            cardPulsa.setBackgroundColor(Integer.parseUnsignedInt("FFEEF2F6", 16))
            cardData.setBackgroundColor(Integer.parseUnsignedInt("FFFFFFFF", 16))
        }

        ButtonUtils.checkButtonState(btnNext)

        listDenom.setOnItemClickListener{adapter, view, position, id ->
            val selectedItem = adapter.getItemAtPosition(position) as DataSubProduct

            subProductCode = selectedItem.provider.split("/")[1]
            denom = selectedItem.denom.split("/")[0]
            price = selectedItem.denom.split("/")[1]
            for ((index, value) in dataSubProduct.withIndex()) {
                listDenom.getChildAt(index).setBackgroundColor(Color.TRANSPARENT)
            }
            listDenom.getChildAt(listDenom.getItemIdAtPosition(position).toInt()).setBackgroundColor(Color.parseColor("#CCEEF1"))

            btnNext.isEnabled = subProductCode != null
            ButtonUtils.checkButtonState(btnNext)
        }

        btnNext.setOnClickListener {
            showDialogConfirmPayment(denom, price, "0", saldo)
        }
        txtPhoneNumber.text = "Topup ke nomor "+phoneNumber
        getDenom()
    }

    fun getUserBallance() {
        FinpaySDK.getUserBallance(this@PulsaDataResultActivity, {
            saldo = it.amount!!
        },{
            Toast.makeText(this@PulsaDataResultActivity, it, Toast.LENGTH_LONG)
        })
    }

    fun getDenom() {
//        dataSubProduct.add(DataSubProduct("12000/12000","INDOSAT/01400812000", "Pulsa INDOSAT12000"))
//        dataSubProduct.add(DataSubProduct("15000/16500","TELKOMSEL/01010415000", "Pulsa TELKOMSEL15000"))
//        dataSubProduct.add(DataSubProduct("25000/26500","TELKOMSEL/01010425000", "Pulsa TELKOMSEL25000"))
//        dataSubProduct.add(DataSubProduct("75000/76500","TELKOMSEL/01010475000", "Pulsa TELKOMSEL75000"))
//        listDenom.adapter = PulsaDataAdapter(this, R.layout.item_pulsa_data, dataSubProduct)
//
//        val provider: String = Utils.getProviderMobile(phoneNumber!!)
//        if(provider == "TELKOMSEL") {
//            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.ic_logo_telkomsel))
//        } else if(provider == "XL") {
//            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.ic_logo_xl))
//        } else if(provider == "AXIS") {
//            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.ic_logo_axis))
//        } else if(provider == "INDOSAT") {
//            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.ic_logo_indosat))
//        } else if(provider == "THREE") {
//            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.ic_logo_three))
//        } else {
//            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.icon_top_qr))
//        }
//        if(dataSubProduct.isEmpty() || dataSubProduct.count() == 0){
//            content.visibility = View.GONE
//            emptyState.visibility = View.VISIBLE
//        }  else {
//            content.visibility = View.VISIBLE
//            emptyState.visibility = View.GONE
//        }


        val listOpr: ArrayList<String> = ArrayList<String>()
        val provider: String = Utils.getProviderMobile(phoneNumber!!)
        if(provider == "TELKOMSEL") {
            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.ic_logo_telkomsel))
        } else if(provider == "XL") {
            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.ic_logo_xl))
        } else if(provider == "AXIS") {
            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.ic_logo_axis))
        } else if(provider == "INDOSAT") {
            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.ic_logo_indosat))
        } else if(provider == "THREE") {
            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.ic_logo_three))
        } else {
            logo.setImageDrawable(this@PulsaDataResultActivity.resources.getDrawable(R.drawable.icon_top_qr))
        }
        listOpr.add(provider)

        progressDialog.setTitle("Mohon Menunggu")
        progressDialog.setMessage("Sedang Memuat ...")
        progressDialog.setCancelable(false)
        progressDialog.show()
        FinpaySDK.getListSubProduct(
            this@PulsaDataResultActivity,
            phoneNumber!!, listOpr, {
                listDenom.adapter = PulsaDataAdapter(this, R.layout.item_pulsa_data, it.dataSubProduct)
                if(it.dataSubProduct.isEmpty() || it.dataSubProduct.count() == 0) {
                    content.visibility = View.GONE
                    emptyState.visibility = View.VISIBLE
                } else {
                    content.visibility = View.VISIBLE
                    emptyState.visibility = View.GONE
                }
                getUserBallance()
                progressDialog.dismiss()
            }, {
                progressDialog.dismiss()
                DialogUtils.showDialogError(this@PulsaDataResultActivity, "", it)
            }
        )
    }

    fun showDialogConfirmPayment(
        denom: String,
        price: String,
        biayaLayanan: String,
        saldo: String
    ) {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.dialog_confirm_payment_ppob)
        val btnPay = dialog.findViewById<CardView>(R.id.btnPay)
        val txtBtnPay = dialog.findViewById<TextView>(R.id.txtBtnPay)
        val txtDenom = dialog.findViewById<TextView>(R.id.txtDenom)
        val txtPrice = dialog.findViewById<TextView>(R.id.txtPrice)
        val txtBiayaLayanan= dialog.findViewById<TextView>(R.id.txtBiayaLayanan)
        val txtTotalBayar= dialog.findViewById<TextView>(R.id.txtTotalBayar)
        val txtSaldo= dialog.findViewById<TextView>(R.id.saldo)
        val cardWarning= dialog.findViewById<CardView>(R.id.cardWarning)

        if(saldo.toInt() < (price.toInt() + biayaLayanan.toInt())){
            cardWarning!!.visibility = View.VISIBLE
            txtBtnPay!!.text = "Isi Saldo"
        }

        txtDenom!!.text = "Pulsa Denom "+TextUtils.formatRupiah(denom.toDouble())
        txtPrice!!.text = TextUtils.formatRupiah(price.toDouble())
        txtBiayaLayanan!!.text = TextUtils.formatRupiah(biayaLayanan.toDouble())
        txtTotalBayar!!.text = TextUtils.formatRupiah((price.toInt() + biayaLayanan.toInt()).toDouble())
        txtSaldo!!.text = TextUtils.formatRupiah(saldo.toDouble())

        btnPay?.setOnClickListener {
            if(saldo.toInt() < (price.toInt() + biayaLayanan.toInt())){
                //open top up
            } else {
                progressDialog.setTitle("Mohon Menunggu")
                progressDialog.setMessage("Sedang Memuat ...")
                progressDialog.setCancelable(false) // blocks UI interaction
                progressDialog.show()
                val sdf = SimpleDateFormat("yyyyMMdHHmmss")
                val currentDate = sdf.format(Date())
                FinpaySDK.ppobInquiry(
                    this@PulsaDataResultActivity,
                    phoneNumber!!,
                    ProductCode.PULSA_DATA,
                    price,
                    {
                        val reffId: String = it.bit61Parse?.billInfo1?.nomorReferensi!!
                        var fee: String = "0"
                        for(data in it.fee) {
                            if(data.sof == "mc") {
                                fee = data.fee.toString()
                            }
                        }
                        FinpaySDK.authPin(
                            this@PulsaDataResultActivity,
                            price, ProductCode.PULSA_DATA,{
                                progressDialog.dismiss()
                                val intent = Intent(this@PulsaDataResultActivity, PaymentActivity::class.java)
                                intent.putExtra("paymentType", PaymentType.paymentPulsaData)
                                intent.putExtra("sof", "mc")
                                intent.putExtra("amount", price.toInt() + biayaLayanan.toInt())
                                intent.putExtra("amountTips", fee)
                                intent.putExtra("reffFlag", reffId)
                                intent.putExtra("billingId", phoneNumber)
                                intent.putExtra("productCode", ProductCode.PULSA_DATA)
                                intent.putExtra("activationDate", currentDate)
                                intent.putExtra("payType", "billpayment")
                                intent.putExtra("widgetURL", it.widgetURL)
                                startActivity(intent)
                            }, {
                                progressDialog.dismiss()
                                DialogUtils.showDialogError(this@PulsaDataResultActivity, "", it)
                            }
                        )
                    },{
                        progressDialog.dismiss()
                        DialogUtils.showDialogError(this@PulsaDataResultActivity, "", it)
                    }
                )
            }
        }
        dialog.show()
    }
}