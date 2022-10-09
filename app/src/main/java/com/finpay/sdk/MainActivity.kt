package com.finpay.sdk

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.finpay.sdk.constant.Constant
import lib.finpay.sdk.FinPaySDK
import com.finpay.wallet.view.AppActivity
import com.finpay.wallet.view.splash.SplashActivity


class MainActivity : AppCompatActivity() {
    lateinit var finPaySDK: FinPaySDK

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textTokenId = findViewById(R.id.tokenId) as TextView
        var textSaldo = findViewById(R.id.saldo) as TextView
        var btnCallSDK = findViewById(R.id.btn_call_sdk) as TextView
        var btnCallWallet = findViewById(R.id.btn_call_wallet) as TextView
        val btnOpenDialogQr = findViewById<Button>(R.id.btn_open_dialog_qr)
        finPaySDK = FinPaySDK()

//        finPaySDK.buildSDK(
//            this,
//            Constant().userName,
//            Constant().password,
//            Constant().secretKey,
//        )

        btnCallSDK.setOnClickListener {
            FinPaySDK().getToken(
                onResult = {
                    token->
                    textTokenId.setText(token.getTokenID())
                }
            )
            FinPaySDK().getToken(
                onResult = {
                    token->
                    textTokenId.setText(token.getTokenID())
                }
            )
        }


        btnCallWallet.setOnClickListener {
            println("test")
            Intent(this, SplashActivity::class.java).apply {
                startActivity(this)
            }
        }

        btnOpenDialogQr.setOnClickListener {
            openQrDialog()
        }
    }

    private fun openQrDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(com.finpay.wallet.R.layout.dialog_share_qr)

        val btnShare = dialog.findViewById<Button>(com.finpay.wallet.R.id.btn_share_qr)
        btnShare.setOnClickListener {
            val qrImage = dialog.findViewById<ImageView>(com.finpay.wallet.R.id.qr_image)
            val mDrawable: Drawable = qrImage.drawable
            val mBitmap = (mDrawable as BitmapDrawable).bitmap
            val path = MediaStore.Images.Media.insertImage(
                contentResolver,
                mBitmap,
                "Image Description",
                null
            )
            val uri = Uri.parse(path)
            val share = Intent(Intent.ACTION_SEND)
            share.type = "image/*"
            share.putExtra(Intent.EXTRA_STREAM, uri)
            startActivity(Intent.createChooser(share, "Bagikan QR kode"))
            dialog.dismiss()
        }
        dialog.show()
    }
}