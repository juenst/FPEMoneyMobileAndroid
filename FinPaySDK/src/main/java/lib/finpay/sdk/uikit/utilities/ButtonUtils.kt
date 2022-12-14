package lib.finpay.sdk.uikit.utilities

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.Button
import lib.finpay.sdk.uikit.helper.FinpayTheme

class ButtonUtils {
    companion object {
        fun checkButtonState(button: Button, finpayTheme: FinpayTheme? = null){
            // Create a color state list programmatically
            val states = arrayOf(
                intArrayOf(android.R.attr.state_enabled), // enabled
                intArrayOf(-android.R.attr.state_enabled) // disabled
            )
            val bgColors = intArrayOf(
                if(finpayTheme?.getPrimaryColor() == null) {
                    Color.parseColor("#00ACBA") // enabled color
                } else {
                    finpayTheme.getPrimaryColor()!!
                },
                Color.parseColor("#d5d5d5") // disabled color
            )
            val textColors = intArrayOf(
                Color.parseColor("#ffffff"), // enabled color
                Color.parseColor("#a5a5a5")// disabled color
            )
            val bgColorStates = ColorStateList(states,bgColors)
            val textColorStates = ColorStateList(states,textColors)

            // Set button background tint
            button.backgroundTintList = bgColorStates
            button.setTextColor(textColorStates)
        }
    }
}