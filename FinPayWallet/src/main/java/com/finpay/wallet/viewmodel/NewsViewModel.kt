//package com.finpay.wallet.viewmodel
//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Transformations
//import com.finpay.wallet.base.BaseViewModel
//import com.finpay.wallet.repository.news.NewsRepository
//import javax.inject.Inject
//
//class NewsViewModel @Inject constructor(private val repository: NewsRepository) : BaseViewModel() {
//    var totalScrollYRecyclerView = 0
//
//    val pageInit = MutableLiveData<Int>()
//
//    private val repoResult = Transformations.map(pageInit) {
//        repository.getNews()
//    }
//
//    val pos = Transformations.switchMap(repoResult) {
//        it.pagedList
//    }
//
//    val networkState = Transformations.switchMap(repoResult) {
//        it.networkState
//    }
//
//    fun retry() {
//        val listing = repoResult.value
//        listing?.retry?.invoke()
//    }
//
//}