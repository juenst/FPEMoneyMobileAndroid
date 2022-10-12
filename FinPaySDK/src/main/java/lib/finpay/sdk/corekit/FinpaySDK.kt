package lib.finpay.sdk.corekit

import android.content.Context
import com.example.testing.Signature
import lib.finpay.sdk.corekit.model.*
import lib.finpay.sdk.corekit.repository.*
import lib.finpay.sdk.uikit.utilities.PrefHelper


public class FinpaySDK {
    companion object {
        lateinit var prefHelper: PrefHelper
        lateinit var signature: Signature

        fun getToken(onSuccess: (Token) -> Unit, onFailed: (String) -> Unit) {
            prefHelper = PrefHelper()
            TokenRepository.getToken( {
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun reqActivation(
            phoneNumber: String,
            onSuccess: (Customer) -> Unit,
            onFailed: (String) -> Unit
        ) {
            CustomerRepository.reqActivation(
                phoneNumber, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun reqConfirmation(
            phoneNumber: String,
            custName: String,
            otp: String,
            custStatusCode: String,
            onSuccess: (Customer) -> Unit,
            onFailed: (String) -> Unit
        ) {
            CustomerRepository.reqConfirmation(
                phoneNumber, custName, otp, custStatusCode, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                })
        }

        fun getHistoryTransaction(
            onResult: (HistoryTransactionModel) -> Unit
        ) {
            getToken({
                if(it.tokenID != null) {
                    HistoryTransactionRepository.getHistoryTransaction (
                        {
                            if (it.getStatusCode() == "000") {
                                onResult(it)
                            }
                        })
                }
            },{})
        }

        fun getHistoryMasterTransaction(
            onResult: (HistoryTransactionModel) -> Unit
        ) {
            getToken({
                if(it.tokenID != null) {
                    HistoryTransactionRepository.getHistoryTransaction (
                        {
                            if (it.getStatusCode() != "000") {
                                onResult(it)
                            }
                        })
                }
            },{})
        }

        fun getUserBallance(
            onResult: (UserBalance) -> Unit,
            onFailed: (String) -> Unit
        ) {
            prefHelper = PrefHelper()
            UserBallanceRepository.getUserBallance({ onResult(it) }, { onFailed(it) })
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
                if(it.tokenID != null) {
                    TransactionRepository.transaction(
                        phoneNumber, it.tokenID.toString(), transAmount, transType, transDesc, dataBagi, {
                            onResult(it)
                        }
                    )
                }
            },{})
        }

        fun upgradeAccount(
            phoneNumber: String,
            imageIdentity: String,
            imageSelfie: String,
            motherName: String,
            noKK: String,
            nationality: String,
            email: String,
            onSuccess: (UpgradeAccountModel) -> Unit,
            onFailed: (String) -> Unit
        )  {
            getToken({
                if(it.tokenID != null) {
                    UpgradeAccountRepository.upgradeAccount(
                        phoneNumber,
                        it.tokenID.toString(),
                        imageIdentity,
                        imageSelfie,
                        motherName,
                        noKK,
                        nationality,
                        email, {
                            onSuccess(it)
                        }, {
                            onFailed(it)
                        }
                    )
                }
            },{})
        }

        fun qrisInquiry(stringQris: String, onSuccess: (QrisInquiry) -> Unit, onFailed: (String) -> Unit)  {
            prefHelper = PrefHelper()
            QrisPayRepository.inquiry(
                stringQris, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun qrisPayment(sof: String, amount: String, amountTips: String, reffFlag: String, pinToken: String, onSuccess: (QrisPayment) -> Unit, onFailed: (String) -> Unit)  {
            QrisPayRepository.payment(
                sof, amount, amountTips, reffFlag, pinToken, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun getListProduct(
            onResult: (ProductModel) -> Unit
        ) {
            getToken( { token ->
                if (token.tokenID != null) {
                    ProductRepository.getListProduct { value->
                        println("Jalan loh " + value.getStatusCode()!!)
                        if (value.getStatusCode() == "000") {
                            onResult(value)
                        }
                    }
                }
            },{})
        }
    }
}