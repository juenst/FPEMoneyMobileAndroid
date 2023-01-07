package lib.finpay.sdk.uikit.view.ppob.alfamart

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.helper.FinpayTheme

class AlfamartActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    lateinit var btnQrCode: TextView

    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}
//    lateinit var btnPembayaranTagihan: LinearLayout
//    lateinit var btnAutoDebit: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alfamart)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)

        btnBack = findViewById(R.id.btnBack)
        btnQrCode = findViewById(R.id.btn_qr_code_switch)
//        btnPembayaranTagihan = findViewById(R.id.btn_pembayaran_tagihan)
//        btnAutoDebit = findViewById(R.id.btn_pendaftaran_pembayaran_autodebit)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnQrCode.setBackgroundColor(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)
        btnQrCode.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarTextColor()!!)


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