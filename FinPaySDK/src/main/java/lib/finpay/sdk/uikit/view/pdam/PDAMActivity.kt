package lib.finpay.sdk.uikit.view.pdam

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import android.content.Intent
import android.widget.EditText

class PDAMActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    private lateinit var searchRegion: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_pdam)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        searchRegion = findViewById(R.id.txt_region)

        btnBack.setOnClickListener {
            finish()
        }

        searchRegion.setOnClickListener {
            val intent = Intent(this, PDAMSearchRegionActivity::class.java)
            this.startActivity(intent)
        }
    }
}