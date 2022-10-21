package lib.finpay.sdk.corekit

import android.content.Context
import lib.finpay.sdk.corekit.helper.Signature
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.model.*
import lib.finpay.sdk.corekit.repository.*
import lib.finpay.sdk.uikit.utilities.PrefHelper


public class FinpaySDK {
    companion object {
        lateinit var prefHelper: PrefHelper
        lateinit var signature: Signature

        fun init(context: Context){
            prefHelper = PrefHelper()
            PrefHelper.setSharedPreferences(context, Constant.sharedPreferencesName, Context.MODE_PRIVATE)
        }

        fun getToken(context: Context, onSuccess: (Token) -> Unit, onFailed: (String) -> Unit) {
            init(context)
            TokenRepository.getToken( {
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun reqActivation(context:Context, phoneNumber: String, onSuccess: (Customer) -> Unit, onFailed: (String) -> Unit) {
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

        fun getHistoryMasterTransaction(context: Context, transType: String, startDate: String, endDate: String, onSuccess: (HistoryTransaction) -> Unit, onFailed: (String) -> Unit) {
            init(context)
            HistoryTransactionRepository.getHistoryMasterTransaction (transType, startDate, endDate, {
                onSuccess(it)
            },{
                onFailed(it)
            })
        }


        fun getUserBallance(context: Context, onResult: (UserBalance) -> Unit, onFailed: (String) -> Unit) {
            init(context)
            UserBallanceRepository.getUserBallance({ onResult(it) }, { onFailed(it) })
        }

        fun transaction(context: Context, transAmount: String, transType: String, transDesc: String, dataBagi: String, onSuccess: (Transaction) -> Unit, onFailed: (String) -> Unit) {
            init(context)
            TransactionRepository.transaction(
                transAmount, transType, transDesc, dataBagi, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun upgradeAccount(context: Context, imageIdentity: String, imageSelfie: String, motherName: String, noKK: String, nationality: String, email: String, onSuccess: (UpgradeAccount) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
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

        fun getListProduct(context: Context, onSuccess: (Product) -> Unit, onFailed: (String) -> Unit) {
            init(context)
            ProductRepository.getListProduct({
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun getListSubProduct(context: Context, phoneNumber: String, listOpr: ArrayList<String>, onSuccess: (SubProduct) -> Unit, onFailed: (String) -> Unit){
            init(context)
            ProductRepository.getListSubProduct(
                phoneNumber,
                listOpr, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                })
        }

        fun getListOprProduct(context: Context, onSuccess: (OprProduct) -> Unit, onFailed: (String) -> Unit){
            init(context)
            ProductRepository.getListOprProduct({
                onSuccess(it)
            },{
                onFailed(it)
            })
        }

        fun getFeePbob(context: Context, phoneNumber: String, payType: String, billingId: String, productCode: String, denom: String, onSuccess: (GetFee) -> Unit, onFailed: (String) -> Unit){
            init(context)
            PpobRepository.getFeePpob(phoneNumber, payType, billingId, productCode, denom,
            {
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun authPin(context: Context, amount: String, productCode: String, onSuccess: (PinAuth) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            PinRepository.authPin(
                amount, productCode, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun resetPin(context: Context, deviceId: String, onSuccess: (PinReset) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            PinRepository.resetPin(
                deviceId, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun changePin(context: Context, onSuccess: (PinChange) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            PinRepository.changePin(
                {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun transferToOtherInquiry(context: Context, phoneNumberDest: String, onSuccess: (TransferOtherInquiry) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            TransferRepository.inquiryOthers(
                phoneNumberDest, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun transferToOtherPayment(context: Context, phoneNumberDest: String, amount: String, desc: String, pinToken: String, onSuccess: (TransferOtherPayment) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            TransferRepository.paymentOthers(
                phoneNumberDest, amount, desc, pinToken ,{
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun getListBank(context: Context, onSuccess: (Bank) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            TransferRepository.getListBank({
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun transferToBankInquiry(context: Context, bankCode: String, bankNo: String, amount: String, onSuccess: (TransferBankInquiry) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            TransferRepository.inquiryBank(
                bankCode, bankNo, amount, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun transferToBankPayment(context: Context, phoneNumberDest: String, reffFlag: String, reffTrx: String, category: String, pinToken: String, desc: String, onSuccess: (TransferBankPayment) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            TransferRepository.paymentBank(
                phoneNumberDest, reffFlag, reffTrx, category, pinToken, desc, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun getMutasiBallance(context: Context, pin: String, custName: String, otp: String, custStatusCode: String, transAmount: String, transType: String, transDesc: String, startDate: String, endDate: String, onSuccess: (MutasiBallance) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            MutasiBallanceRepository.mutasiBallance(pin, custName, otp, custStatusCode, transAmount, transType, transDesc, startDate, endDate, {
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun regisAccMerchant(context: Context, phoneNumber: String, custName: String, transType: String, onSuccess: (RegisAccMerchant) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            RegisAccMerchantRepository.regisAccMerchant(phoneNumber, custName, transType, {
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun unpair(context: Context, transType: String, onSuccess: (Unpair) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            UnpairRepository.unpair(transType, {
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun checkProfile(context: Context, onSuccess: (Profile) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            CustomerRepository.checkProfile({
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun widgetProfile(context: Context, onSuccess: (WidgetProfile) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            WidgetProfileRepository.widgetProfile({
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun widgetTopup(context: Context, onSuccess: (WidgetTopUp) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            WidgetTopupRepository.widgetTopUp({
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun topup(context: Context, amount: String, sof: String, onSuccess: (TopupInquiry) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            TopupRepository.topup(amount, sof, {
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }
    }

}