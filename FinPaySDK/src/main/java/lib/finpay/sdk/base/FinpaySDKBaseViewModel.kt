package lib.finpay.sdk.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import lib.finpay.sdk.service.network.FinpaySDKDataSource
import kotlin.coroutines.CoroutineContext

open class FinpaySDKBaseViewModel(
    application: Application,
    protected val dataSource: FinpaySDKDataSource
) : AndroidViewModel(application) {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    protected val scope = CoroutineScope(coroutineContext)

    private val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun setLoading(state: Boolean) {
        loadingLiveData.postValue(state)
    }

    fun isLoading(): LiveData<Boolean> = loadingLiveData

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}