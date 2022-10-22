package lib.finpay.sdk.uikit.view.ppob.insurance

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R

class InsuranceActivity : AppCompatActivity() {
    private lateinit var btnBack: ImageView
    private lateinit var lnTokioMarineI: LinearLayout
    private lateinit var lnCarLifeI: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insurance)

        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        lnTokioMarineI = findViewById(R.id.lnTokioI)
        lnCarLifeI = findViewById(R.id.lnCarLifeI)

        btnBack.setOnClickListener {
            onBackPressed()
        }

        lnTokioMarineI.setOnClickListener {
            val intent = Intent(this, TokioMarineActivity::class.java)
            startActivity(intent)
        }

        lnCarLifeI.setOnClickListener {
            val intent = Intent(this, CarLifeActivity::class.java)
            startActivity(intent)
        }
    }
}