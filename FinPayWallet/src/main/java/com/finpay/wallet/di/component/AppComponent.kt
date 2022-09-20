//package com.finpay.wallet.di.component
//
//import android.app.Application
//import com.finpay.wallet.MainApplication
//import com.finpay.wallet.base.`interface`.ToolbarInterface
//import com.finpay.wallet.di.module.*
//import dagger.BindsInstance
//import dagger.Component
//import dagger.android.AndroidInjectionModule
//import javax.inject.Singleton
//
//@Singleton
//@Component(modules = [
//    ApplicationModule::class,
//    ApiModule::class,
//    AndroidInjectionModule::class,
//    ActivityModule::class,
//    ViewModelModule::class,
//    DatabaseModule::class,
//    FragmentModule::class,
//    ServiceModule::class
//])
//interface AppComponent {
//    @Component.Factory
//    interface Factory {
//        fun create(@BindsInstance application: Application): AppComponent
//    }
//    fun inject(application: MainApplication)
//}