package lib.finpay.sdk.uikit

import android.content.Context
import com.finpay.wallet.utilities.DialogUtils

class FinpaySDKUI {
    companion object {
        fun openQris(
            context: Context
        ) {
            DialogUtils.showDialogConnectAccount(context)
        }
    }
}