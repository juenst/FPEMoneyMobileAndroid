package lib.finpay.sdk.uikit.view.ppob.internettvcable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import lib.finpay.sdk.R

class InternetTvCableActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    lateinit var lnIndihome: LinearLayout
    lateinit var lnMNCVision: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet_tv_cable)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        lnIndihome = findViewById(R.id.lnIndihome)
        lnMNCVision = findViewById(R.id.lnMNCVision)

        btnBack.setOnClickListener {
            onBackPressed()
        }

        lnIndihome.setOnClickListener {
            val intent = Intent(this, IndihomeActivity::class.java)
            startActivity(intent)
        }

        lnMNCVision.setOnClickListener {
            val intent = Intent(this, MNCActivity::class.java)
            startActivity(intent)
        }
    }
}