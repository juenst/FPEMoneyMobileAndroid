package com.finpay.wallet.view.profile.pin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.finpay.wallet.R
import com.finpay.wallet.view.AppActivity
import com.finpay.wallet.view.login.LoginActivity
import kotlinx.android.synthetic.main.activity_change_pin.*

class InputPinActivity : AppCompatActivity() {
    val pin = mutableListOf("", "", "", "", "", "", "")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_pin)

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
                    val intent = Intent(this, AppActivity::class.java)
                    startActivity(intent)
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