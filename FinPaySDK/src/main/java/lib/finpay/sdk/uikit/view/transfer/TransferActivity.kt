package lib.finpay.sdk.uikit.view.transfer

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R

class TransferActivity : AppCompatActivity()  {
    lateinit var btnBack: ImageView
    lateinit var btnToOther: LinearLayout
    lateinit var btnToBank: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)
        supportActionBar!!.hide()

        btnToOther = findViewById(R.id.btnToOther)
        btnToBank = findViewById(R.id.btnToBank)
        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener{
            onBackPressed()
        }
    }
}