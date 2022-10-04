package lib.finpay.sdk

import android.content.Context
import android.content.SharedPreferences
import com.example.testing.Signature
import lib.finpay.sdk.helper.PrefHelper
import lib.finpay.sdk.helper.SharedPrefKeys
import lib.finpay.sdk.model.*
import lib.finpay.sdk.repository.*


class FinPaySDK{

    fun init(
        merchantUsername: String,
        merchantPassword: String,
        merchantSecretKey: String,
        transNumber: String,
    ) {
//        val prefHelper = PrefHelper()
//        prefHelper.setStringToShared(SharedPrefKeys.MERCHANT_USERNAME, merchantUsername)
//        prefHelper.setStringToShared(SharedPrefKeys.MERCHANT_PASSWORD, merchantPassword)
//        prefHelper.setStringToShared(SharedPrefKeys.MERCHANT_SECRET_KEY, merchantSecretKey)
//        prefHelper.setStringToShared(SharedPrefKeys.TRANSACTION_NUMBER, transNumber)
    }

    fun getToken(
        onResult: (TokenModel) -> Unit
    ) {
        TokenRepository.getToken() {
            if (it.getTokenID() != null) {
                onResult(it)
            }
        }
    }

    fun reqActivation(
        phoneNumber: String,
        onResult: (ReqActivationModel) -> Unit
    ) {
        getToken({
            if(it.getTokenID() != null) {
                ReqActivationRepository.reqActivation(
                    phoneNumber, it.getTokenID().toString(), {
                    if (it.getCustStatusCode() != null) {
                        if(it.getCustStatusCode() == "003") {
                            onResult(it)
                        } else {
                            //reqConfirmation()
                        }
                    }
                })
            }
        })
    }

    fun reqConfirmation(
        phoneNumber: String,
        custName: String,
        otp: String,
        custStatusCode: String,
        onResult: (ReqConfirmationModel) -> Unit
    ) {
        getToken({
            if(it.getTokenID() != null) {
                ReqConfirmationRepository.reqConfirmation(
                    phoneNumber,
                    it.getTokenID().toString(),
                    custName,
                    otp,
                    custStatusCode,
                    {
                        if (it.getStatusCode() != "000") {
                            onResult(it)
                        }
                    })
            }
        })
    }

    fun getHistoryTransaction(
        onResult: (HistoryTransactionModel) -> Unit
    ) {
        getToken({
            if(it.getTokenID() != null) {
                HistoryTransactionRepository.getHistoryTransaction (
                    {
                        if (it.getStatusCode() != "000") {
                            onResult(it)
                        }
                    })
            }
        })
    }

    fun getHistoryMasterTransaction(
        onResult: (HistoryTransactionModel) -> Unit
    ) {
        getToken({
            if(it.getTokenID() != null) {
                HistoryTransactionRepository.getHistoryTransaction (
                    {
                        if (it.getStatusCode() != "000") {
                            onResult(it)
                        }
                    })
            }
        })
    }

    fun getUserBallance(
        phoneNumber: String,
        onResult: (UserBallanceModel) -> Unit
    ) {
        getToken({
            if(it.getTokenID() != null) {
                UserBallanceRepository.getUserBallance(
                    phoneNumber, it.getTokenID().toString(), {
                        onResult(it)
                    }
                )
            }
        })
    }

    fun transaction(
        phoneNumber: String,
        transAmount: String,
        transType: String,
        transDesc: String,
        dataBagi: String,
        onResult: (TransactionModel) -> Unit
    ) {
        getToken({
            if(it.getTokenID() != null) {
                TransactionRepository.transaction(
                    phoneNumber, it.getTokenID().toString(), transAmount, transType, transDesc, dataBagi, {
                        onResult(it)
                    }
                )
            }
        })
    }
}