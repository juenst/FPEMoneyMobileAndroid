//package lib.finpay.sdk.ui.onboard
//
//import android.content.Intent
//import android.os.Bundle
//import android.os.Handler
//import lib.finpay.sdk.R
//import lib.finpay.sdk.databinding.ActivitySplashBinding
//import lib.finpay.sdk.presentation.base.BaseActivity
//import lib.finpay.sdk.ui.MainActivity
//import lib.finpay.sdk.utils.DisplayUtils
//
//class SplashActivity : BaseActivity<ActivitySplashBinding>() {
//
//    private val mHandler = Handler()
//
//    override fun getLayoutId(): Int = R.layout.activity_splash
//
//    override fun onViewReady(savedInstance: Bundle?) {
//        DisplayUtils.setTransparentStatusBar(window)
//        mHandler.postDelayed(runnable,3000)
//    }
//
//    private val runnable = Runnable {
//        startActivity(Intent(this, MainActivity::class.java))
//        finish()
//    }
//
//    override fun onBackPressed() {
//        // prevent back press
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mHandler.removeCallbacksAndMessages(runnable)
//    }
//}
