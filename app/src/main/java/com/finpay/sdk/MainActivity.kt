package com.finpay.sdk

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.finpay.sdk.constant.Constant
import kotlinx.coroutines.*
import lib.finpay.sdk.FinPaySDK
import lib.finpay.sdk.helper.Signature

class MainActivity : AppCompatActivity() {
    private lateinit var signature: Signature
    private lateinit var tvTokenId: TextView
    private lateinit var finPaySDK: FinPaySDK
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    @OptIn(DelicateCoroutinesApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        finPaySDK = FinPaySDK()
        tvTokenId = findViewById(R.id.tokenId)

//        val tokenID = finPaySDK.getToken(
//            Constant().userName,
//            Constant().password,
//            Constant().secretKey,
//            "TRX1234567890"
//        )


//        GlobalScope.launch(Dispatchers.IO) {
//            val tokenID = async {
//                finPaySDK.getToken(
//                    Constant().userName,
//                    Constant().password,
//                    Constant().secretKey,
//                    "TRX1234567890"
//                )
//            }
//            tokenID.await()
//        }
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            onError("Exception handled: ${throwable.localizedMessage}")
        }
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                val tokenID = finPaySDK.getToken(
                    Constant().userName,
                    Constant().password,
                    Constant().secretKey,
                    "TRX1234567890"
                )

                withContext(Dispatchers.Main){

                }
            } catch (e: Exception) {
                println("$e")
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }
}