package com.finpay.wallet.view.camera

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.databinding.ActivityCameraResultBinding
import kotlinx.android.synthetic.main.activity_camera_result.*
import java.io.File


class CameraResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraResultBinding
    private val uriImage: String? by lazy {
        intent.getStringExtra(EXTRA_DATA)
    }


    companion object {
        const val EXTRA_RESULT = "extra_result_value"
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.imgResult

        val imgFile = File(uriImage!!.replace("URI file://", ""))
        if(imgFile.exists()) {
            val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            imgResult.setImageBitmap(myBitmap)
        }

        binding.btnNext.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_RESULT, uriImage)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        binding.btnRetry.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

    }
}