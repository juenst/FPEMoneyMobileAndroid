//package lib.finpay.sdk
//
//import android.content.Context
//import androidx.multidex.MultiDex
//import lib.finpay.sdk.di.component.DaggerAppComponent
//import lib.finpay.sdk.utils.AppLogger
//import dagger.android.support.DaggerApplication
//
//
//class App : DaggerApplication() {
//
//    private val applicationInjector = DaggerAppComponent.builder().application(this).build()
//
//    override fun attachBaseContext(base: Context?) {
//        super.attachBaseContext(base)
//        MultiDex.install(this)
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        AppLogger.init()
//    }
//
//    override fun applicationInjector() = applicationInjector
//}