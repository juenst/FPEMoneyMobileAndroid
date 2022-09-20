package lib.finpay.sdk.viewmodel

import androidx.lifecycle.MutableLiveData
import lib.finpay.sdk.model.TokenModel
import lib.finpay.sdk.repository.TokenRepository
import lib.finpay.sdk.service.ApiResult

class TokenViewModel(
    private val tokenRepository: TokenRepository
) {
    val tokenResult = MutableLiveData<ApiResult<TokenModel>>()

    fun getToken(merchantUsername: String, merchantPassword: String, merchantSecretKey: String, transNumber: String) {
        tokenRepository.getToken(merchantUsername, merchantPassword, merchantSecretKey, transNumber).observeForever {
            tokenResult.postValue(it)
        }
    }
}