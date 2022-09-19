package lib.finpay.sdk.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import lib.finpay.sdk.di.module.*
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    ApiModule::class,
    AndroidInjectionModule::class,
    ActivityModule::class,
    ViewModelModule::class,
    DatabaseModule::class,
    FragmentModule::class,
    ServiceModule::class
])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
    fun inject(application: MainApplication)
}