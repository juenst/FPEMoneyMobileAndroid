package com.finpay.sdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import lib.finpay.sdk.helper.SignatureHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var getSignature = SignatureHelper().createSignature()
        print(getSignature)
    }
}