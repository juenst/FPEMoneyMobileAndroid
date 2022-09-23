package com.finpay.wallet.view.camera

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R
import com.google.android.material.button.MaterialButton

class CameraActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnCamera: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        btnCamera = findViewById(R.id.btn_goto_camera_result)
        btnCamera.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.btn_goto_camera_result){
            print("Go To Camera Screen With Guide Line")
        }
    }
}