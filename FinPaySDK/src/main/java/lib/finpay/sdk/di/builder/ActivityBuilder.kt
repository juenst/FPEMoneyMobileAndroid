package lib.finpay.sdk.di.builder

import lib.finpay.sdk.ui.MainActivity
import lib.finpay.sdk.ui.onboard.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributesSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

}