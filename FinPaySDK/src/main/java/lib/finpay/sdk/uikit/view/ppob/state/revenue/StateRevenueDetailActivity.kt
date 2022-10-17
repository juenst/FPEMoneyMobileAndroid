package lib.finpay.sdk.uikit.view.ppob.state.revenue

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.model.DetailProductModel
import lib.finpay.sdk.corekit.model.Product
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import java.util.*

class StateRevenueDetailActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    private lateinit var menuTitle: TextView
    private lateinit var billingCode: EditText
    private lateinit var btnNext: Button
    private lateinit var listProduct: Product


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_state_revenue_detail)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        menuTitle = findViewById(R.id.state_revenue_menu_title)
        billingCode = findViewById(R.id.txt_billing_code)
        btnNext = findViewById(R.id.btn_next)

        val extras = intent.extras
        if (extras != null) {
            val value = extras.getString("type")
            if (value != null) {
                menuTitle.text = value.uppercase(Locale.getDefault())
            }
        }

        FinpaySDK.getListProduct(this, {
            listProduct = it
        }, {
            Toast.makeText(this@StateRevenueDetailActivity, it, Toast.LENGTH_LONG)
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
                    it.productDesc!!.uppercase(Locale.getDefault()).contains(menuTitle.text)
                } as ArrayList<DetailProductModel>
            FinpaySDK.ppobInquiry(
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