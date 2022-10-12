package lib.finpay.sdk.uikit.view.ppob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import lib.finpay.sdk.R

class CarLifeInsuranceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_life_insurance)

        val backButton = findViewById<ImageView>(R.id.btnBack)

        backButton.setOnClickListener{
            onBackPressed()
        }
    }
}