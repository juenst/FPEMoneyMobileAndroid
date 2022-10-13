package lib.finpay.sdk.uikit

import android.content.Context
import android.content.Intent
import android.widget.Toast
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.model.Credential
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.PinTypeKeys
import lib.finpay.sdk.uikit.utilities.PrefHelper
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import lib.finpay.sdk.uikit.view.AppActivity
import lib.finpay.sdk.uikit.view.pin.PinActivity
import lib.finpay.sdk.uikit.view.qris.QRISActivity
import lib.finpay.sdk.uikit.view.wallet.WalletActivity

class FinpaySDKUI {

    companion object {
        fun connectAccount(
            context: Context,
            credential: Credential
        ) {
            FinpaySDK.init(context)
            FinpaySDK.prefHelper.setStringToShared(SharedPrefKeys.MERCHANT_USERNAME, credential.getUsername()!!)
            FinpaySDK.prefHelper.setStringToShared(SharedPrefKeys.MERCHANT_PASSWORD, credential.getUsername()!!)
            FinpaySDK.prefHelper.setStringToShared(SharedPrefKeys.MERCHANT_SECRET_KEY, credential.getSecretKey()!!)
            FinpaySDK.prefHelper.setStringToShared(SharedPrefKeys.USER_PHONE_NUMBER, credential.getPhoneNumber()!!)
            FinpaySDK.getToken ({
                FinpaySDK.prefHelper.setStringToShared(SharedPrefKeys.TOKEN_ID, it.tokenID!!)

                if(credential.getUsername() == null || credential.getPassword() == null || credential.getSecretKey() == null) {
                    DialogUtils.showDialogError(context, "Credential Error", "Client key, username, or password cannot be null or empty. Please set the client key, username, or password")
                } else if(credential.getPhoneNumber() == null) {
                    DialogUtils.showDialogError(context, "Required", "Phone number cannot be null or empty")
                } else {
                    //cek ke reqActivation dulu
                    //jika custStatusCode 003, sudah bisa connect
                    //jika custStatusCode 001/002 harus memasukan OTP dan hit API reqConfirmation
                    FinpaySDK.reqActivation(
                        context,
                        credential.getPhoneNumber()!!, {
                            if(it.custStatusCode == "003") {
                                FinpaySDK.prefHelper.setBooleanToShared(SharedPrefKeys.IS_CONNECT, true)
                                DialogUtils.showDialogError(context, "Aktifasi Sukses", "Aktifasi akun berhasil, silahkan hubungkan kembali")
                            } else {
                                val intent = Intent(context, PinActivity::class.java)
                                intent.putExtra("pinType", "otp_connect")
                                intent.putExtra("phoneNumber", credential.getPhoneNumber())
                                intent.putExtra("custName", credential.getCustName())
                                intent.putExtra("custStatusCode", it.custStatusCode.toString())
                                context.startActivity(intent)
                            }
                        }, {
                            Toast.makeText(context, it, Toast.LENGTH_LONG)
                        }
                    )
                }
            }, {
                Toast.makeText(context, it, Toast.LENGTH_LONG)
            })
        }

        fun logout(context: Context) {
            FinpaySDK.init(context)
            FinpaySDK.prefHelper.clearDataLogout()
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
                val intent = Intent(context, WalletActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(context, credential)
            }
        }

        fun openTransfer(context: Context, credential: Credential) {
            FinpaySDK.init(context)
            var isConnect: Boolean = FinpaySDK.prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, WalletActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(context, credential)
            }
        }
    }
}