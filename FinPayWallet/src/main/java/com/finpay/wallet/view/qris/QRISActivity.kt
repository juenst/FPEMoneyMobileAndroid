package com.finpay.wallet.view.qris

import android.Manifest
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.finpay.wallet.R
import com.finpay.wallet.constant.Constant
import com.finpay.wallet.databinding.ActivityQrisBinding
import com.finpay.wallet.view.pin.PinActivity
import com.finpay.wallet.view.upgrade.UpgradeAccountSelfieResultActivity
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Detector.Detections
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.zxing.integration.android.IntentIntegrator
import com.midtrans.sdk.corekit.utilities.Utils
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class QRISActivity : AppCompatActivity() {
    lateinit var progressDialog: ProgressDialog
    lateinit var btnBack: ImageView
    private val requestCodeCameraPermission = 1001
    private lateinit var cameraSource: CameraSource
    private lateinit var barcodeDetector: BarcodeDetector
    private var scannedValue = ""

    private lateinit var binding: ActivityQrisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrisBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar!!.hide()

        progressDialog = ProgressDialog(this@QRISActivity)

        if (ContextCompat.checkSelfPermission(this@QRISActivity, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            askForCameraPermission()
        } else {
            setupControls()
        }

        val aniSlide: Animation = AnimationUtils.loadAnimation(this@QRISActivity, R.anim.scanner_animation)
        binding.barcodeLine.startAnimation(aniSlide)
    }

    private fun setupControls() {
        barcodeDetector = BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build()
        cameraSource = CameraSource.Builder(this, barcodeDetector)
            .setRequestedPreviewSize(1920, 1080)
            .setAutoFocusEnabled(true) //you should add this feature
            .build()

        binding.cameraSurfaceView.getHolder().addCallback(object : SurfaceHolder.Callback {
            @SuppressLint("MissingPermission")
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    //Start preview after 1s delay
                    cameraSource.start(holder)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            @SuppressLint("MissingPermission")
            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
                try {
                    cameraSource.start(holder)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource.stop()
            }
        })


        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {
                Toast.makeText(applicationContext, "Scanner has been closed", Toast.LENGTH_SHORT).show()
            }

            override fun receiveDetections(detections: Detections<Barcode>) {
//                progressDialog.setTitle("Mohon Menunggu")
//                progressDialog.setMessage("Sedang Memuat ...")
//                progressDialog.setCancelable(false) // blocks UI interaction
//                progressDialog.show()

                val barcodes = detections.detectedItems
                if (barcodes.size() == 1) {
                    scannedValue = barcodes.valueAt(0).rawValue
                    if(scannedValue.contains("FINPAY")) {
                        runOnUiThread {
                            cameraSource.stop()
//                            progressDialog.dismiss()
                            val intent = Intent(this@QRISActivity, QRISResultActivity::class.java)
                            intent.putExtra("resultQR", "${scannedValue}")
                            startActivity(intent)
                            this@QRISActivity.finish()
                        }
                    } else {
//                        progressDialog.dismiss()
                        //Toast.makeText(this@QRISActivity, "Format QRIS salah", Toast.LENGTH_SHORT).show()
                    }
                }else {
//                    progressDialog.dismiss()
                    //Toast.makeText(this@QRISActivity, "value- else", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun askForCameraPermission() {
        ActivityCompat.requestPermissions(
            this@QRISActivity,
            arrayOf(android.Manifest.permission.CAMERA),
            requestCodeCameraPermission
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestCodeCameraPermission && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupControls()
            } else {
                Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraSource.stop()
    }
}