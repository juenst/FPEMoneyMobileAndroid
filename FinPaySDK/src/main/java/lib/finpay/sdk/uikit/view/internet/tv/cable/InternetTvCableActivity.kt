package lib.finpay.sdk.uikit.view.internet.tv.cable

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R

class InternetTvCableActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    private lateinit var btnIndiHome: LinearLayout
    private lateinit var btnMNCvision: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_internet_tv_cable)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        btnIndiHome = findViewById(R.id.btn_indihome)
        btnMNCvision = findViewById(R.id.btn_mnc_vision)

        btnBack.setOnClickListener {
            finish()
        }

        btnIndiHome.setOnClickListener {
            val intent = Intent(this, InternetTvCableDetailActivity::class.java)
            intent.putExtra("type", "IndiHome")
            this.startActivity(intent)
        }

        btnMNCvision.setOnClickListener {
            val intent = Intent(this, InternetTvCableDetailActivity::class.java)
            intent.putExtra("type", "MNC Vision")
            this.startActivity(intent)
        }
    }
}