package lib.finpay.sdk.uikit

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.widget.Toast
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.model.Credential
import lib.finpay.sdk.corekit.model.HistoryTransaction
import lib.finpay.sdk.corekit.model.UpgradeAccount
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.PinTypeKeys
import lib.finpay.sdk.uikit.utilities.PrefHelper
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import lib.finpay.sdk.uikit.view.AppActivity
import lib.finpay.sdk.uikit.view.pin.PinActivity
import lib.finpay.sdk.uikit.view.ppob.alfamart.AlfamartActivity
import lib.finpay.sdk.uikit.view.ppob.bpjs.BpjsActivity
import lib.finpay.sdk.uikit.view.ppob.bpjs.BpjsKesehatanActivity
import lib.finpay.sdk.uikit.view.ppob.finpay.FinpayActivity
import lib.finpay.sdk.uikit.view.ppob.instalment.InstalmentActivity
import lib.finpay.sdk.uikit.view.ppob.insurance.InsuranceActivity
import lib.finpay.sdk.uikit.view.ppob.internettvcable.InternetTvCableActivity
import lib.finpay.sdk.uikit.view.ppob.pascabayar.PascaBayarActivity
import lib.finpay.sdk.uikit.view.ppob.pdam.PDAMActivity
import lib.finpay.sdk.uikit.view.ppob.pegadaian.PegadaianActivity
import lib.finpay.sdk.uikit.view.ppob.pln.PLNActivity
import lib.finpay.sdk.uikit.view.ppob.pulsa_data.PulsaDataActivity
import lib.finpay.sdk.uikit.view.ppob.revenue.RevenueActivity
import lib.finpay.sdk.uikit.view.ppob.telkom.TelkomActivity
import lib.finpay.sdk.uikit.view.ppob.telkomsel.BestTelkomselPackageActivity
import lib.finpay.sdk.uikit.view.ppob.voucher.VoucherTVCableActivity
import lib.finpay.sdk.uikit.view.qris.QRISActivity
import lib.finpay.sdk.uikit.view.topup.TopupActivity
import lib.finpay.sdk.uikit.view.transaction.TransactionHistoryActivity
import lib.finpay.sdk.uikit.view.transfer.TransferActivity
import lib.finpay.sdk.uikit.view.upgrade.UpgradeAccountActivity
import lib.finpay.sdk.uikit.view.wallet.WalletActivity
import lib.finpay.sdk.uikit.view.wallet.WalletSDKActivity

class FinpaySDKUI {

    companion object {
        lateinit var progressDialog: ProgressDialog
        fun connectAccount(
            transNumber: String,
            context: Context,
            credential: Credential
        ) {
            progressDialog = ProgressDialog(context)
            progressDialog.setTitle("Mohon Menunggu")
            progressDialog.setMessage("Sedang Memuat ...")
            progressDialog.setCancelable(false)
            progressDialog.show()
            FinpaySDK.init(context)
            FinpaySDK.prefHelper.setStringToShared(SharedPrefKeys.MERCHANT_USERNAME, credential.getUsername()!!)
            FinpaySDK.prefHelper.setStringToShared(SharedPrefKeys.MERCHANT_PASSWORD, credential.getPassword()!!)
            FinpaySDK.prefHelper.setStringToShared(SharedPrefKeys.MERCHANT_SECRET_KEY, credential.getSecretKey()!!)
            FinpaySDK.prefHelper.setStringToShared(SharedPrefKeys.USER_PHONE_NUMBER, credential.getPhoneNumber()!!)
            FinpaySDK.prefHelper.setStringToShared(SharedPrefKeys.TOKEN_ID, "finpay")
            FinpaySDK.getToken (transNumber, context, {
                FinpaySDK.prefHelper.setStringToShared(SharedPrefKeys.TOKEN_ID, it.tokenID!!)

                if(credential.getUsername() == null || credential.getPassword() == null || credential.getSecretKey() == null || credential.getUsername() == "" || credential.getPassword() == "" || credential.getSecretKey() == "") {
                    DialogUtils.showDialogError(context, "Credential Error", "Client key, username, or password cannot be null or empty. Please set the client key, username, or password")
                    progressDialog.dismiss()
                } else if(credential.getPhoneNumber() == null || credential.getPhoneNumber() == "") {
                    DialogUtils.showDialogError(
                        context,
                        "Required",
                        "Phone number cannot be null or empty"
                    )
                    progressDialog.dismiss()
                } else if(credential.getPhoneNumber().toString().length < 10 || credential.getPhoneNumber().toString().length > 15) {
                    DialogUtils.showDialogError(
                        context,
                        "",
                        "The phone number must be between 10 and 15 digit"
                    )
                    progressDialog.dismiss()
                } else {
                    FinpaySDK.reqActivation(
                        context,
                        credential.getPhoneNumber()!!, transNumber, {
                            if(it.custStatusCode == "003") {
                                progressDialog.dismiss()
                                FinpaySDK.prefHelper.setBooleanToShared(SharedPrefKeys.IS_CONNECT, true)
                                DialogUtils.showDialogSuccess(
                                    context, "Activation Successful",
                                    "Account activation successful, please reconnect",
                                    {
                                        //walletUIBuilder(transNumber, context, credential)
                                    }
                                )
                            } else {
                                progressDialog.dismiss()
                                val intent = Intent(context, PinActivity::class.java)
                                intent.putExtra("pinType", "otp_connect")
                                intent.putExtra("phoneNumber", credential.getPhoneNumber())
                                intent.putExtra("custName", credential.getCustName())
                                intent.putExtra("custStatusCode", it.custStatusCode.toString())
                                intent.putExtra("transNumber", transNumber)
                                context.startActivity(intent)
                            }
                        }, {
                            progressDialog.dismiss()
                            DialogUtils.showDialogError(context, "Error", it)
                        }
                    )
                }
            }, {
                progressDialog.dismiss()
                DialogUtils.showDialogError(context, "Error", it)
            })
        }

        fun logout(context: Context, onSuccess: () -> Unit) {
            FinpaySDK.init(context)
            FinpaySDK.prefHelper.clearDataLogout()
            if(FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT) != true) {
                onSuccess()
                println(FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.TOKEN_ID))
            }
        }


        fun applicationUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, AppActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun qrisUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, QRISActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun walletUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, WalletSDKActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun topupUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, TopupActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun transferUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val upgradeAccount: Boolean = false
                if(upgradeAccount == false) {
                    DialogUtils.showDialogUpgradeAccount(transNumber, context, credential)
                } else {
                    val intent = Intent(context, TransferActivity::class.java)
                    context.startActivity(intent)
                }
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun upgradeAccountUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, UpgradeAccountActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun historyTransactionUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, TransactionHistoryActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun telkomUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, TelkomActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun alfamartUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, AlfamartActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun bpjsUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, BpjsKesehatanActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun finpayUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, FinpayActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun instalmentUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, InstalmentActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun insuranceUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, InsuranceActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun internetTvCableUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, InternetTvCableActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun pascaBayarUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, PascaBayarActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun pdamUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, PDAMActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun pegadaianUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, PegadaianActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun plnUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, PLNActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun pulsaDataUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, PulsaDataActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun revenueUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, RevenueActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun telkomselUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, BestTelkomselPackageActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }

        fun voucherTvCableUIBuilder(transNumber: String, context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, VoucherTVCableActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(transNumber, context, credential)
            }
        }
    }
}