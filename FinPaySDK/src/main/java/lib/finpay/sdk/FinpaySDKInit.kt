package lib.finpay.sdk

import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class FinpaySDKInit {
    @Inject
    lateinit var dispactchingActivityInjector: DispatchingAndroidInjector<Any>

    private val localeAppDelegate =
        LocaleHelperApplicationDelegate()

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        appComponent = DaggerAppComponent.factory().create(this)
        appComponent.inject(this)
//        PrefHelper.setSharedPreferences(applicationContext, GlobalVar.sharedPreferencesName,
//            Context.MODE_PRIVATE)

    }

    override fun androidInjector(): AndroidInjector<Any> = dispactchingActivityInjector

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(localeAppDelegate.attachBaseContext(base!!))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeAppDelegate.onConfigurationChanged(this)
    }
}