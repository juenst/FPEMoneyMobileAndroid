package lib.finpay.sdk.uikit.utilities

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.R
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

        fun showDialogUpgradeAccount(
            context: Context,
            credential: Credential
        ) {
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(R.layout.dialog_upgrade_account)
            val btnUpgrade = dialog.findViewById<Button>(R.id.btnUpgrade)

            btnUpgrade?.setOnClickListener {
                FinpaySDKUI.openUpgradeAccount(context, credential)
            }
            dialog.show()
        }

        fun showDialogError(
            context: Context,
            titleDialog: String,
            descriptionDialog: String
        ) {
            val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val builder = AlertDialog.Builder(context,R.style.CustomAlertDialog).create()
            val view = layoutInflater.inflate(R.layout.dialog_alert, null)
            val img = view.findViewById<ImageView>(R.id.img)
            val title = view.findViewById<TextView>(R.id.txtTitle)
            val desc = view.findViewById<TextView>(R.id.txtDesc)
            val button = view.findViewById<Button>(R.id.btnDismiss)
            if(titleDialog == "") {
                title.visibility = View.GONE
            } else {
                title.visibility = View.VISIBLE
                title.text = titleDialog
            }
            desc.text = descriptionDialog
            builder.setView(view)
            button.setOnClickListener {
                builder.dismiss()
            }
            builder.setCanceledOnTouchOutside(false)
            builder.show()
        }
    }
}