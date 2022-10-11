package lib.finpay.sdk

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
//import com.finpay.wallet.view.wallet.WalletActivity
import lib.finpay.sdk.model.*
import lib.finpay.sdk.repository.*


public class FinPaySDK {
//    fun buildSDK(
//        context: Context,
//        merchantUsername: String,
//        merchantPassword: String,
//        merchantSecretKey: String
//    ) {
//        val prefHelper = PrefHelper()
//        prefHelper.setStringToShared(SharedPrefKeys.MERCHANT_USERNAME, merchantUsername)
//        prefHelper.setStringToShared(SharedPrefKeys.MERCHANT_PASSWORD, merchantPassword)
//        prefHelper.setStringToShared(SharedPrefKeys.MERCHANT_SECRET_KEY, merchantSecretKey)
//    }

    fun init(context: Context, credential: Credential): FinPaySDK {
        if(credential.getUsername() == null || credential.getPassword() == null || credential.getSecretKey() == null) {
            println("Client key, username and password cannot be null or empty. Please set the client key, username and password")
            Toast.makeText(context, "Client key, username and password cannot be null or empty. Please set the client key, username and password", Toast.LENGTH_LONG)
        }
        return this
    }

//    fun setContext(var1: Context): FinPaySDK {
//        this.context = var1
//        return this
//    }

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
        context: Context,
        phoneNumber: String,
        onResult: (UserBallanceModel) -> Unit,
        onFailed: (String) -> Unit
    ) : FinPaySDK{
        getToken({
            if(it.getTokenID() != null) {
                UserBallanceRepository.getUserBallance(
                    phoneNumber, it.getTokenID().toString(), {
                        onResult(it)
                    }, {
                        onFailed(it)
                    }
                )
            }
        })
        return this
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
            if(it.getTokenID() != null) {
                UpgradeAccountRepository.upgradeAccount(
                    phoneNumber,
                    it.getTokenID().toString(),
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
        })
    }

//    fun openWallet(context: Context) {
//        val intent = Intent (context, WalletActivity::class.java)
//        context.startActivity(intent)
//    }
}