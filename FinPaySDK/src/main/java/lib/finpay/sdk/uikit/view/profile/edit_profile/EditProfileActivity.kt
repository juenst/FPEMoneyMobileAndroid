package lib.finpay.sdk.uikit.view.profile.edit_profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import lib.finpay.sdk.R

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

//        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val backButton = findViewById(R.id.backButton) as ImageView
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}