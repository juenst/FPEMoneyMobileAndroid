package com.finpay.wallet.view.camera

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.databinding.ActivityCameraResultBinding

class CameraResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraResultBinding
    val uriImage: String? by lazy {
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

        binding.btnContinue.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_RESULT, uriImage)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        binding.btnCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}