package lib.finpay.sdk.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lib.finpay.sdk.service.network.ApiService
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val viewModelsMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    @Inject
    lateinit var api: ApiService

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //TODO() Add ViewModel Thats needed
        val creator = viewModelsMap[modelClass] ?: viewModelsMap.asIterable().firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}

