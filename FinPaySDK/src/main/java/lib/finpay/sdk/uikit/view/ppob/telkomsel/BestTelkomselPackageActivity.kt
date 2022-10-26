package lib.finpay.sdk.uikit.view.ppob.telkomsel

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R

class BestTelkomselPackageActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    private lateinit var btnPaymentCodePay: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_paket_terbaik_telkomsel)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        btnPaymentCodePay = findViewById(R.id.btn_payment_code_pay)

        btnBack.setOnClickListener {
            finish()
        }

        btnPaymentCodePay.setOnClickListener {
            val intent = Intent(this, BestTelkomselPackageDetailActivity::class.java)
            this.startActivity(intent)
        }
    }
}