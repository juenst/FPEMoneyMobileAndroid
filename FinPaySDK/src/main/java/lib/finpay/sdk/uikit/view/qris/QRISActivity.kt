package lib.finpay.sdk.uikit.view.qris

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.SurfaceHolder
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
import lib.finpay.sdk.R
import lib.finpay.sdk.databinding.ActivityQrisBinding
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.DialogUtils
import java.io.*

//class QRISActivity : AppCompatActivity() {
//    lateinit var appbar: androidx.appcompat.widget.Toolbar
//    lateinit var appbarTitle: TextView
//    lateinit var progressDialog: ProgressDialog
//    lateinit var btnBack: ImageView
//    lateinit var btnGallery: Button
//    private val requestCodeCameraPermission = 1001
//    private lateinit var cameraSource: CameraSource
//    private lateinit var barcodeDetector: BarcodeDetector
//    private var scannedValue = ""
//
//    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
//    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}
//
//    private lateinit var binding: ActivityQrisBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityQrisBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//        supportActionBar!!.hide()
//
//        //theming
//        binding.appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
//        binding.appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
//        binding.btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
//        binding.btnGallery.setBackgroundColor(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)
//        binding.cameraOverlay.setColorFilter(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)
//
//        progressDialog = ProgressDialog(this@QRISActivity)
//
//        //theming
//        binding.appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
//        binding.appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
//        binding.btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
//        binding.btnFlash.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
//        binding.btnGallery.setBackgroundColor(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getPrimaryColor()!!)
//        binding.btnGallery.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val window: Window = window
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            if(finpayTheme?.getAppBarBackgroundColor() == null) {
//                window.setStatusBarColor(Color.parseColor("#333333"))
//            } else {
//                window.setStatusBarColor(finpayTheme?.getAppBarBackgroundColor()!!)
//            }
//        }
//
//        binding.btnGallery.setOnClickListener {
//            openGallery()
//        }
//
//        if (ContextCompat.checkSelfPermission(this@QRISActivity, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            askForCameraPermission()
//        } else {
//            setupControls()
//        }
//
//        val aniSlide: Animation = AnimationUtils.loadAnimation(this@QRISActivity, R.anim.scanner_animation)
//        binding.barcodeLine.startAnimation(aniSlide)
//    }
//
//
//    private fun setupControls() {
//        barcodeDetector = BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build()
//        cameraSource = CameraSource.Builder(this, barcodeDetector)
//            .setRequestedPreviewSize(1920, 1080)
//            .setAutoFocusEnabled(true)
//            .build()
//
//        binding.cameraSurfaceView.getHolder().addCallback(object : SurfaceHolder.Callback {
//            @SuppressLint("MissingPermission")
//            override fun surfaceCreated(holder: SurfaceHolder) {
//                try {
//                    //Start preview after 1s delay
//                    cameraSource.start(holder)
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//            }
//
//            @SuppressLint("MissingPermission")
//            override fun surfaceChanged(
//                holder: SurfaceHolder,
//                format: Int,
//                width: Int,
//                height: Int
//            ) {
//                try {
//                    cameraSource.start(holder)
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//            }
//
//            override fun surfaceDestroyed(holder: SurfaceHolder) {
//                cameraSource.stop()
//            }
//        })
//
//
//        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
//            override fun release() {
//                DialogUtils.showDialogError(applicationContext, "", "Scanner has been closed", finpayTheme)
//            }
//
//            override fun receiveDetections(detections: Detections<Barcode>) {
//                val barcodes = detections.detectedItems
//                println("masuk sini woi")
//                println(barcodes)
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
//                        DialogUtils.showDialogError(this@QRISActivity, "", "Format QRIS salah")
//                    }
//                }else {
////                    progressDialog.dismiss()
//                    cameraSource.stop()
//                    this@QRISActivity.finish()
//                    DialogUtils.showDialogError(this@QRISActivity, "", "value- else")
//                }
//            }
//        })
//    }
//
//    private fun askForCameraPermission() {
//        ActivityCompat.requestPermissions(
//            this@QRISActivity,
//            arrayOf(android.Manifest.permission.CAMERA),
//            requestCodeCameraPermission
//        )
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == requestCodeCameraPermission && grantResults.isNotEmpty()) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                setupControls()
//            } else {
//                Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        cameraSource.stop()
//    }
//
//    private fun openGallery() {
//        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        resultLauncherGallery.launch(galleryIntent)
//    }
//
//    var resultLauncherGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            val data: Intent? = result.data
//
//            val imageUri = data!!.data!!
//            val imagePath = convertMediaUriToPath(imageUri)
//            val imgFile = File(imagePath)
//            scanImageQRCode(imgFile)
//        } else {
//            println("Cancel")
//        }
//    }
//
//    private fun convertMediaUriToPath(uri: Uri):String {
//        val proj = arrayOf<String>(MediaStore.Images.Media.DATA)
//        val cursor = contentResolver.query(uri, proj, null, null, null)
//        val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//        cursor.moveToFirst()
//        val path = cursor.getString(columnIndex)
//        cursor.close()
//        return path
//    }
//
//    private fun scanImageQRCode(file: File){
//        val inputStream: InputStream = BufferedInputStream(FileInputStream(file))
//        val bitmap = BitmapFactory.decodeStream(inputStream)
//        val decoded = scanQRImage(bitmap)
//        Log.i("QrTest", "Decoded string=$decoded")
//    }
//
//    private fun scanQRImage(bMap: Bitmap): String? {
//        var contents: String? = null
//        val intArray = IntArray(bMap.width * bMap.height)
//        bMap.getPixels(intArray, 0, bMap.width, 0, 0, bMap.width, bMap.height)
//        val source: LuminanceSource = RGBLuminanceSource(bMap.width, bMap.height, intArray)
//        val bitmap = BinaryBitmap(HybridBinarizer(source))
//        val reader: MultiFormatReader = MultiFormatReader()
//        cameraSource.stop()
//        try {
//            val result: com.google.zxing.Result = reader.decode(bitmap)
//            contents = result.text
//            println("QR code -> ${contents}")
//            if(contents.contains("QRIS")) {
//                val intent = Intent(this@QRISActivity, QRISResultActivity::class.java)
//                intent.putExtra("transNumber", transNumber!!)
//                intent.putExtra("theme", finpayTheme)
//                intent.putExtra("resultQR", "${contents}")
//                startActivity(intent)
//                this@QRISActivity.finish()
//            } else {
//                DialogUtils.showDialogError(this, "", "Invalid QR Format")
//            }
//        } catch (e: Exception) {
//            Log.e("QrTest", "Error decoding qr code", e)
//            DialogUtils.showDialogError(this, "", "Error decoding QR Code, Mohon pilih gambar QR Code yang benar!")
//        }
//        return contents
//    }
//}

class QRISActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    lateinit var progressDialog: ProgressDialog
    lateinit var btnBack: ImageView
    lateinit var btnGallery: Button
    private val requestCodeCameraPermission = 1001
    private val requestCodeGalleryPermission = 1001
    private lateinit var cameraSource: CameraSource
    private lateinit var barcodeDetector: BarcodeDetector
    private var scannedValue = ""
    var flashLightStatus: Boolean = false

    private lateinit var binding: ActivityQrisBinding

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrisBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar!!.hide()

        progressDialog = ProgressDialog(this@QRISActivity)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        //theming
        binding.appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        binding.appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        binding.btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        binding.cameraOverlay.setColorFilter(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)
        binding.btnGallery.setBackgroundColor(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getPrimaryColor()!!)
        binding.btnGallery.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if(finpayTheme?.getAppBarBackgroundColor() == null) {
                window.setStatusBarColor(Color.parseColor("#333333"))
            } else {
                window.setStatusBarColor(finpayTheme?.getAppBarBackgroundColor()!!)
            }
        }

        binding.btnGallery.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this@QRISActivity, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                askForGalleryPermission()
            } else {
                openGallery()
            }
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


        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {
                DialogUtils.showDialogError(this@QRISActivity, "", "Scanner has been closed", finpayTheme)
            }

            override fun receiveDetections(detections: Detections<Barcode>) {
                val barcodes = detections.detectedItems
                if (barcodes.size() == 1) {
                    scannedValue = barcodes.valueAt(0).rawValue
                    if(scannedValue.contains("QRIS")) {
                        runOnUiThread {
                            cameraSource.stop()
                            val intent = Intent(this@QRISActivity, QRISResultActivity::class.java)
                            intent.putExtra("transNumber", transNumber!!)
                            intent.putExtra("theme", finpayTheme)
                            intent.putExtra("resultQR", "${scannedValue}")
                            startActivity(intent)
                            this@QRISActivity.finish()
                        }
                    } else {
                        DialogUtils.showDialogError(this@QRISActivity, "", "Format QRIS salah", finpayTheme)
                    }
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

    private fun askForGalleryPermission() {
        ActivityCompat.requestPermissions(
            this@QRISActivity,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            requestCodeGalleryPermission
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
                DialogUtils.showDialogError(this@QRISActivity, "", "Permission Denied", finpayTheme)
            }
        }

        if (requestCode == requestCodeGalleryPermission && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                DialogUtils.showDialogError(this@QRISActivity, "", "Permission Denied", finpayTheme)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraSource.stop()
    }

    //===============

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncherGallery.launch(galleryIntent)
    }

    var resultLauncherGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val imageUri = data!!.data!!
            val imagePath = convertMediaUriToPath(imageUri)
            println("masuk sini")
            println(imagePath)
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
        println(bitmap)
        val decoded = scanQRImage(bitmap)
        Log.i("QrTest", "Decoded string=$decoded")
    }

    private fun scanQRImage(bMap: Bitmap): String? {
        var contents: String? = null
        val intArray = IntArray(bMap.width * bMap.height)
        bMap.getPixels(intArray, 0, bMap.width, 0, 0, bMap.width, bMap.height)
        val source: LuminanceSource = RGBLuminanceSource(bMap.width, bMap.height, intArray)
        val bitmap = BinaryBitmap(HybridBinarizer(source))
        val reader: MultiFormatReader = MultiFormatReader()
        try {
            val result: com.google.zxing.Result = reader.decode(bitmap)
            contents = result.text
            println("QR code -> ${contents}")
            if(contents.contains("QRIS")) {
                val intent = Intent(this@QRISActivity, QRISResultActivity::class.java)
                intent.putExtra("transNumber", transNumber!!)
                intent.putExtra("theme", finpayTheme)
                intent.putExtra("resultQR", "${contents}")
                startActivity(intent)
                this@QRISActivity.finish()
            } else {
                DialogUtils.showDialogError(this, "", "Invalid QR Format", finpayTheme)
            }
        } catch (e: Exception) {
            Log.e("QrTest", "Error decoding qr code", e)
            DialogUtils.showDialogError(this, "", "Error decoding QR Code, Mohon pilih gambar QR Code yang benar!", finpayTheme)
        }
        return contents
    }
}