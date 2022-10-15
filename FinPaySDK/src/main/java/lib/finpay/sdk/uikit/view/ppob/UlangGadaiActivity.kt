package lib.finpay.sdk.uikit.view.ppob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import lib.finpay.sdk.R

class UlangGadaiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ulang_gadai)

        val backButton = findViewById<ImageView>(R.id.btnBack)

        backButton.setOnClickListener{
            onBackPressed()
        }
    }
}