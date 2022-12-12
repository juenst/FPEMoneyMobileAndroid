package lib.finpay.sdk.uikit.utilities

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.Credential
import lib.finpay.sdk.uikit.FinpaySDKUI


class DialogUtils {

    companion object{
        fun showDialogConnectAccount(
            transNumber: String,
            context: Context,
            credential: Credential
        ) {
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(R.layout.dialog_connect_account)
            val bottomSheetInternal: FrameLayout = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
            bottomSheetInternal.setBackgroundResource(R.drawable.rounded_bottomsheet_dialog)
            val btnConnect = dialog.findViewById<CardView>(R.id.btnConnect)
            btnConnect?.setOnClickListener {
                FinpaySDKUI.connectAccount(transNumber, context, credential)
                dialog.dismiss()
            }
            dialog.show()
        }

        fun showDialogUpgradeAccount(
            transNumber: String,
            context: Context,
            credential: Credential
        ) {
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(R.layout.dialog_upgrade_account)
            val btnUpgrade = dialog.findViewById<Button>(R.id.btnUpgrade)

            btnUpgrade?.setOnClickListener {
                FinpaySDKUI.upgradeAccountUIBuilder(transNumber, context, credential)
            }
            dialog.show()
        }

        fun showDialogError(
            context: Context,
            titleDialog: String,
            descriptionDialog: String
        ) {
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(R.layout.dialog_alert)
            val bottomSheetInternal: FrameLayout = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
            bottomSheetInternal.setBackgroundResource(R.drawable.rounded_bottomsheet_dialog)
            dialog.setCancelable(true)
            val img = dialog.findViewById<ImageView>(R.id.img)
            val title = dialog.findViewById<TextView>(R.id.txtTitle)
            val desc = dialog.findViewById<TextView>(R.id.txtDesc)
            val button = dialog.findViewById<CardView>(R.id.btnClose)
            img?.setImageDrawable(context.resources.getDrawable(R.drawable.ic_failed))
            if(titleDialog == "") {
                title?.visibility = View.GONE
            } else {
                title?.visibility = View.VISIBLE
                title?.text = titleDialog
            }
            desc?.text = descriptionDialog

            button?.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        fun showDialogSuccess(
            context: Context,
            titleDialog: String,
            descriptionDialog: String,
            onTap: () -> Unit
        ) {
//            val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            val builder = AlertDialog.Builder(context,R.style.CustomAlertDialog).create()
//            val view = layoutInflater.inflate(R.layout.dialog_alert, null)
//            val img = view.findViewById<ImageView>(R.id.img)
//            val title = view.findViewById<TextView>(R.id.txtTitle)
//            val desc = view.findViewById<TextView>(R.id.txtDesc)
//            val button = view.findViewById<CardView>(R.id.btnClose)
//
//            img.setImageDrawable(context.resources.getDrawable(R.drawable.ic_success))
//            if(titleDialog == "") {
//                title.visibility = View.GONE
//            } else {
//                title.visibility = View.VISIBLE
//                title.text = titleDialog
//            }
//            desc.text = descriptionDialog
//            builder.setView(view)
//            button.setOnClickListener {
//                builder.dismiss()
//                onTap()
//            }
//            builder.setCanceledOnTouchOutside(false)
//            builder.show()
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(R.layout.dialog_alert)
            val bottomSheetInternal: FrameLayout = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
            bottomSheetInternal.setBackgroundResource(R.drawable.rounded_bottomsheet_dialog)
            dialog.setCancelable(true)
            val img = dialog.findViewById<ImageView>(R.id.img)
            val title = dialog.findViewById<TextView>(R.id.txtTitle)
            val desc = dialog.findViewById<TextView>(R.id.txtDesc)
            val button = dialog.findViewById<CardView>(R.id.btnClose)
            img?.setImageDrawable(context.resources.getDrawable(R.drawable.ic_success))
            if(titleDialog == "") {
                title?.visibility = View.GONE
            } else {
                title?.visibility = View.VISIBLE
                title?.text = titleDialog
            }
            desc?.text = descriptionDialog

            button?.setOnClickListener {
                onTap()
            }
            dialog.show()
        }

        fun showDialogComingSoon(
            context: Context
        ) {
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(R.layout.dialog_coming_soon)
            val btnClose = dialog.findViewById<CardView>(R.id.btnClose)

            btnClose?.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}