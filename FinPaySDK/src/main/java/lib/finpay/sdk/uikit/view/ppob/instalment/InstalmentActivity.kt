package lib.finpay.sdk.uikit.view.ppob.instalment

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R

class InstalmentActivity : AppCompatActivity() {
    private lateinit var btnBack: ImageView
    private lateinit var lnBussanAF: LinearLayout
    private lateinit var lnSmartF: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instalment)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        lnBussanAF = findViewById(R.id.lnBussanAF)
        lnSmartF = findViewById(R.id.lnSmartF)

        btnBack.setOnClickListener {
            onBackPressed()
        }

        lnBussanAF.setOnClickListener {
            val intent = Intent(this, BussanAutoFinanceActivity::class.java)
            startActivity(intent)
        }

        lnSmartF.setOnClickListener {
            val intent = Intent(this, SmartFinanceActivity::class.java)
            startActivity(intent)
        }
    }
}