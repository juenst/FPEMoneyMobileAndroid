//package lib.finpay.sdk.di.component
//
//import android.app.Application
//import dagger.BindsInstance
//import dagger.Component
//import dagger.android.AndroidInjector
//import dagger.android.support.AndroidSupportInjectionModule
//import dagger.android.support.DaggerApplication
//import lib.finpay.sdk.App
//import lib.finpay.sdk.di.builder.ActivityBuilder
//import lib.finpay.sdk.di.builder.FragmentBuilder
//import lib.finpay.sdk.di.module.*
//import lib.finpay.sdk.di.scope.ApplicationScope
//
//@ApplicationScope
//@Component(
//    modules = [
//        AppModule::class,
//        DatabaseModule::class,
//        NetworkModule::class,
//        RepositoryModule::class,
//        ActivityBuilder::class,
//        FragmentBuilder::class,
//        ViewModelModule::class,
//        AndroidSupportInjectionModule::class
//    ]
//)
//interface DaggerAppComponent : AndroidInjector<DaggerApplication?> {
//    fun inject(application: App)
//
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun application(application: Application): Builder
//
//        fun build(): AppComponent
//    }
//}