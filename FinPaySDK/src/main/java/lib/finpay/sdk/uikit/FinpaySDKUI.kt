package lib.finpay.sdk.uikit

import android.content.Context
import android.content.Intent
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.view.AppActivity

class FinpaySDKUI {
    companion object {
        fun openWallet(
            context: Context
        ) {
            val intent = Intent(context, AppActivity::class.java)
            context.startActivity(intent)
        }

        fun openQris(
            context: Context
        ) {
            DialogUtils.showDialogConnectAccount(context)
        }
    }
}