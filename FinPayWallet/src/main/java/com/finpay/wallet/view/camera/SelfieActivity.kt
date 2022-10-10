package com.finpay.wallet.view.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.finpay.wallet.R
import com.finpay.wallet.databinding.ActivitySelfieBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class SelfieActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelfieBinding
    private var imageCapture: ImageCapture? = null
    private lateinit var outputDirectory: File

    companion object {
        const val EXTRA_RESULT = "extra_result_value"
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.getStringExtra(SelfieResultActivity.EXTRA_RESULT)?.let {
                val intent = Intent()
                intent.putExtra(EXTRA_RESULT, it)
                setResult(RESULT_OK, intent)
                finish()
            }
        } else if (result.resultCode == RESULT_CANCELED) {
            //TODO: RE USE CAMERA
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        outputDirectory = getOutputDirectory()
        binding = ActivitySelfieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        if (allPermissionGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, Constant.REQUIRED_PERMISSIONS,
                Constant.REQUEST_CODE_PERMISSIONS,
            )
        }
        binding.btnTakePic.setOnClickListener {
            takePic()
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
        val imageCapture = imageCapture ?: return
        val photoFile =
            File(
                outputDirectory,
                SimpleDateFormat(
                    Constant.FILE_NAME_FORMAT,
                    Locale.getDefault()
                ).format(System.currentTimeMillis()) + ".jpg"
            )
        val outputOption = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            outputOption,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val saveUri = Uri.fromFile(photoFile)
                    val intent = Intent(this@SelfieActivity, SelfieResultActivity::class.java)
                    intent.putExtra(SelfieResultActivity.EXTRA_DATA, "URI $saveUri")
                    intent.putExtra("imagesResult", photoFile)
                    resultLauncher.launch(intent)
                }

                override fun onError(exception: ImageCaptureException) {
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
                mPreview.setSurfaceProvider(binding.previewView.surfaceProvider)
            }
            imageCapture = ImageCapture.Builder().build()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch (e: Exception) {
                Log.d(Constant.TAG, "startCamera Fail", e)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun allPermissionGranted() =
        Constant.REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                baseContext, it
            ) == PackageManager.PERMISSION_GRANTED
        }

}


object Constant {
    const val TAG = "cameraX"
    const val FILE_NAME_FORMAT = "yy-MM-dd-HH-mm-ss-SSS"
    const val REQUEST_CODE_PERMISSIONS = 123
    val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
}