package com.finpay.wallet.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

//class LoginViewModel @Inject constructor(
//    private val loginRepository: LoginRepository,
//    private val checkBorrowerRepository: CheckBorrowerRepository,
//    val dummyEntityDao: DummyEntityDao
//) : BaseViewModel() {
//
//    val loginResult = MutableLiveData<ApiResult<Login>>()
//    val checkBorrowerResult = MutableLiveData<ApiResult<CheckBorrower>>()
//
//    fun testDb() {
//        viewModelScope.launch {
//            dummyEntityDao.insert(DummyEntity(1, "test"))
//        }
//    }
//
//    fun login(phoneNumber: String, password: String, fcmToken: String) {
//        loginRepository.login(phoneNumber, password, fcmToken).observeForever {
//            loginResult.postValue(it)
//        }
//    }
//
//    fun checkBorrower(mobile: String) {
//        checkBorrowerRepository.checkBorrower(mobile).observeForever {
//            checkBorrowerResult.postValue(it)
//        }
//    }
//
//
//}