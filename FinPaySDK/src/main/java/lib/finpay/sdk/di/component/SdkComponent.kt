package lib.finpay.sdk.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import lib.finpay.sdk.FinPaySDK
import javax.inject.Singleton

@Singleton
@Component(
    modules = []
)

interface SdkComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): SdkComponent
    }
    fun inject(SdkComponent: FinPaySDK)
}