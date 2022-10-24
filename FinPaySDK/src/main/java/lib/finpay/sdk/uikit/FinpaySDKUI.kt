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
import lib.finpay.sdk.uikit.view.qris.QRISActivity
import lib.finpay.sdk.uikit.view.transaction.TransactionHistoryActivity
import lib.finpay.sdk.uikit.view.transfer.TransferActivity
import lib.finpay.sdk.uikit.view.upgrade.UpgradeAccountActivity
import lib.finpay.sdk.uikit.view.wallet.WalletActivity
import lib.finpay.sdk.uikit.view.wallet.WalletSDKActivity

class FinpaySDKUI {

    companion object {
        lateinit var progressDialog: ProgressDialog
        fun connectAccount(
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
            FinpaySDK.getToken (context, {
                FinpaySDK.prefHelper.setStringToShared(SharedPrefKeys.TOKEN_ID, it.tokenID!!)

                if(credential.getUsername() == null || credential.getPassword() == null || credential.getSecretKey() == null) {
                    DialogUtils.showDialogError(context, "Credential Error", "Client key, username, or password cannot be null or empty. Please set the client key, username, or password")
                    progressDialog.dismiss()
                } else if(credential.getPhoneNumber() == null) {
                    DialogUtils.showDialogError(context, "Required", "Phone number cannot be null or empty")
                    progressDialog.dismiss()
                } else {
                    FinpaySDK.reqActivation(
                        context,
                        credential.getPhoneNumber()!!, {
                            if(it.custStatusCode == "003") {
                                progressDialog.dismiss()
                                FinpaySDK.prefHelper.setBooleanToShared(SharedPrefKeys.IS_CONNECT, true)
                                DialogUtils.showDialogSuccess(
                                    context, "Aktivasi Sukses",
                                    "Aktivasi akun berhasil, silahkan hubungkan kembali",
                                    {
                                        openWallet(context, credential)
                                    }
                                )
                            } else {
                                progressDialog.dismiss()
                                val intent = Intent(context, PinActivity::class.java)
                                intent.putExtra("pinType", "otp_connect")
                                intent.putExtra("phoneNumber", credential.getPhoneNumber())
                                intent.putExtra("custName", credential.getCustName())
                                intent.putExtra("custStatusCode", it.custStatusCode.toString())
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
                println("masuk sini")
                println(FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.TOKEN_ID))
            }
        }


        fun openApplication(context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, AppActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(context, credential)
            }
        }

        fun openQris(context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, QRISActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(context, credential)
            }
        }

        fun openWallet(context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, WalletSDKActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(context, credential)
            }
        }

        fun openTransfer(context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val upgradeAccount: Boolean = false
                if(upgradeAccount == false) {
                    DialogUtils.showDialogUpgradeAccount(context, credential)
                } else {
                    val intent = Intent(context, TransferActivity::class.java)
                    context.startActivity(intent)
                }
            } else {
                DialogUtils.showDialogConnectAccount(context, credential)
            }
        }

        fun openUpgradeAccount(context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, UpgradeAccountActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(context, credential)
            }
        }

        fun openHistoryTransaction(context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, TransactionHistoryActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(context, credential)
            }
        }
    }
}