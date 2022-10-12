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

class FinpaySDKUI {

    companion object {
        lateinit var prefHelper: PrefHelper
        fun connectAccount(
            context: Context,
            credential: Credential
        ) {
            prefHelper = PrefHelper()
            PrefHelper.setSharedPreferences(context, Constant.sharedPreferencesName, Context.MODE_PRIVATE)
            prefHelper.setStringToShared(SharedPrefKeys.MERCHANT_USERNAME, credential.getUsername()!!)
            prefHelper.setStringToShared(SharedPrefKeys.MERCHANT_PASSWORD, credential.getUsername()!!)
            prefHelper.setStringToShared(SharedPrefKeys.MERCHANT_SECRET_KEY, credential.getSecretKey()!!)
            prefHelper.setStringToShared(SharedPrefKeys.USER_PHONE_NUMBER, credential.getPhoneNumber()!!)
            FinpaySDK.getToken ({
                prefHelper.setStringToShared(SharedPrefKeys.TOKEN_ID, it.tokenID!!)

                if(credential.getUsername() == null || credential.getPassword() == null || credential.getSecretKey() == null) {
                    println("Client key, username and password cannot be null or empty. Please set the client key, username and password")
                    Toast.makeText(context, "Client key, username, or password cannot be null or empty. Please set the client key, username, or password", Toast.LENGTH_LONG)
                } else if(credential.getPhoneNumber() == null) {
                    Toast.makeText(context, "Phone number cannot be null or empty", Toast.LENGTH_LONG)
                } else {
                    //cek ke reqActivation dulu
                    //jika custStatusCode 003, sudah bisa connect
                    //jika custStatusCode 001/002 harus hit API reqConfirmation
                    //logic sudah ada di SDK
                    FinpaySDK.reqActivation(
                        credential.getPhoneNumber()!!, {
                            if(it.custStatusCode == "003") {
                                prefHelper.setBooleanToShared(SharedPrefKeys.IS_CONNECT, true)
                                Toast.makeText(context, "Aktifasi akun berhasil, silahkan hubungkan kembali", Toast.LENGTH_LONG)
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
            prefHelper = PrefHelper()
            PrefHelper.setSharedPreferences(context, Constant.sharedPreferencesName, Context.MODE_PRIVATE)
            prefHelper.clearDataLogout()
        }


        fun openWallet(context: Context) {
            val intent = Intent(context, AppActivity::class.java)
            context.startActivity(intent)
        }

        fun openQris(
            context: Context,
            credential: Credential
        ) {
            //jika belum connect account
            //panggil function connect account
            prefHelper = PrefHelper()
            PrefHelper.setSharedPreferences(context, Constant.sharedPreferencesName, Context.MODE_PRIVATE)
            var isConnect: Boolean = prefHelper.getBoolFromShared(SharedPrefKeys.IS_CONNECT)
            if(isConnect == true) {
                val intent = Intent(context, QRISActivity::class.java)
                context.startActivity(intent)
            } else {
                DialogUtils.showDialogConnectAccount(context, credential)
            }
        }
    }
}