package lib.finpay.sdk.uikit.view.ppob.pdam

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.model.DetailProductModel
import lib.finpay.sdk.corekit.model.Product
import java.util.*

class PDAMActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    private lateinit var searchRegion: EditText
    private lateinit var customerNumber: EditText
    private lateinit var btnNext: Button
    private lateinit var listProduct: Product


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

        FinpaySDK.getListProduct(this, {
            listProduct = it
        }, {
            Toast.makeText(this@PDAMActivity, it, Toast.LENGTH_LONG)
        })

        searchRegion.setOnClickListener {
            val intent = Intent(this, PDAMSearchRegionActivity::class.java)
            this.startActivity(intent)
        }

        btnNext.setOnClickListener {
            var currentProduct =
                listProduct.dataProduct!!.filter {
                    it.productDesc!!.uppercase(Locale.getDefault()).contains("PDAM")
                } as ArrayList<DetailProductModel>
            FinpaySDK.ppobInquiry(
                this,
                customerNumber.text.toString(),
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