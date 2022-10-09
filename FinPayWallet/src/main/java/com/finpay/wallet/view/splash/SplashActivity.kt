package com.finpay.wallet.view.splash

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.multidex.BuildConfig.VERSION_CODE
import com.finpay.wallet.R
import com.finpay.wallet.view.AppActivity
import com.finpay.wallet.view.onboarding.OnboardingActivity

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val appVersion = findViewById<TextView>(R.id.appVersion)
        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        val manager = this.packageManager
        val info = manager.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES)
        val versionCode: Int = info.versionCode

        appVersion.text = versionCode.toString()
        Handler().postDelayed({
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.
    }
}
