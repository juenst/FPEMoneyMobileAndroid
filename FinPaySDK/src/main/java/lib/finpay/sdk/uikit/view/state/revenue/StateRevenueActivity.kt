package lib.finpay.sdk.uikit.view.state.revenue

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R

class StateRevenueActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    private lateinit var btnPnbp: LinearLayout
    private lateinit var btnBeaCukai: LinearLayout
    private lateinit var btnPajakOnline: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_state_revenue)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        btnPnbp = findViewById(R.id.btn_pnbp)
        btnBeaCukai = findViewById(R.id.btn_bea_cukai)
        btnPajakOnline = findViewById(R.id.btn_pajak_online)
        btnBack.setOnClickListener {
            finish()
        }

        btnPnbp.setOnClickListener {
            val intent = Intent(this, StateRevenueDetailActivity::class.java)
            intent.putExtra("type", "PNBP")
            this.startActivity(intent)
        }

        btnBeaCukai.setOnClickListener {
            val intent = Intent(this, StateRevenueDetailActivity::class.java)
            intent.putExtra("type", "BEA CUKAI")
            this.startActivity(intent)
        }

        btnPajakOnline.setOnClickListener {
            val intent = Intent(this, StateRevenueDetailActivity::class.java)
            intent.putExtra("type", "PAJAK ONLINE")
            this.startActivity(intent)
        }
    }
}