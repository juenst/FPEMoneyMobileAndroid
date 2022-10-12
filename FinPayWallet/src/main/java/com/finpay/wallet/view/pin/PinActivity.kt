package com.finpay.wallet.view.pin

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.finpay.wallet.R
import com.finpay.wallet.view.AppActivity
import com.finpay.wallet.view.transaction.TransactionDetailActivity
import lib.finpay.sdk.FinPaySDK

class PinActivity : AppCompatActivity() {
    val pinType: String? by lazy {
        intent.getStringExtra("pinType")
    }
    lateinit var progressDialog: ProgressDialog

    val pin = mutableListOf("", "", "", "", "", "", "")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_pin)

        progressDialog = ProgressDialog(this@PinActivity)
        val backButton = findViewById<ImageView>(R.id.backButton)

        val firstPin = findViewById(R.id.firstPinInput) as TextView
        val secondPin = findViewById(R.id.secondPinInput) as TextView
        val thirdPin = findViewById(R.id.thirdPinInput) as TextView
        val fourthPin = findViewById(R.id.fourthPinInput) as TextView
        val fifthPin = findViewById(R.id.fifthPinInput) as TextView
        val sixthPin = findViewById(R.id.sixthPinInput) as TextView

        val pinButton0 = findViewById(R.id.pinButton0Input) as TextView
        val pinButton1 = findViewById(R.id.pinButton1Input) as TextView
        val pinButton2 = findViewById(R.id.pinButton2Input) as TextView
        val pinButton3 = findViewById(R.id.pinButton3Input) as TextView
        val pinButton4 = findViewById(R.id.pinButton4Input) as TextView
        val pinButton5 = findViewById(R.id.pinButton5Input) as TextView
        val pinButton6 = findViewById(R.id.pinButton6Input) as TextView
        val pinButton7 = findViewById(R.id.pinButton7Input) as TextView
        val pinButton8 = findViewById(R.id.pinButton8Input) as TextView
        val pinButton9 = findViewById(R.id.pinButton9Input) as TextView
        val pinButtonDel = findViewById(R.id.pinButtonDelInput) as ImageView

        val texts = listOf(firstPin, secondPin, thirdPin, fourthPin, fifthPin, sixthPin)
        val pins = listOf(pinButton0,pinButton1,pinButton2,pinButton3,pinButton4,pinButton5,pinButton6,pinButton7,pinButton8,pinButton9)

        for(i in 0..9){
            pins[i].setOnClickListener {
                onPinPressed(i.toString(), pin, texts)
                if(pin[5]!=""){
                    if(pinType == "login") {
                        val intent = Intent(this, AppActivity::class.java)
                        startActivity(intent)
                    } else if(pinType == "paymentQris") {
                        val sof: String? by lazy {
                            intent.getStringExtra("sof")
                        }
                        val amount: String? by lazy {
                            intent.getStringExtra("amount")
                        }
                        val amountTips: String? by lazy {
                            intent.getStringExtra("amountTips")
                        }
                        val reffFlag: String? by lazy {
                            intent.getStringExtra("reffFlag")
                        }
                        progressDialog.setTitle("Mohon Menunggu")
                        progressDialog.setMessage("Sedang Memuat ...")
                        progressDialog.setCancelable(false) // blocks UI interaction
                        progressDialog.show()
                        FinPaySDK().qrisPayment(
                            "083815613839",
                            sof!!,
                            amount!!,
                            amountTips!!,
                            reffFlag!!,
                            pin[0]+pin[1]+pin[2]+pin[3]+pin[4]+pin[5], {
                               progressDialog.dismiss()
                                val intent = Intent(this@PinActivity, TransactionDetailActivity::class.java)
                                startActivity(intent)

                            }, {
                                progressDialog.dismiss()
                                println(it)
                                Toast.makeText(this@PinActivity, it, Toast.LENGTH_LONG)
                            }
                        )
                    }
                }
                Log.e("",pin.toString())
            }
        }

        pinButtonDel.setOnClickListener {
            onDeleted(pin, texts)

        }

        backButton.setOnClickListener{
            onBackPressed()
        }
    }

    fun onDeleted(array:MutableList<String>, text:List<TextView>){
        if(array[0]!=""){
            array[array.indexOfLast { it!="" }] = ""
            text[0].text = if (array[0]!="") "•" else ""
            text[1].text=if (array[1]!="") "•" else ""
            text[2].text=if (array[2]!="") "•" else ""
            text[3].text=if (array[3]!="") "•" else ""
            text[4].text=if (array[4]!="") "•" else ""
            text[5].text=if (array[5]!="") "•" else ""
        }
    }

    fun onPinPressed(value:String, array:MutableList<String>, text:List<TextView>){
        if(array[5]==""){
            array[array.indexOfFirst { it=="" }] = value
            text[0].text = if (array[0]!="") "•" else ""
            text[1].text=if (array[1]!="") "•" else ""
            text[2].text=if (array[2]!="") "•" else ""
            text[3].text=if (array[3]!="") "•" else ""
            text[4].text=if (array[4]!="") "•" else ""
            text[5].text=if (array[5]!="") "•" else ""
        }
    }
}