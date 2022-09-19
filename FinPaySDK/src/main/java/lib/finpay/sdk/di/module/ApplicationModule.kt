package lib.finpay.sdk.di.module

import android.app.Application
import android.content.Context
//import com.finpay.wallet.MainApplication
//import com.finpay.wallet.utilities.ConstValue
//import com.finpay.wallet.utilities.PrefHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
@Singleton
@Provides
fun provideContext(application: Application): Context = application

//    @Singleton
//    @Provides
//    fun provideMainApplication(application: Application): MainApplication = application as MainApplication
//
//    @Provides
//    fun prefHelper(context: Context) : PrefHelper {
//        PrefHelper.setSharedPreferences(context, ConstValue.sharedPreferencesName, Context.MODE_PRIVATE)
//        return PrefHelper()
//    }

}