package lib.finpay.sdk.uikit.utilities

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.DisplayMetrics
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ln
import kotlin.math.pow

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

    fun Context.getJsonFromRaw(file: Int): String {
        return this.resources.openRawResource(file).bufferedReader().use {
            it.readText()
        }
    }

    fun encodeImage(path: String): String? {
        val imgFile = File(path.replace("file://", ""))
        if (imgFile.exists()) {
            val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            val byteArrayOutputStream = ByteArrayOutputStream()
            myBitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream)
            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
            val encoded = Base64.encodeToString(byteArray, Base64.DEFAULT)
            return encoded
        }
        return ""
    }

    fun getFileToByte(filePath: String?): String? {
        var bmp: Bitmap? = null
        var bos: ByteArrayOutputStream? = null
        var bt: ByteArray? = null
        var encodeString: String? = null
        var filePath = File(filePath!!.replace("file://", ""))
        try {
            bmp = BitmapFactory.decodeFile(filePath.toString())
            bos = ByteArrayOutputStream()
            bmp.compress(Bitmap.CompressFormat.JPEG, 50, bos)
            bt = bos.toByteArray()
            encodeString = Base64.encodeToString(bt, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return encodeString
    }

    fun validateMobileNumber(phoneNumber: String): String {
        val regex = Regex("[^0-9]")
       var number:String = regex.replace(phoneNumber, "")
        return number
    }

    fun getProviderMobile(phoneNumber: String): String {
        var provider: String = ""
        if(phoneNumber.substring(0,1) == "62") {
            phoneNumber.replace("62", "0")
        }
        if(
            phoneNumber.substring(0,4) == "0817" ||
            phoneNumber.substring(0,4) == "0818" ||
            phoneNumber.substring(0,4) == "0819" ||
            phoneNumber.substring(0,4) == "0859" ||
            phoneNumber.substring(0,4) == "0877" ||
            phoneNumber.substring(0,4) == "0878" ||
            phoneNumber.substring(0,4) == "0879"
        ) {
            provider = "XL"
        } else if(
            phoneNumber.substring(0,4) == "0811" ||
            phoneNumber.substring(0,4) == "0812" ||
            phoneNumber.substring(0,4) == "0813" ||
            phoneNumber.substring(0,4) == "0821" ||
            phoneNumber.substring(0,4) == "0822" ||
            phoneNumber.substring(0,4) == "0823" ||
            phoneNumber.substring(0,4) == "0851" ||
            phoneNumber.substring(0,4) == "0852" ||
            phoneNumber.substring(0,4) == "0853"
        ) {
            provider = "TELKOMSEL"
        } else if(
            phoneNumber.substring(0,4) == "0814" ||
            phoneNumber.substring(0,4) == "0815" ||
            phoneNumber.substring(0,4) == "0816" ||
            phoneNumber.substring(0,4) == "0855" ||
            phoneNumber.substring(0,4) == "0856" ||
            phoneNumber.substring(0,4) == "0857" ||
            phoneNumber.substring(0,4) == "0858"
        ) {
            provider = "INDOSAT"
        } else if(
            phoneNumber.substring(0,4) == "0831" ||
            phoneNumber.substring(0,4) == "0832" ||
            phoneNumber.substring(0,4) == "0833" ||
            phoneNumber.substring(0,4) == "0838"
        ) {
//            provider = "AXIS"
            provider = "XL"
        } else if (
            phoneNumber.substring(0,4) == "0895" ||
            phoneNumber.substring(0,4) == "0896" ||
            phoneNumber.substring(0,4) == "0897" ||
            phoneNumber.substring(0,4) == "0898" ||
            phoneNumber.substring(0,4) == "0899"
        ){
            provider = "THREE"
        } else if (
            phoneNumber.substring(0,4) == "0881" ||
            phoneNumber.substring(0,4) == "0882" ||
            phoneNumber.substring(0,4) == "0883" ||
            phoneNumber.substring(0,4) == "0884" ||
            phoneNumber.substring(0,4) == "0885" ||
            phoneNumber.substring(0,4) == "0886" ||
            phoneNumber.substring(0,4) == "0887" ||
            phoneNumber.substring(0,4) == "0888" ||
            phoneNumber.substring(0,4) == "0889"
        ){
            provider = "SMARTFREN"
        }else {
            provider = "UNKNOWN"
        }

        return provider
    }

    fun getFormatedNumber(count: Long): String {
        if (count < 1000) return "" + count
        val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
        val number: String = String.format("%.1f %c", count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
        val split = number.split(",")
        //return number
        return split[0].replace(".0","")
    }
}