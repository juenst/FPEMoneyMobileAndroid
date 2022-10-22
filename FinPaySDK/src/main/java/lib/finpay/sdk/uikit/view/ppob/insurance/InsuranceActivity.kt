package lib.finpay.sdk.uikit.view.ppob.insurance

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.view.ppob.instalment.BussanAutoFinanceActivity
import lib.finpay.sdk.uikit.view.ppob.instalment.SmartFinanceActivity

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
//        lnSmartF = findViewById(R.id.lnSmartF)

        btnBack.setOnClickListener {
            onBackPressed()
        }

        lnTokioMarineI.setOnClickListener {
            val intent = Intent(this, TokioMarineActivity::class.java)
            startActivity(intent)
        }

//        lnSmartF.setOnClickListener {
//            val intent = Intent(this, SmartFinanceActivity::class.java)
//            startActivity(intent)
//        }
    }
}