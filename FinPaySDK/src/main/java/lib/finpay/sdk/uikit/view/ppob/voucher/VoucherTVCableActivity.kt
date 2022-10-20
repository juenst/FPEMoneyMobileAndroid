package lib.finpay.sdk.uikit.view.ppob.voucher

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.view.AppActivity

class VoucherTVCableActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    lateinit var lnKvision: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet_tv_cable)

        btnBack = findViewById(R.id.btnBack)
        lnKvision = findViewById(R.id.lnKvision)

        btnBack.setOnClickListener {
            onBackPressed()
        }

        lnKvision.setOnClickListener {
            val intent = Intent(this, KvisionActivity::class.java)
            startActivity(intent)
        }
    }
}