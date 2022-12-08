package lib.finpay.sdk.uikit.view.ppob.telkomsel

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.model.DetailProductModel
import lib.finpay.sdk.corekit.model.Product
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import java.util.*

class BestTelkomselPackageDetailActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    private lateinit var billingCode: EditText
    private lateinit var btnNext: Button
    private lateinit var listProduct: Product


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_paket_terbaik_telkomsel_detail)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        billingCode = findViewById(R.id.txt_kode_bayar)
        btnNext = findViewById(R.id.btn_next)

        FinpaySDK.getListProduct(java.util.UUID.randomUUID().toString(), this, {
            listProduct = it
        }, {
            Toast.makeText(this@BestTelkomselPackageDetailActivity, it, Toast.LENGTH_LONG)
        })

        btnBack.setOnClickListener {
            finish()
        }

        billingCode.doOnTextChanged { text, start, before, count ->
            btnNext.isEnabled = (!text.isNullOrBlank() && text.length >= 7)
            ButtonUtils.checkButtonState(btnNext)
        }

        btnNext.setOnClickListener {
            var currentProduct =
                listProduct.dataProduct!!.filter {
                    it.productDesc!!.uppercase(Locale.getDefault()).contains("menuTitle.text")
                } as ArrayList<DetailProductModel>
            FinpaySDK.ppobInquiry(
                java.util.UUID.randomUUID().toString(),
                this,
                billingCode.text.toString(),
                currentProduct.first().productCode!!,
                "",
                {
                    print("${it.statusCode}")
                },
                {
                    print("${it.toString()}")
                })
        }
    }
}