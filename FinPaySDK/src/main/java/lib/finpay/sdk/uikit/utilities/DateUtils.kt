package lib.finpay.sdk.uikit.utilities

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    fun getStandardTime(): String? {
        val dateFormat = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
        return dateFormat.format(Date())
    }

    fun getStandardTime(date: Date?): String? {
        val dateFormat = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
        return dateFormat.format(date)
    }

    fun getStandardTimeShort(): String? {
        val dateFormat = SimpleDateFormat("dd-MMM-yyyy")
        return dateFormat.format(Date())
    }

    fun getCurrentTime(): String? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return dateFormat.format(Date())
    }

    fun getCurrentTimeShort(): String? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        return dateFormat.format(Date())
    }
}