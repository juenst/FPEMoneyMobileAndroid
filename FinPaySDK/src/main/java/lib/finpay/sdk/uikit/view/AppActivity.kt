package lib.finpay.sdk.uikit.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import lib.finpay.sdk.R
import lib.finpay.sdk.databinding.ActivityAppBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import lib.finpay.sdk.uikit.FinpaySDKUI
import lib.finpay.sdk.uikit.utilities.Credential
import lib.finpay.sdk.uikit.view.qris.QRISActivity

class AppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()

        binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_app)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_qris, R.id.navigation_notifications)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //Add custom tab menu
        val bottomMenuView = navView.getChildAt(0) as BottomNavigationMenuView
        val view = bottomMenuView.getChildAt(1)
        val itemView = view as BottomNavigationItemView

        val viewCustom = LayoutInflater.from(this).inflate(R.layout.button_custom, bottomMenuView, false)
        itemView.addView(viewCustom)

        val btnQris = findViewById<ImageButton>(R.id.btnQris)

        btnQris.setOnClickListener {
            FinpaySDKUI.qrisUIBuilder("", this@AppActivity, Credential.credential())
        }
    }
}