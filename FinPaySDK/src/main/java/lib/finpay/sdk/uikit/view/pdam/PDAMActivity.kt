package lib.finpay.sdk.uikit.view.pdam

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.model.DetailProductModel
import lib.finpay.sdk.corekit.model.ProductModel
import java.util.*

class PDAMActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    private lateinit var searchRegion: EditText
    private lateinit var customerNumber: EditText
    private lateinit var btnNext: Button
    private lateinit var listProduct: ProductModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_pdam)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        btnNext = findViewById(R.id.btn_next)
        searchRegion = findViewById(R.id.txt_region)
        customerNumber = findViewById(R.id.txt_customer_number)

        btnBack.setOnClickListener {
            finish()
        }

        FinpaySDK.getListProduct { values ->
            listProduct = values
        }

        searchRegion.setOnClickListener {
            val intent = Intent(this, PDAMSearchRegionActivity::class.java)
            this.startActivity(intent)
        }

        btnNext.setOnClickListener {
            var currentProduct =
                listProduct.getDataProduct()!!.filter {
                    it.getProductDesc()!!.uppercase(Locale.getDefault()).contains("PDAM")
                } as ArrayList<DetailProductModel>
            FinpaySDK.ppobInquiry(
                this,
                customerNumber.text.toString(),
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