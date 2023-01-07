package lib.finpay.sdk.uikit.view.ppob.pegadaian

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.view.ppob.alfamart.AlfamartActivity

class PegadaianActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pegadaian)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        val backButton = findViewById<ImageView>(R.id.btnBack)
        val btnUlangGadai = findViewById<LinearLayout>(R.id.btnUlangGadai)
        val btnCicilGadai = findViewById<LinearLayout>(R.id.btnCicilGadai)
        val btnGadaiTebus = findViewById<LinearLayout>(R.id.btnGadaiTebus)
        val btnAngsuranMikro = findViewById<LinearLayout>(R.id.btnAngsuranMikro)
        val btnTabunganEmas = findViewById<LinearLayout>(R.id.btnTabunganEmas)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        backButton.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)

        backButton.setOnClickListener{
            onBackPressed()
        }

        btnUlangGadai.setOnClickListener {
            val intent = Intent(this, UlangGadaiActivity::class.java)
            this.startActivity(intent)
        }

        btnCicilGadai.setOnClickListener {
            val intent = Intent(this, CicilGadaiActivity::class.java)
            this.startActivity(intent)
        }

        btnGadaiTebus.setOnClickListener {
            val intent = Intent(this, GadaiTebusActivity::class.java)
            this.startActivity(intent)
        }

        btnAngsuranMikro.setOnClickListener {
            val intent = Intent(this, AngsuranMikroActivity::class.java)
            this.startActivity(intent)
        }

        btnTabunganEmas.setOnClickListener {
            val intent = Intent(this, TabunganEmasActivity::class.java)
            this.startActivity(intent)
        }
    }
}