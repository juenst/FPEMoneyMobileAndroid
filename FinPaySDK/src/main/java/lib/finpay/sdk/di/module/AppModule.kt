package lib.finpay.sdk.di.module

import android.app.Application
import lib.finpay.sdk.data.preference.PrefHelperImpl
import lib.finpay.sdk.di.scope.ApplicationScope
import lib.finpay.sdk.domain.preference.PrefHelper
import lib.finpay.sdk.domain.utils.SchedulerProvider
import lib.finpay.sdk.presentation.AppSchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @ApplicationScope
    fun providePreferencesHelper(app: Application): PrefHelper = PrefHelperImpl(app)

    @Provides
    @ApplicationScope
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}