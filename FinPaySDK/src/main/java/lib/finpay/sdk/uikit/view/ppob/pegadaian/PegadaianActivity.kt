package lib.finpay.sdk.uikit.view.ppob.pegadaian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.view.ppob.alfamart.AlfamartActivity

class PegadaianActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pegadaian)
        supportActionBar!!.hide()

        val backButton = findViewById<ImageView>(R.id.btnBack)
        val btnUlangGadai = findViewById<LinearLayout>(R.id.btnUlangGadai)
        val btnCicilGadai = findViewById<LinearLayout>(R.id.btnCicilGadai)
        val btnGadaiTebus = findViewById<LinearLayout>(R.id.btnGadaiTebus)
        val btnAngsuranMikro = findViewById<LinearLayout>(R.id.btnAngsuranMikro)
        val btnTabunganEmas = findViewById<LinearLayout>(R.id.btnTabunganEmas)

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