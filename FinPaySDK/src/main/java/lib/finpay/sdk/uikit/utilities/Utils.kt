package lib.finpay.sdk.uikit.utilities

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    const val CARD_TYPE_VISA = "VISA"
    const val CARD_TYPE_MASTERCARD = "MASTERCARD"
    const val CARD_TYPE_AMEX = "AMEX"
    const val CARD_TYPE_JCB = "JCB"
    private const val SECOND = 1000L
    private const val MINUTE = 60000L
    private const val HOUR = 3600000L
    private const val DAY = 86400000L

//    fun isNetworkAvailable(var0: Context): Boolean {
//        return try {
//            val var1 = var0.getSystemService("connectivity") as ConnectivityManager
//            var1.activeNetworkInfo != null && var1.activeNetworkInfo!!.isAvailable && var1.activeNetworkInfo!!
//                .isConnected
//        } catch (var2: Exception) {
//            Logger.e(var2.message)
//            false
//        }
//    }

//    fun hideKeyboard(var0: Context, var1: View?) {
//        try {
//            val var2 = var0.getSystemService("") as InputMethodManager
//            if (var1 != null) {
//                var2.hideSoftInputFromWindow(var1.windowToken, 0)
//                var1.clearFocus()
//            }
//        } catch (var3: Exception) {
//            Logger.e(var3.message)
//        }
//    }

    fun getFormattedAmount(var0: Double): String {
        return try {
            val var2 =
                DecimalFormatSymbols(Locale.US)
            var2.decimalSeparator = '.'
            var2.groupingSeparator = ','
            DecimalFormat("#,###.##", var2).format(var0)
        } catch (var4: IllegalArgumentException) {
            "" + var0
        } catch (var4: NullPointerException) {
            "" + var0
        }
    }

    fun getValidityTime(var0: String?): String? {
        if (var0 != null) {
            val var1 = var0.split(" ".toRegex()).toTypedArray()
            if (var1.size > 1) {
                try {
                    val var2 = SimpleDateFormat("yyyy-MM-dd")
                    val var3 = Calendar.getInstance()
                    var3.time = var2.parse(var1[0])
                    var3.add(5, 1)
                    val var4 = var2.format(var3.time)
                    val var5 = var4.split("-".toRegex()).toTypedArray()
                    val var6 = Utils.getMonth(var5[1].toInt())
                    val var7 = "" + var5[2] + " " + var6 + " " + var5[0] + ", " + var1[1]
//                    Logger.i("after parsing validity date becomes : $var4")
//                    Logger.i("month is : $var6")
//                    Logger.i("validity time is : $var7")
                    return var7
                } catch (var8: ParseException) {
//                    Logger.e("Error while parsing date : " + var8.message)
                }
            }
        }
        return var0
    }

    fun getMonth(var0: Int): String {
        return when (var0) {
            1 -> "January"
            2 -> "February"
            3 -> "March"
            4 -> "April"
            5 -> "May"
            6 -> "June"
            7 -> "July"
            8 -> "August"
            9 -> "September"
            10 -> "October"
            11 -> "November"
            12 -> "December"
            else -> "Invalid Month"
        }
    }

    fun dpToPx(var0: Int): Int {
        return (var0.toFloat() * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun getFormattedCreditCardNumber(var0: String): String {
        val var1 = StringBuilder()
        if (var0.length == 16) {
            var var2 = 0
            while (var2 < 16) {
                var1.append(var0.substring(var2, var2 + 4))
                var1.append(" ")
                var2 += 4
            }
        }
        return var1.toString()
    }

    fun getCardType(var0: String): String {
        return try {
            if (var0.isEmpty()) {
                ""
            } else if (var0[0] == '4') {
                "VISA"
            } else if (var0[0] == '5' && (var0[1] == '1' || var0[1] == '2' || var0[1] == '3' || var0[1] == '4' || var0[1] == '5')) {
                "MASTERCARD"
            } else if (var0[0] != '3' || var0[1] != '4' && var0[1] != '7') {
                if (!var0.startsWith("35") && !var0.startsWith("2131") && !var0.startsWith("1800")) "" else "JCB"
            } else {
                "AMEX"
            }
        } catch (var2: RuntimeException) {
            ""
        }
    }

    fun getFormattedTime(var0: Long): String {
        val var2 =
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z")
        var2.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
        return var2.format(Date(var0))
    }

    fun formatDouble(var0: Double): String {
        var var2 = "0"
        try {
            var2 = if (var0 == var0.toLong().toDouble()) String.format(
                "%d",
                var0.toLong()
            ) else String.format("%s", var0)
        } catch (var4: RuntimeException) {
//            Logger.e("formatDouble():" + var4.message)
        }
        return var2
    }

    fun getDeviceType(var0: Activity): String {
        val var2 = DisplayMetrics()
        var0.windowManager.defaultDisplay.getMetrics(var2)
        val var3 = var2.heightPixels.toFloat() / var2.ydpi
        val var4 = var2.widthPixels.toFloat() / var2.xdpi
        val var5 = Math.sqrt((var4 * var4 + var3 * var3).toDouble())
        val var1: String
        var1 = if (var5 >= 6.5) {
            "TABLET"
        } else {
            "PHONE"
        }
        return var1
    }
}