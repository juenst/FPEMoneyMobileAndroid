package lib.finpay.sdk.uikit.view.ppob.revenue

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.view.ppob.instalment.BussanAutoFinanceActivity
import lib.finpay.sdk.uikit.view.ppob.instalment.SmartFinanceActivity

class RevenueActivity : AppCompatActivity() {
    private lateinit var btnBack: ImageView
    private lateinit var lnPnbp: LinearLayout
    private lateinit var lnBeaCukai: LinearLayout
    private lateinit var lnPajakOnline: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revenue)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        lnPnbp = findViewById(R.id.lnPnbp)
        lnBeaCukai = findViewById(R.id.lnBeacukai)
        lnPajakOnline = findViewById(R.id.lnPajakOnline)

        btnBack.setOnClickListener {
            onBackPressed()
        }

        lnPnbp.setOnClickListener {
            val intent = Intent(this, PnbpActivity::class.java)
            startActivity(intent)
        }

        lnBeaCukai.setOnClickListener {
            val intent = Intent(this, BeaCukaiActivity::class.java)
            startActivity(intent)
        }

        lnPajakOnline.setOnClickListener {
            val intent = Intent(this, PajakOnlineActivity::class.java)
            startActivity(intent)
        }
    }
}