package com.finpay.wallet.view.profile.pin

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.finpay.wallet.R
import kotlinx.android.synthetic.main.activity_change_pin.*
import java.util.*

class ChangePinActivity : AppCompatActivity() {
    val oldPin = mutableListOf("", "", "", "", "", "", "")
    val newPin = mutableListOf("", "", "", "", "", "", "")
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pin)

        val oldPinView = findViewById(R.id.oldPin) as LinearLayout
        val newPinView = findViewById(R.id.newPin) as LinearLayout

        val backButton = findViewById(R.id.backButton) as ImageView

        val firstPin = findViewById(R.id.firstPin) as TextView
        val secondPin = findViewById(R.id.secondPin) as TextView
        val thirdPin = findViewById(R.id.thirdPin) as TextView
        val fourthPin = findViewById(R.id.fourthPin) as TextView
        val fifthPin = findViewById(R.id.fifthPin) as TextView
        val sixthPin = findViewById(R.id.sixthPin) as TextView

        val pinButton0 = findViewById(R.id.pinButton0) as TextView
        val pinButton1 = findViewById(R.id.pinButton1) as TextView
        val pinButton2 = findViewById(R.id.pinButton2) as TextView
        val pinButton3 = findViewById(R.id.pinButton3) as TextView
        val pinButton4 = findViewById(R.id.pinButton4) as TextView
        val pinButton5 = findViewById(R.id.pinButton5) as TextView
        val pinButton6 = findViewById(R.id.pinButton6) as TextView
        val pinButton7 = findViewById(R.id.pinButton7) as TextView
        val pinButton8 = findViewById(R.id.pinButton8) as TextView
        val pinButton9 = findViewById(R.id.pinButton9) as TextView
        val pinButtonDel = findViewById(R.id.pinButtonDel) as TextView

        val firstPinNew = findViewById(R.id.firstPinNew) as TextView
        val secondPinNew = findViewById(R.id.secondPinNew) as TextView
        val thirdPinNew = findViewById(R.id.thirdPinNew) as TextView
        val fourthPinNew = findViewById(R.id.fourthPinNew) as TextView
        val fifthPinNew = findViewById(R.id.fifthPinNew) as TextView
        val sixthPinNew = findViewById(R.id.sixthPinNew) as TextView

        val confirmButton = findViewById(R.id.confirmButton) as Button

        val pins = listOf(pinButton0,pinButton1,pinButton2,pinButton3,pinButton4,pinButton5,pinButton6,pinButton7,pinButton8,pinButton9)
        val texts = listOf(firstPin, secondPin, thirdPin, fourthPin, fifthPin, sixthPin)
        val newTexts = listOf(firstPinNew, secondPinNew, thirdPinNew, fourthPinNew, fifthPinNew, sixthPinNew)
        for(i in 0..9){
            pins[i].setOnClickListener {
                if(oldPinView.visibility==View.VISIBLE){
                    onPinPressed(i.toString(), oldPin, texts)
                    if(oldPin[5]!=""){
                        oldPinView.visibility= View.GONE
                        newPinView.visibility= View.VISIBLE
                    }
                    Log.e("",oldPin.toString())
                }else{
                    onPinPressed(i.toString(), newPin, newTexts)
                    confirmButton.isEnabled = newPin[5]!=""
                    Log.e("",newPin.toString())
                }

            }
        }

        pinButtonDel.setOnClickListener {
            if(oldPinView.visibility==View.VISIBLE){
//                oldPin[oldPin.indexOfLast { it!="" }] = ""
//                text[0].text = if (array[0]!="") "•" else ""
//                text[1].text=if (array[1]!="") "•" else ""
//                text[2].text=if (array[2]!="") "•" else ""
//                text[3].text=if (array[3]!="") "•" else ""
//                text[4].text=if (array[4]!="") "•" else ""
//                text[5].text=if (array[5]!="") "•" else ""
                onDeleted(oldPin, texts)
            }else{
//                newPin[newPin.indexOfLast { it!="" }] = ""
                onDeleted(newPin, newTexts)
                confirmButton.isEnabled = newPin[5]!=""
            }

        }

        backButton.setOnClickListener {
            onBackPressed()
        }

//        pinButton0.setOnClickListener {
//            onPinPressed("0")
//            if(oldPin[5]!=""){
//                oldPinView.visibility= View.GONE
//                newPinView.visibility= View.VISIBLE
//            }
//        }
//        pinButton1.setOnClickListener {
//            onPinPressed("1")
//            if(oldPin[5]!=""){
//                oldPinView.visibility= View.GONE
//                newPinView.visibility= View.VISIBLE
//            }
//        }
//        pinButton2.setOnClickListener {
//            onPinPressed("2")
//            if(oldPin[5]!=""){
//                oldPinView.visibility= View.GONE
//                newPinView.visibility= View.VISIBLE
//            }
//        }
//        pinButton3.setOnClickListener {
//            onPinPressed("3")
//            if(oldPin[5]!=""){
//                oldPinView.visibility= View.GONE
//                newPinView.visibility= View.VISIBLE
//            }
//        }
//        pinButton4.setOnClickListener {
//            onPinPressed("4")
//            if(oldPin[5]!=""){
//                oldPinView.visibility= View.GONE
//                newPinView.visibility= View.VISIBLE
//            }
//        }
//        pinButton5.setOnClickListener {
//            onPinPressed("5")
//            if(oldPin[5]!=""){
//                oldPinView.visibility= View.GONE
//                newPinView.visibility= View.VISIBLE
//            }
//        }
//        pinButton6.setOnClickListener {
//            onPinPressed("6")
//            if(oldPin[5]!=""){
//                oldPinView.visibility= View.GONE
//                newPinView.visibility= View.VISIBLE
//            }
//        }
//        pinButton7.setOnClickListener {
//            onPinPressed("7")
//            if(oldPin[5]!=""){
//                oldPinView.visibility= View.GONE
//                newPinView.visibility= View.VISIBLE
//            }
//        }
//        pinButton8.setOnClickListener {
//            onPinPressed("8")
//            if(oldPin[5]!=""){
//                oldPinView.visibility= View.GONE
//                newPinView.visibility= View.VISIBLE
//            }
//        }
//        pinButton9.setOnClickListener {
//            onPinPressed("9")
//            if(oldPin[5]!=""){
//                oldPinView.visibility= View.GONE
//                newPinView.visibility= View.VISIBLE
//            }
//        }




    }

    fun onPinPressed(value:String, array:MutableList<String>, text:List<TextView>){
        if(array.last()==""){
            array[array.indexOfFirst { it=="" }] = value
            text[0].text = if (array[0]!="") "•" else ""
            text[1].text=if (array[1]!="") "•" else ""
            text[2].text=if (array[2]!="") "•" else ""
            text[3].text=if (array[3]!="") "•" else ""
            text[4].text=if (array[4]!="") "•" else ""
            text[5].text=if (array[5]!="") "•" else ""
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


}