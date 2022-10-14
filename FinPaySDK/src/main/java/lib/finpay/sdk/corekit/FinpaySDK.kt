package lib.finpay.sdk.corekit

import android.content.Context
import com.example.testing.Signature
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.model.*
import lib.finpay.sdk.corekit.repository.*
import lib.finpay.sdk.uikit.FinpaySDKUI
import lib.finpay.sdk.uikit.utilities.PrefHelper


public class FinpaySDK {
    companion object {
        lateinit var prefHelper: PrefHelper
        lateinit var signature: Signature

        fun init(context: Context){
            prefHelper = PrefHelper()
            PrefHelper.setSharedPreferences(context, Constant.sharedPreferencesName, Context.MODE_PRIVATE)
        }

        fun getToken(/*context: Context, */onSuccess: (Token) -> Unit, onFailed: (String) -> Unit) {
            //init(context)
            TokenRepository.getToken( {
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun reqActivation(
            context:Context,
            phoneNumber: String,
            onSuccess: (Customer) -> Unit,
            onFailed: (String) -> Unit
        ) {
            init(context)
            CustomerRepository.reqActivation(
                phoneNumber, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun reqConfirmation(context: Context, phoneNumber: String, custName: String, otp: String, custStatusCode: String, onSuccess: (Customer) -> Unit, onFailed: (String) -> Unit) {
            init(context)
            CustomerRepository.reqConfirmation(
                phoneNumber, custName, otp, custStatusCode, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                })
        }

        fun getHistoryTransaction(context: Context, startDate: String, endDate: String, onSuccess: (HistoryTransaction) -> Unit, onFailed: (String) -> Unit) {
            init(context)
            HistoryTransactionRepository.getHistoryTransaction (startDate, endDate, {
                onSuccess(it)
            },{
                onFailed(it)
            })
        }


        fun getUserBallance(context: Context, onResult: (UserBalance) -> Unit, onFailed: (String) -> Unit) {
            init(context)
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

        fun upgradeAccount(imageIdentity: String, imageSelfie: String, motherName: String, noKK: String, nationality: String, email: String, onSuccess: (UpgradeAccount) -> Unit, onFailed: (String) -> Unit)  {
            UpgradeAccountRepository.upgradeAccount(
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

        fun qrisInquiry(context: Context, stringQris: String, onSuccess: (QrisInquiry) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            QrisPayRepository.inquiry(
                stringQris, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun qrisPayment(context: Context, sof: String, amount: String, amountTips: String, reffFlag: String, pinToken: String, onSuccess: (QrisPayment) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            QrisPayRepository.payment(
                sof, amount, amountTips, reffFlag, pinToken, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun ppobInquiry(context: Context, billingId: String, productCode: String, billingAmount: String, onSuccess: (PpobInquiry) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            PpobRepository.inquiry(
                billingId, productCode, billingAmount, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun ppobPayment(context: Context, sof: String, payType: String, denom: String, amount: String, billingId: String, productCode: String, reffFlag: String, activationDate: String, pinToken: String, onSuccess: (PpobPayment) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            PpobRepository.payment(
                sof, payType, denom, amount, billingId, productCode, reffFlag,activationDate, pinToken, {
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

        fun getListSubProduct(
            listOpr: ArrayList<String>,
            onResult: (SubProduct) -> Unit
        ){
            //Example
            listOpr.add("Telkomsel")
            getToken( { token ->
                if (token.tokenID != null) {
                    ProductRepository.getListSubProduct(
                        listOpr
                    ) { value->
                        println("Jalan loh " + value.statusCode)
                        if (value.statusCode == "000") {
                            onResult(value)
                        }
                    }
                }
            },{})
        }

        fun getListOprProduct(
            productCode: String,
            onResult: (OprProduct) -> Unit
        ){
            getToken( { token ->
                if (token.tokenID != null) {
                    ProductRepository.getListOprProduct(
                        productCode
                    ) { value->
                        println("Jalan loh " + value.statusCode)
                        if (value.statusCode == "000") {
                            onResult(value)
                        }
                    }
                }
            },{})
        }

        fun authPin(context: Context, amount: String, productCode: String, onSuccess: (AuthPin) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            PinRepository.authPin(
                amount, productCode, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }
    }
}