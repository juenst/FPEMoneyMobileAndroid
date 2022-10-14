//package lib.finpay.sdk.uikit.utilities.widget
//
//import android.R
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnDismissListener;
//import android.graphics.drawable.AnimationDrawable;
//import android.view.Gravity;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//
//class CustomProgress : Dialog, OnDismissListener {
//    constructor(context: Context) : super(context) {}
//    constructor(context: Context, theme: Int) : super(context, theme) {}
//
//    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        if (hasFocus) {
//            val imageView: ImageView = findViewById(R.id.spinnerImageView) as ImageView
//            val spinner = imageView
//                .getBackground() as AnimationDrawable
//            spinner.start()
//        }
//    }
//
//    fun setMessage(message: CharSequence?) {
//        if (message != null && message.length > 0) {
//            findViewById(R.id.message).setVisibility(View.VISIBLE)
//            val txt = findViewById(R.id.message) as TextView
//            txt.text = message
//            txt.invalidate()
//        }
//    }
//
//    override fun onDismiss(arg0: DialogInterface?) {
//        println("dismiss is called")
//    }
//
//    companion object {
//        fun show(
//            context: Context, message: String?,
//            indeterminate: Boolean, cancelable: Boolean
//        ): CustomProgress {
//            val dialog = CustomProgress(context, R.style.ProgressHUD)
//            dialog.setTitle("")
//            dialog.setContentView(R.layout.anim_hud)
//            if (message == null || message.length == 0) {
//                dialog.findViewById(R.id.message).setVisibility(View.GONE)
//            } else {
//                val txt = dialog.findViewById(R.id.message) as TextView
//                txt.text = message
//            }
//            dialog.setCancelable(cancelable)
//            // dialog.setOnCancelListener(cancelListener);
//            dialog.getWindow()!!.getAttributes().gravity = Gravity.CENTER
//            val lp: WindowManager.LayoutParams = dialog.getWindow()!!.getAttributes()
//            lp.dimAmount = 0.2f
//            dialog.getWindow()!!.setAttributes(lp)
//            // dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
//            try {
//                show()
//            } catch (e: Exception) {
//                // TODO: handle exception
//                e.printStackTrace()
//            }
//            return dialog
//        }
//    }
//}