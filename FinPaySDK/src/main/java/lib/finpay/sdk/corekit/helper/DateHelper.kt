package lib.finpay.sdk.corekit.helper

import java.text.SimpleDateFormat
import java.util.*
import java.util.Date

class DateHelper {
    companion object {
        fun getCurrentDate(): String {
            val sdf = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
            val currentDate = sdf.format(Date())

            return currentDate
        }

        fun getCurrentDateTransaction(): String {
            val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
            val currentDate = sdf.format(Date())

            return currentDate
        }
    }
}