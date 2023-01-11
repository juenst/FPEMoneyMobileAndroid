package lib.finpay.sdk.uikit.utilities

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
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
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.corekit.model.Credential
import lib.finpay.sdk.uikit.FinpaySDKUI
import lib.finpay.sdk.uikit.constant.PaymentType
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.view.payment.PaymentActivity
import java.text.SimpleDateFormat
import java.util.*


class DialogUtils {

    companion object{
        fun showDialogConnectAccount(
            transNumber: String,
            context: Context,
            credential: Credential,
            theme: FinpayTheme? = null,
            onSuccess: () -> Unit
        ) {
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(R.layout.dialog_connect_account)
            val bottomSheetInternal: FrameLayout = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
            bottomSheetInternal.setBackgroundResource(R.drawable.rounded_bottomsheet_dialog)
            val btnConnect = dialog.findViewById<CardView>(R.id.btnConnect)
            btnConnect!!.setCardBackgroundColor(if(theme?.getPrimaryColor() == null) Color.parseColor("#00ACBA") else theme.getPrimaryColor()!!)
            btnConnect.setOnClickListener {
                FinpaySDKUI.connectAccount(transNumber, context, credential, theme, {
                    onSuccess()
                    dialog.dismiss()
                })
            }
            dialog.show()
        }

        fun showDialogUpgradeAccount(
            transNumber: String,
            context: Context,
            credential: Credential,
            theme: FinpayTheme? = null
        ) {
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(R.layout.dialog_upgrade_account)
            val btnUpgrade = dialog.findViewById<CardView>(R.id.btnUpgrade)
            btnUpgrade!!.setCardBackgroundColor(if(theme?.getPrimaryColor() == null) Color.parseColor("#00ACBA") else theme.getPrimaryColor()!!)

            btnUpgrade.setOnClickListener {
                FinpaySDKUI.upgradeAccountUIBuilder(transNumber, context, credential)
                dialog.dismiss()
            }
            dialog.show()
        }

        fun showDialogError(
            context: Context,
            titleDialog: String,
            descriptionDialog: String,
            theme: FinpayTheme? = null
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
            button!!.setCardBackgroundColor(if(theme?.getPrimaryColor() == null) Color.parseColor("#00ACBA") else theme.getPrimaryColor()!!)
            if(titleDialog == "") {
                title?.visibility = View.GONE
            } else {
                title?.visibility = View.VISIBLE
                title?.text = titleDialog
            }
            desc?.text = descriptionDialog

            button.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        fun showDialogSuccess(
            context: Context,
            titleDialog: String,
            descriptionDialog: String,
            onTap: () -> Unit,
            theme: FinpayTheme? = null
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
            img?.setImageDrawable(context.resources.getDrawable(R.drawable.ic_success))
            button!!.setCardBackgroundColor(if(theme?.getPrimaryColor() == null) Color.parseColor("#00ACBA") else theme.getPrimaryColor()!!)
            if(titleDialog == "") {
                title?.visibility = View.GONE
            } else {
                title?.visibility = View.VISIBLE
                title?.text = titleDialog
            }
            desc?.text = descriptionDialog

            button.setOnClickListener {
                onTap()
                dialog.dismiss()
            }
            dialog.show()
        }

        fun showDialogComingSoon(
            context: Context,
            theme: FinpayTheme? = null
        ) {
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(R.layout.dialog_coming_soon)
            val btnClose = dialog.findViewById<CardView>(R.id.btnClose)
            btnClose!!.setCardBackgroundColor(if(theme?.getPrimaryColor() == null) Color.parseColor("#00ACBA") else theme.getPrimaryColor()!!)

            btnClose.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        fun showDialogConfirmPayment(
            context: Context,
            transNumber: String,
            title: String,
            subtitle: String,
            currentBalance: String,
            tagihan: String,
            fee: String,
            onTap: () -> Unit,
            theme: FinpayTheme? = null
        ) {
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(R.layout.dialog_confirm_payment_ppob)
            val btnPay = dialog.findViewById<CardView>(R.id.btnPay)
            val txtTitle = dialog.findViewById<TextView>(R.id.txtTitle)
            val txtBtnPay = dialog.findViewById<TextView>(R.id.txtBtnPay)
            val txtSubTitle = dialog.findViewById<TextView>(R.id.txtSubTitle)
            val txtPrice = dialog.findViewById<TextView>(R.id.txtPrice)
            val txtBiayaLayanan= dialog.findViewById<TextView>(R.id.txtBiayaLayanan)
            val txtTotalBayar= dialog.findViewById<TextView>(R.id.txtTotalBayar)
            val txtSaldo= dialog.findViewById<TextView>(R.id.saldo)
            val cardWarning= dialog.findViewById<CardView>(R.id.cardWarning)
            val logoProvider= dialog.findViewById<ImageView>(R.id.logoProvider)

            //theming
            btnPay!!.setCardBackgroundColor(if(theme?.getPrimaryColor() == null) Color.parseColor("#00ACBA") else theme.getPrimaryColor()!!)
//            cardWarning!!.setCardBackgroundColor(if(theme?.getSecondaryColor() == null) Color.parseColor("#00ACBA") else theme.getSecondaryColor()!!)

            if(currentBalance.toInt() < (tagihan.toInt() + fee.toInt())){
                cardWarning!!.visibility = View.VISIBLE
                txtBtnPay!!.text = "Isi Saldo"
            }
            txtTitle!!.text = title
            txtSubTitle!!.text = subtitle
            txtPrice!!.text = TextUtils.formatRupiah(tagihan.toDouble())
            txtBiayaLayanan!!.text = TextUtils.formatRupiah(fee.toDouble())
            txtTotalBayar!!.text = TextUtils.formatRupiah((tagihan.toInt() + fee.toInt()).toDouble())
            txtSaldo!!.text = TextUtils.formatRupiah(currentBalance.toDouble())

            btnPay.setOnClickListener {
                if(currentBalance.toInt() < (tagihan.toInt() + fee.toInt())){
                    FinpaySDKUI.topupUIBuilder(transNumber, context, Credential(), theme)
                } else {
                    onTap()
                }
            }
            dialog.show()
        }
    }
}