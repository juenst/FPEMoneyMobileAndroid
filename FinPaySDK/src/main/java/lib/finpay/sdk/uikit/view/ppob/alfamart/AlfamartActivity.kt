package lib.finpay.sdk.uikit.view.ppob.alfamart

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R

class AlfamartActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
//    lateinit var btnPembayaranTagihan: LinearLayout
//    lateinit var btnAutoDebit: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alfamart)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
//        btnPembayaranTagihan = findViewById(R.id.btn_pembayaran_tagihan)
//        btnAutoDebit = findViewById(R.id.btn_pendaftaran_pembayaran_autodebit)

        btnBack.setOnClickListener {
            finish()
        }

//        btnPembayaranTagihan.setOnClickListener {
//            val intent = Intent(this, BPJSDetailActivity::class.java)
//            this.startActivity(intent)
//        }
//
//        btnAutoDebit.setOnClickListener {
//            val intent = Intent(this, InternetTvCableDetailActivity::class.java)
//            this.startActivity(intent)
//        }
    }
}