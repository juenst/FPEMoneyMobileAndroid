package lib.finpay.sdk.base

import androidx.lifecycle.ViewModel
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector

open class BaseViewModel : ViewModel(), HasAndroidInjector{

    override fun androidInjector(): AndroidInjector<Any> {
        TODO("Not yet implemented")
    }

}