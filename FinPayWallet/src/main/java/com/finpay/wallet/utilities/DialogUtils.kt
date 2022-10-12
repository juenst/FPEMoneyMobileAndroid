package com.finpay.wallet.utilities

import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import com.finpay.wallet.R
import com.finpay.wallet.view.pin.PinActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class DialogUtils {

    companion object{
        fun showDialogConnectAccount(
            context: Context
        ) {
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(R.layout.dialog_connect_account)
            val btnConnect = dialog.findViewById<Button>(R.id.btnConnect)

            btnConnect?.setOnClickListener {
                println("connect")
            }
            dialog.show()
        }
    }
}