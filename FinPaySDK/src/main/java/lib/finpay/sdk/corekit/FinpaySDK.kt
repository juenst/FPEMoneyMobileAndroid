package lib.finpay.sdk.corekit

import android.content.Context
import lib.finpay.sdk.corekit.helper.Signature
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.model.*
import lib.finpay.sdk.corekit.repository.*
import lib.finpay.sdk.uikit.FinpaySDKUI
import lib.finpay.sdk.uikit.utilities.PrefHelper
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys


public class FinpaySDK {
    companion object {
        lateinit var prefHelper: PrefHelper
        lateinit var signature: Signature

        fun init(context: Context){
            prefHelper = PrefHelper()
            PrefHelper.setSharedPreferences(context, Constant.sharedPreferencesName, Context.MODE_PRIVATE)
        }

        fun getToken(transNumber: String, context: Context, onSuccess: (Token) -> Unit, onFailed: (String) -> Unit) {
            init(context)
            if(prefHelper.getStringFromShared(SharedPrefKeys.TOKEN_ID) == null || prefHelper.getStringFromShared(SharedPrefKeys.TOKEN_ID) == "") {
                onFailed("You must connect account first")
            } else {
                TokenRepository.getToken( transNumber, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                })
            }
        }

        fun reqActivation(context:Context, phoneNumber: String, transNumber: String, onSuccess: (Customer) -> Unit, onFailed: (String) -> Unit) {
            init(context)
            CustomerRepository.reqActivation(
                phoneNumber, transNumber, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun reqConfirmation(context: Context, phoneNumber: String, transNumber: String, custName: String, otp: String, custStatusCode: String, onSuccess: (Customer) -> Unit, onFailed: (String) -> Unit) {
            init(context)
            CustomerRepository.reqConfirmation(
                phoneNumber, transNumber, custName, otp, custStatusCode, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                })
        }

        fun getHistoryTransaction(transNumber: String, context: Context, startDate: String, endDate: String, onSuccess: (HistoryTransaction) -> Unit, onFailed: (String) -> Unit) {
            init(context)
            HistoryTransactionRepository.getHistoryTransaction (transNumber, startDate, endDate, {
                onSuccess(it)
            },{
                onFailed(it)
            })
        }

        fun getHistoryMasterTransaction(transNumber: String, context: Context, transType: String, startDate: String, endDate: String, onSuccess: (HistoryTransaction) -> Unit, onFailed: (String) -> Unit) {
            init(context)
            HistoryTransactionRepository.getHistoryMasterTransaction (transNumber, transType, startDate, endDate, {
                onSuccess(it)
            },{
                onFailed(it)
            })
        }


        fun getUserBallance(transNumber: String, context: Context, onResult: (UserBalance) -> Unit, onFailed: (String) -> Unit) {
            init(context)
            UserBallanceRepository.getUserBallance(transNumber, { onResult(it) }, { onFailed(it) })
        }

        fun transaction(transNumber: String, context: Context, transAmount: String, transType: String, transDesc: String, dataBagi: String, onSuccess: (Transaction) -> Unit, onFailed: (String) -> Unit) {
            init(context)
            TransactionRepository.transaction(
                transNumber, transAmount, transType, transDesc, dataBagi, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun upgradeAccount(transNumber: String, context: Context, imageIdentity: String, imageSelfie: String, motherName: String, noKK: String, nationality: String, email: String, onSuccess: (UpgradeAccount) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            UpgradeAccountRepository.upgradeAccount(
                transNumber,
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

        fun qrisInquiry(transNumber: String, context: Context, stringQris: String, onSuccess: (QrisInquiry) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            QrisPayRepository.inquiry(
                transNumber,
                stringQris, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun qrisPayment(transNumber: String, context: Context, sof: String, amount: String, amountTips: String, reffFlag: String, pinToken: String, onSuccess: (QrisPayment) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            QrisPayRepository.payment(
                transNumber, sof, amount, amountTips, reffFlag, pinToken, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun ppobInquiry(transNumber: String, context: Context, billingId: String, productCode: String, billingAmount: String, onSuccess: (PpobInquiry) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            PpobRepository.inquiry(
                transNumber, billingId, productCode, billingAmount, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun ppobPayment(transNumber: String, context: Context, phoneNumber: String, sof: String, payType: String, denom: String, amount: String, billingId: String, productCode: String, reffFlag: String, activationDate: String, pinToken: String, onSuccess: (PpobPayment) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            PpobRepository.payment(
                transNumber, phoneNumber, sof, payType, denom, amount, billingId, productCode, reffFlag,activationDate, pinToken, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun getListProduct(transNumber: String, context: Context, onSuccess: (Product) -> Unit, onFailed: (String) -> Unit) {
            init(context)
            ProductRepository.getListProduct(transNumber, {
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun getListSubProduct(transNumber: String, context: Context, phoneNumber: String, listOpr: ArrayList<String>, onSuccess: (SubProduct) -> Unit, onFailed: (String) -> Unit){
            init(context)
            ProductRepository.getListSubProduct(
                transNumber, phoneNumber,
                listOpr, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                })
        }

        fun getListOprProduct(transNumber: String, context: Context, onSuccess: (OprProduct) -> Unit, onFailed: (String) -> Unit){
            init(context)
            ProductRepository.getListOprProduct(transNumber, {
                onSuccess(it)
            },{
                onFailed(it)
            })
        }

        fun getFeePbob(transNumber: String, context: Context, phoneNumber: String, payType: String, billingId: String, productCode: String, denom: String, onSuccess: (GetFee) -> Unit, onFailed: (String) -> Unit){
            init(context)
            PpobRepository.getFeePpob(transNumber, phoneNumber, payType, billingId, productCode, denom,
            {
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun authPin(transNumber: String, context: Context, amount: String, productCode: String, onSuccess: (PinAuth) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            PinRepository.authPin(
                transNumber, amount, productCode, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun resetPin(transNumber: String, context: Context, deviceId: String, onSuccess: (PinReset) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            PinRepository.resetPin(
                transNumber, deviceId, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun changePin(transNumber: String, context: Context, onSuccess: (PinChange) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            PinRepository.changePin(
                transNumber, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun transferToOtherInquiry(transNumber: String, context: Context, phoneNumberDest: String, onSuccess: (TransferOtherInquiry) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            TransferRepository.inquiryOthers(
                transNumber, phoneNumberDest, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun transferToOtherPayment(transNumber: String, context: Context, phoneNumberDest: String, amount: String, desc: String, pinToken: String, onSuccess: (TransferOtherPayment) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            TransferRepository.paymentOthers(
                transNumber, phoneNumberDest, amount, desc, pinToken ,{
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun getListBank(transNumber: String, context: Context, onSuccess: (Bank) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            TransferRepository.getListBank(transNumber, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun transferToBankInquiry(transNumber: String, context: Context, bankCode: String, bankNo: String, amount: String, onSuccess: (TransferBankInquiry) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            TransferRepository.inquiryBank(
                transNumber, bankCode, bankNo, amount, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun transferToBankPayment(transNumber: String, context: Context, phoneNumberDest: String, reffFlag: String, reffTrx: String, category: String, pinToken: String, desc: String, onSuccess: (TransferBankPayment) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            TransferRepository.paymentBank(
                transNumber, phoneNumberDest, reffFlag, reffTrx, category, pinToken, desc, {
                    onSuccess(it)
                }, {
                    onFailed(it)
                }
            )
        }

        fun getMutasiBallance(transNumber: String, context: Context, pin: String, custName: String, otp: String, custStatusCode: String, transAmount: String, transType: String, transDesc: String, startDate: String, endDate: String, onSuccess: (MutasiBallance) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            MutasiBallanceRepository.mutasiBallance(transNumber, pin, custName, otp, custStatusCode, transAmount, transType, transDesc, startDate, endDate, {
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun regisAccMerchant(transNumber: String, context: Context, phoneNumber: String, custName: String, transType: String, onSuccess: (RegisAccMerchant) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            RegisAccMerchantRepository.regisAccMerchant(transNumber, phoneNumber, custName, transType, {
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun unpair(transNumber: String, context: Context, transType: String, onSuccess: (Unpair) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            UnpairRepository.unpair(transNumber, transType, {
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun checkProfile(transNumber: String, context: Context, onSuccess: (Profile) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            CustomerRepository.checkProfile(transNumber, {
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun widgetProfile(transNumber: String, context: Context, onSuccess: (WidgetProfile) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            WidgetProfileRepository.widgetProfile(transNumber, {
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun widgetTopup(transNumber: String, context: Context, onSuccess: (WidgetTopUp) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            WidgetTopupRepository.widgetTopUp(transNumber,{
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }

        fun topup(transNumber: String, context: Context, amount: String, sof: String, onSuccess: (TopupInquiry) -> Unit, onFailed: (String) -> Unit)  {
            init(context)
            TopupRepository.topup(transNumber, amount, sof, {
                onSuccess(it)
            }, {
                onFailed(it)
            })
        }
    }

}