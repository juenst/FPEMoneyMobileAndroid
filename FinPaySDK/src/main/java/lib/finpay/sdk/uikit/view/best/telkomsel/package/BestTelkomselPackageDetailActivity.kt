package lib.finpay.sdk.uikit.view.best.telkomsel.`package`

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.model.DetailProductModel
import lib.finpay.sdk.corekit.model.ProductModel
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import java.util.*

class BestTelkomselPackageDetailActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    private lateinit var billingCode: EditText
    private lateinit var btnNext: Button
    private lateinit var listProduct: ProductModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_paket_terbaik_telkomsel_detail)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        billingCode = findViewById(R.id.txt_kode_bayar)
        btnNext = findViewById(R.id.btn_next)

        FinpaySDK.getListProduct { values ->
            listProduct = values
        }

        btnBack.setOnClickListener {
            finish()
        }

        billingCode.doOnTextChanged { text, start, before, count ->
            btnNext.isEnabled = (!text.isNullOrBlank() && text.length >= 7)
            ButtonUtils.checkButtonState(btnNext)
        }

        btnNext.setOnClickListener {
            var currentProduct =
                listProduct.getDataProduct()!!.filter {
                    it.getProductDesc()!!.uppercase(Locale.getDefault()).contains("menuTitle.text")
                } as ArrayList<DetailProductModel>
            FinpaySDK.ppobInquiry(
                this,
                billingCode.text.toString(),
                currentProduct.first().getProductCode()!!,
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