package com.finpay.wallet.view.qris

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.SurfaceHolder
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.finpay.wallet.R
import com.finpay.wallet.databinding.ActivityQrisBinding
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Detector.Detections
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.zxing.BinaryBitmap
import com.google.zxing.LuminanceSource
import com.google.zxing.MultiFormatReader
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.common.HybridBinarizer
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.io.Reader
import java.util.*

class QRISActivity : AppCompatActivity() {
    lateinit var progressDialog: ProgressDialog
    lateinit var btnBack: ImageView
    lateinit var btnGallery: Button
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
        binding.btnGallery.setOnClickListener {
            openGallery()
        }

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


//        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
//            override fun release() {
//                Toast.makeText(applicationContext, "Scanner has been closed", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun receiveDetections(detections: Detections<Barcode>) {
//                val barcodes = detections.detectedItems
//                if (barcodes.size() == 1) {
////                    progressDialog.setTitle("Mohon Menunggu")
////                    progressDialog.setMessage("Sedang Memuat ...")
////                    progressDialog.setCancelable(false) // blocks UI interaction
////                    progressDialog.show()
//
//                    scannedValue = barcodes.valueAt(0).rawValue
//                    if(scannedValue.contains("QRIS")) {
//                        runOnUiThread {
//                            cameraSource.stop()
//                            println(scannedValue)
////                            progressDialog.dismiss()
//                            val intent = Intent(this@QRISActivity, QRISResultActivity::class.java)
//                            intent.putExtra("resultQR", "${scannedValue}")
//                            startActivity(intent)
//                            this@QRISActivity.finish()
//                        }
//                    } else {
////                        progressDialog.dismiss()
//                        cameraSource.stop()
//                        this@QRISActivity.finish()
//                        Toast.makeText(this@QRISActivity, "Format QRIS salah", Toast.LENGTH_SHORT).show()
//                    }
//                }else {
////                    progressDialog.dismiss()
//                    cameraSource.stop()
//                    this@QRISActivity.finish()
//                    Toast.makeText(this@QRISActivity, "value- else", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
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

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncherGallery.launch(galleryIntent)
    }

    var resultLauncherGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data

            val imageUri = data!!.data!!
            val imagePath = convertMediaUriToPath(imageUri)
            val imgFile = File(imagePath)
            scanImageQRCode(imgFile)
        } else {
            println("Cancel")
        }
    }

    private fun convertMediaUriToPath(uri: Uri):String {
        val proj = arrayOf<String>(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri, proj, null, null, null)
        val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val path = cursor.getString(columnIndex)
        cursor.close()
        return path
    }

    private fun scanImageQRCode(file: File){
        val inputStream: InputStream = BufferedInputStream(FileInputStream(file))
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val decoded = scanQRImage(bitmap)
        Log.i("QrTest", "Decoded string=$decoded")
    }

    private fun scanQRImage(bMap: Bitmap): String? {
//        progressDialog.setTitle("Mohon Menunggu")
//        progressDialog.setMessage("Sedang Memuat ...")
//        progressDialog.setCancelable(false) // blocks UI interaction
//        progressDialog.show()

        var contents: String? = null
        val intArray = IntArray(bMap.width * bMap.height)
        //copy pixel data from the Bitmap into the 'intArray' array
        bMap.getPixels(intArray, 0, bMap.width, 0, 0, bMap.width, bMap.height)
        val source: LuminanceSource = RGBLuminanceSource(bMap.width, bMap.height, intArray)
        val bitmap = BinaryBitmap(HybridBinarizer(source))
        val reader: MultiFormatReader = MultiFormatReader()
        cameraSource.stop()
        try {
            val result: com.google.zxing.Result = reader.decode(bitmap)
            contents = result.text
            println("QR code -> ${contents}")
//            progressDialog.dismiss()
            val intent = Intent(this@QRISActivity, QRISResultActivity::class.java)
            intent.putExtra("resultQR", "${contents}")
            startActivity(intent)
            this@QRISActivity.finish()
        } catch (e: Exception) {
//            progressDialog.dismiss()
            Log.e("QrTest", "Error decoding qr code", e)
            Toast.makeText(this, "Error decoding QR Code, Mohon pilih gambar QR Code yang benar!", Toast.LENGTH_SHORT).show()
        }
        return contents
    }
}