package lib.finpay.sdk.uikit.view.upgrade

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.constant.Constant
import lib.finpay.sdk.uikit.utilities.Utils
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class UpgradeAccountSelfieCameraActivity : AppCompatActivity() {
    private lateinit var btnBack: ImageView
    private lateinit var btnTakePicture: CardView
    private var imageCapture: ImageCapture? = null
    private lateinit var outputDirectory: File
    private lateinit var cameraView: PreviewView
    lateinit var progressDialog: ProgressDialog
    var activity: Activity? = null

    val imgResultIdentity: String? by lazy { intent.getStringExtra("imgResultIdentity") }
    val imgResultIdentityBase64: String? by lazy { intent.getStringExtra("imgResultIdentityBase64") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_account_selfie_camera)
        supportActionBar!!.hide()

        outputDirectory = getOutputDirectory()
        progressDialog = ProgressDialog(this)
        btnTakePicture = findViewById(R.id.btnTakePicture)
        btnBack = findViewById(R.id.btnBack)
        cameraView = findViewById(R.id.cameraView)
        activity = this@UpgradeAccountSelfieCameraActivity

        btnBack.setOnClickListener {
            onBackPressed()
        }

        btnTakePicture.setOnClickListener{
            takePic()
        }

        if (allPermissionGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, Constant.REQUIRED_PERMISSIONS,
                Constant.REQUEST_CODE_PERMISSIONS,
            )
        }
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let { mFile ->
            File(mFile, resources.getString(R.string.app_name)).apply {
                mkdirs()
            }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    private fun takePic() {
        progressDialog.setTitle("Mohon Menunggu")
        progressDialog.setMessage("Sedang Memuat ...")
        progressDialog.setCancelable(false) // blocks UI interaction
        progressDialog.show()

        val imageCapture = imageCapture ?: return
        val photoFile = File(outputDirectory, SimpleDateFormat(Constant.FILE_NAME_FORMAT, Locale.getDefault()).format(System.currentTimeMillis()) + ".jpg")
        val outputOption = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            outputOption,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val saveUri = Uri.fromFile(photoFile)
                    val intent = Intent(this@UpgradeAccountSelfieCameraActivity, UpgradeAccountSelfieResultActivity::class.java)
                    intent.putExtra("imgResultSelfie", "${saveUri}")
//                    intent.putExtra("imgResultSelfieBase64", Utils.encodeImage(saveUri.toString()))
                    intent.putExtra("imgResultIdentity", imgResultIdentity)
//                    intent.putExtra("imgResultIdentityBase64", imgResultIdentityBase64)
                    progressDialog.dismiss()
                    startActivity(intent)
                    this@UpgradeAccountSelfieCameraActivity.finish()
                }

                override fun onError(exception: ImageCaptureException) {
                    progressDialog.dismiss()
                    Toast.makeText(this@UpgradeAccountSelfieCameraActivity, exception.message, Toast.LENGTH_LONG)
                    Log.e(Constant.TAG, "onError: ${exception.message}", exception)
                }
            })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constant.REQUEST_CODE_PERMISSIONS) {
            if (allPermissionGranted()) {
                startCamera()
            } else {
                Toast.makeText(this, "Permission not granted by the user", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also { mPreview ->
                mPreview.setSurfaceProvider(cameraView.surfaceProvider)
            }
            imageCapture = ImageCapture.Builder().build()
            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch (e: Exception) {
                Log.d(Constant.TAG, "startCamera Fail", e)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun allPermissionGranted() = Constant.REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }
}