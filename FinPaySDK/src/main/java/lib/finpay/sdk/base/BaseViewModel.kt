package lib.finpay.sdk.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class BaseViewModel : ViewModel(), HasAndroidInjector{

//    @Inject
//    lateinit var api: Api
    private val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    fun setLoading(state: Boolean){
        loadingLiveData.postValue(state)
    }

    fun isLoading(): LiveData<Boolean> = loadingLiveData

    @Inject
    lateinit var dispactchingActivityInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> = dispactchingActivityInjector

}