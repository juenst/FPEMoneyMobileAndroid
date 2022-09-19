package com.finpay.wallet.repository.listpricetbs

//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.paging.DataSource
//import com.finpay.wallet.view.pricetbs.PriceTbs
//
//class PriceDataSourceFactory (
//) : DataSource.Factory<Int, PriceTbs>() {
//    val sourceLiveData = MutableLiveData<PriceDataSource>()
//    override fun create(): DataSource<Int, PriceTbs> {
//        val itemDataSource =
//            PriceDataSource()
//        sourceLiveData.postValue(itemDataSource)
//        return itemDataSource
//    }
//}