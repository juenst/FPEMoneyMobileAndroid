package lib.finpay.sdk.uikit.utilities

import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import lib.finpay.sdk.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.corekit.model.Credential
import lib.finpay.sdk.uikit.FinpaySDKUI

class DialogUtils {

    companion object{
        fun showDialogConnectAccount(
            context: Context,
            credential: Credential
        ) {
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(R.layout.dialog_connect_account)
            val btnConnect = dialog.findViewById<Button>(R.id.btnConnect)

            btnConnect?.setOnClickListener {
                FinpaySDKUI.connectAccount(context, credential)
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}