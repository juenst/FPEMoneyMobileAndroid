//package com.finpay.wallet.utilities
//
//import android.annotation.SuppressLint
//import android.app.Activity
//import android.content.Context
//import android.database.Cursor
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.graphics.Matrix
//import android.media.ExifInterface
//import android.net.Uri
//import android.provider.MediaStore
//import android.util.Log
////import com.finpay.wallet.BuildConfig
//import com.finpay.wallet.R
//import org.json.JSONObject
//import java.io.InputStream
//import java.text.DateFormat
//import java.text.DecimalFormat
//import java.text.DecimalFormatSymbols
//import java.text.SimpleDateFormat
//import java.util.*
//import java.util.regex.Pattern
//import javax.crypto.Cipher
//import javax.crypto.spec.IvParameterSpec
//import javax.crypto.spec.SecretKeySpec
//
//
//object StringFormat {
//    fun stringCheck(string: String?, replacement: String) : String {
//        if(string.isNullOrEmpty()) return replacement;
//        return string;
//    }
//
//    fun formatCurrencyIDR(money: Long): String {
//        if (money == 0L) {
////            return "0,00"
//            return "0"
//        }
//        val symbols = DecimalFormatSymbols()
//        symbols.groupingSeparator = '.'
//        symbols.decimalSeparator = ','
////        val decimalFormat = DecimalFormat("#,###.00", symbols)
//        val decimalFormat = DecimalFormat("#,###", symbols)
//        val returnData = decimalFormat.format(money)
//        return returnData
//    }
//
//    fun convertMMYYYY2YYYYMM(dateString: String): String {
//        var result = ""
//        val df = SimpleDateFormat("MM/yyyy")
//        val df2 = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
//        val sdf = SimpleDateFormat("yyyy-MM")
//        if(dateString.isNotEmpty())
//        {
//            try {
//                val date = df.parse(dateString)
//                result = sdf.format(date)
//            }
//            catch (e: Exception)
//            {
//                try {
//                    val date = df2.parse(dateString)
//                    result = sdf.format(date)
//                }
//                catch (f: java.lang.Exception)
//                {
//                    result = dateString
//                }
//            }
//
//        }
//
//        return result
//    }
//
//    fun formatCurrencyNumber(moneyString: String): String {
//        val money = if (moneyString.isBlank()) 0L else moneyString.toLong()
//        if (money == 0L) {
//            return "0"
//        }
//        val symbols = DecimalFormatSymbols()
//        symbols.groupingSeparator = '.'
//        symbols.decimalSeparator = ','
//        val decimalFormat = DecimalFormat("#,###", symbols)
//        val returnData = "${decimalFormat.format(money)},-"
//        return returnData
//    }
//
//    fun formatCurrencyNumber2Point(moneyString: String): String {
//        val money = if (moneyString.isBlank()) 0L else moneyString.toLong()
//        if (money == 0L) {
//            return "0,00"
//        }
//        val symbols = DecimalFormatSymbols()
//        symbols.groupingSeparator = '.'
//        symbols.decimalSeparator = ','
//        val decimalFormat = DecimalFormat("#,###.##", symbols)
//        val decimal = moneyString.toDouble()
//
//        val returnData = "${if(decimalFormat.format(decimal).contains(",")) decimalFormat.format(
//            decimal
//        ) else "${decimalFormat.format(decimal)},00"}"
//        return returnData
//    }
//
//    fun isAlphanumeric(source: String): Boolean {
//        return source.matches("[a-zA-Z0-9]*".toRegex())
//    }
//
//    fun getDateMMYYYY(dateString: Date): String {
//        val sdf = SimpleDateFormat("MM/yyyy")
//        return sdf.format(dateString)
//
//    }
//    //"Jan","Feb","Mar","Apr","Mei","Jun","Jul","Agu","Sep","Okt","Nov","Des"
//    private fun mothInIDfMMM(month: Int): String {
//        when (month) {
//            1 -> return "Jan"
//            2 -> return "Feb"
//            3 -> return "Mar"
//            4 -> return "Apr"
//            5 -> return "Mei"
//            6 -> return "Jun"
//            7 -> return "Jul"
//            8 -> return "Agu"
//            9 -> return "Sep"
//            10 -> return "Okt"
//            11 -> return "Nov"
//            12 -> return "Des"
//            else -> return month.toString()
//        }
//    }
//
//    private fun mothInID(month: Int): String {
//        when (month) {
//            1 -> return "Januari"
//            2 -> return "Februari"
//            3 -> return "Maret"
//            4 -> return "April"
//            5 -> return "Mei"
//            6 -> return "Juni"
//            7 -> return "Juli"
//            8 -> return "Agustus"
//            9 -> return "September"
//            10 -> return "Oktober"
//            11 -> return "November"
//            12 -> return "Desember"
//            else -> return month.toString()
//        }
//    }
//
//    fun getLocalDateFormatFromLaravel(dateString: String, language: String) : String
//    {
//        var result = ""
//        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
//        val df2: DateFormat = SimpleDateFormat("yyyy-MM-dd")
//        val sdf: DateFormat = SimpleDateFormat("d MMMM yyyy")
//        try {
//            var resultDate = Date()
//            if(dateString.length<11)
//            {
//                resultDate= df2.parse(dateString)
//            }
//            else
//            {
//                resultDate= df2.parse(dateString)
//            }
//            if(language.equals("id", true))
//            {
//                val cal = Calendar.getInstance()
//                cal.time = resultDate
//                val date = cal.get(Calendar.DAY_OF_MONTH)
//                val month = cal.get(Calendar.MONTH) +1
//                val year = cal.get(Calendar.YEAR)
//
//                result = "$date ${StringFormat.mothInID(month)} $year"
//            }
//            else
//            {
//                result = sdf.format(resultDate)
//            }
//        } catch (e: java.lang.Exception) {
//            result = dateString
//        }
//        return result
//    }
//
//    fun getMonthYearFromLaravel(dateString: String, language: String) : String
//    {
//        var result = ""
//        val dfLaravel: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
//        val dfMMYYYY: DateFormat = SimpleDateFormat("MM/yyyy")
//        val sdf: DateFormat = SimpleDateFormat("MMMM yyyy")
//        try {
//            val resultDate = dfLaravel.parse(dateString)
//
//            if(language.equals("id", true))
//            {
//                val cal = Calendar.getInstance()
//                cal.time = resultDate
//                val month = cal.get(Calendar.MONTH) +1
//                val year = cal.get(Calendar.YEAR)
//
//                result = "${mothInIDfMMM(month)} $year"
//            }
//            else
//            {
//                result = sdf.format(resultDate)
//            }
//        } catch (e: java.lang.Exception) {
//            try {
//                val resultDate = dfMMYYYY.parse(dateString)
//
//                if(language.equals("id", true))
//                {
//                    val cal = Calendar.getInstance()
//                    cal.time = resultDate
//                    val month = cal.get(Calendar.MONTH) +1
//                    val year = cal.get(Calendar.YEAR)
//
//                    result = "${mothInIDfMMM(month)} $year"
//                }
//                else
//                {
//                    result = sdf.format(resultDate)
//                }
//            }
//            catch (f: java.lang.Exception) {
//                result = dateString
//            }
//        }
//        return result
//    }
//
//    fun getLocalDate(dateString: String) : String
//    {
//        var result = ""
//        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
//        val sdf: DateFormat = SimpleDateFormat("yyyy-MM-dd")
//        try {
//            val resultDate: Date = df.parse(dateString)
//            result = sdf.format(resultDate)
//        } catch (e: java.lang.Exception) {
//            result = dateString
//        }
//        return result
//    }
//
//    fun getValueFromCurrencyText(value: String): String {
//        val result = value.replace(ConstValue.CURRENCY, "").replace(" ", "").replace(",", "")
//        if(result.isBlank())
////            return "0"
//            return ""
//        return result
//    }
//
//    fun dateFormatMMMMYYYY(dateInput: String?): String? {
//        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
//        val sdf: DateFormat = SimpleDateFormat("MMMM yyyy")
//        var dateResult = ""
//        try {
//            val result: Date = df.parse(dateInput)
//            dateResult = sdf.format(result)
//        } catch (e: java.lang.Exception) {
//        }
//        return dateResult
//    }
//
//    fun dateFormatMMMMYYYY3(dateInput: String?): String? {
//        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
//        val sdf: DateFormat = SimpleDateFormat("yyyy-MM")
//        var dateResult = ""
//        try {
//            val result: Date = df.parse(dateInput)
//            dateResult = sdf.format(result)
//        } catch (e: java.lang.Exception) {
//        }
//        return dateResult
//    }
//
//    fun dateFormatMMYYYY(dateInput: String): String {
//        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
//        val sdf: DateFormat = SimpleDateFormat("MM/yyyy")
//        var dateResult = ""
//        try {
//            val result: Date = df.parse(dateInput)
//            dateResult = sdf.format(result)
//        } catch (e: java.lang.Exception) {
//            dateResult = dateInput
//        }
//        return dateResult
//    }
//
//    fun dateFormatMMYYYYWithoutT(dateInput: String): String {
//        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
//        val sdf: DateFormat = SimpleDateFormat("MM/yyyy")
//        var dateResult = ""
//        try {
//            val result: Date = df.parse(dateInput)
//            dateResult = sdf.format(result)
//        } catch (e: java.lang.Exception) {
//            dateResult = dateInput
//        }
//        return dateResult
//    }
//
//    fun dateFormatMMMMYYYY2(dateInput: String): String {
//        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
//        val sdf: DateFormat = SimpleDateFormat("MMMM yyyy")
//        var dateResult = ""
//        try {
//            val result: Date = df.parse(dateInput)
//            dateResult = sdf.format(result)
//        } catch (e: java.lang.Exception) {
//            dateResult=dateInput
//        }
//        return dateResult
//    }
//
//
//    fun dayMont(dateInput: String?): String? {
//        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
//        val sdf: DateFormat = SimpleDateFormat("d MMM yyyy")
//        var dateResult = ""
//        try {
//            val result: Date = df.parse(dateInput)
//            dateResult = sdf.format(result)
//        } catch (e: java.lang.Exception) {
//        }
//        return dateResult
//    }
//
//    fun getSelisihWaktu(dateInput: String): String {
//        var result = ""
//
//        if (dateInput != "") {
//            val format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss zzzz")
//
//            val cal1 = Calendar.getInstance()
//            val cal2 = Calendar.getInstance()
//            cal1.time = format.parse(dateInput)
//            cal1.set(Calendar.MONTH, cal1.get(Calendar.MONTH) + 1)
//            cal2.set(Calendar.MONTH, cal2.get(Calendar.MONTH) + 1)
//            val milliseconds = cal2.timeInMillis - cal1.timeInMillis
//
//            val seconds = (milliseconds / 1000) % 60
//            val minutes = (milliseconds / (1000 * 60) % 60)
//            val hours = (milliseconds / (1000 * 60 * 60) % 24)
//            val days = (milliseconds / (1000 * 60 * 60 * 24)).toInt()
//
//            if (days > 0) {
//                result = dayMont(dateInput)!!
//                return result
//            }
//
//            if (hours in 1..24) {
//                result = "${hours} h"
//                return result
//            }
//
//            if (minutes in 1..60) {
//                result = "${minutes} m"
//                return result
//            }
//            if(seconds in 0..60){
//                result = "$seconds s"
//                return result
//            }
//        }
//        return result
//    }
//
//
//    fun dateFormatDdMmYyyy(dateInput: String?): String? {
//        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
//        val sdf: DateFormat = SimpleDateFormat("dd/MM/yyyy")
//        var dateResult = ""
//        try {
//            val result: Date = df.parse(dateInput)
//            dateResult = sdf.format(result)
//        } catch (e: java.lang.Exception) {
//        }
//        return dateResult
//    }
//
//    fun dateFormatHhMm(dateInput: String?): String? {
//        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
//        val sdf: DateFormat = SimpleDateFormat("HH:mm")
//        var dateResult = ""
//        try {
//            val result: Date = df.parse(dateInput)
//            dateResult = sdf.format(result)
//        } catch (e: java.lang.Exception) {
//        }
//        return dateResult
//    }
//
//    fun dateFormatLaravel(dateInput: String): String {
//        val df: DateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")
//        val sdf: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//        var dateResult = ""
//        try {
//            val result: Date = df.parse(dateInput)
//            dateResult = sdf.format(result)
//        } catch (e: java.lang.Exception) {
//        }
//        return dateResult
//    }
//
//    fun dateFormatLaravelToAmPmYotani(dateInput: String?): String? {
//        val sdf = SimpleDateFormat(ConstValue.JOB_VACANCY_SHOW_TIME)
//        val sdfLaravel = SimpleDateFormat(ConstValue.PARSE_DATE_LARAVEL)
//        var dateResult = dateInput
//        try {
//            val result: Date = sdfLaravel.parse(dateInput)
//            dateResult = sdf.format(result)
//        } catch (e: java.lang.Exception) {
//        }
//        return dateResult
//
//    }
//
//    fun dateToString(dateInput: Date?): String? {
//        var date = ""
//        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
//        try {
//            date = df.format(dateInput)
//        } catch (e: java.lang.Exception) {
//        }
//        return date
//    }
//
//    @SuppressLint("Range")
//    fun getPhotoFromCameraUri(
//        activity: Activity,
//        context: Context,
//        uri: Uri
//    ): String {
//        var stringResult = ""
//
//        var options = BitmapFactory.Options()
//        options.inSampleSize = 4
//        var filePath = arrayOf(MediaStore.Images.Media.DATA)
//        var cursor: Cursor = context!!.contentResolver.query(
//            uri,
//            filePath,
//            null,
//            null,
//            null
//        )!!
//        cursor.moveToFirst()
//
//        var mImagePath: String = cursor.getString(cursor.getColumnIndex(filePath[0]))
//
//        var stream: InputStream =
//            context!!.contentResolver.openInputStream(uri)!!
//
//        var yourSelectedImage: Bitmap =
//            BitmapFactory.decodeStream(stream, null, options)!!
//        stream.close()
//
//        //orientation
//        try {
//            var rotate = 0
//            try {
//                var exif = ExifInterface(mImagePath)
//                var orientation = exif.getAttributeInt(
//                    ExifInterface.TAG_ORIENTATION,
//                    ExifInterface.ORIENTATION_NORMAL
//                );
//
//                when (orientation) {
//                    ExifInterface.ORIENTATION_ROTATE_270 ->
//                        rotate = 270
//                    ExifInterface.ORIENTATION_ROTATE_180 ->
//                        rotate = 180
//                    ExifInterface.ORIENTATION_ROTATE_90 ->
//                        rotate = 90
//
//                }
//            } catch (e: java.lang.Exception) {
//                e.printStackTrace()
//            }
//            var matrix = Matrix()
//            matrix.postRotate(rotate.toFloat())
//            yourSelectedImage = Bitmap.createBitmap(
//                yourSelectedImage,
//                0,
//                0,
//                yourSelectedImage.width,
//                yourSelectedImage.height,
//                matrix,
//                true
//            )
////            Glide.with(context!!)
////                .load(yourSelectedImage)
////                .apply(RequestOptions().override(600, 150))
////                .into(imageView)
//            val limageUrii = getRealPathFromURI(uri, activity)
//            stringResult = limageUrii
//        } catch (e: java.lang.Exception) {
//        }
//
//        return stringResult
//    }
//
//    fun getPhotoFromGallery(
//        activity: Activity,
//        uri: Uri
//    ): String {
//
//
//        val limageUrii = getRealPathFromURI(uri, activity)
//        return limageUrii
//    }
//
//
//    fun isContainNummber(source: String): Boolean{
//        val pettern = Pattern.compile("^(?=.*[0-9]).{5,}$")
//        return pettern.matcher(source).matches()
//    }
//
//    fun getRealPathFromURI(uri: Uri, activity: Activity): String {
//        val proj = MediaStore.Images.Media.DATA
//        val cursor: Cursor = activity!!.managedQuery(uri, arrayOf(proj), null, null, null)
//        val column_index: Int = cursor
//            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//        cursor.moveToFirst()
//        return cursor.getString(column_index)
//    }
//
//    fun formatStringDate(date: String, formatFrom: String, formatTo: String): String {
//        val sdfFrom = SimpleDateFormat(formatFrom)
//        val sdfTo = SimpleDateFormat(formatTo)
//        val date = sdfFrom.parse(date)
//        return sdfTo.format(date)
//    }
//}
//
//
//fun JSONObject.optStringIfNullReturnEmpty(key: String): String? {
//    return if (this.isNull(key)) "" else this.optString(key)
//}
//
////fun JSONArray.optArrayIfNullReturnEmpty(key: JSONArray): JSONArray? {
////    return if (this.isNull(key)) "" else this.optString(key)
////}
//
//fun String.encrypt(): String {
////    val key = BuildConfig.KEY_AES  //128 bit key
////    val initVector = BuildConfig.RANDOM_INIT_VECTOR // 16 bytes IV
////    try {
////        val iv = IvParameterSpec(initVector.toByteArray(charset("UTF-8")))
////        val skeySpec = SecretKeySpec(key.toByteArray(charset("UTF-8")), "AES")
////
////        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
////        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)
////
////        val encrypted = cipher.doFinal(this.toByteArray())
////        return android.util.Base64.encodeToString(encrypted, 0).replace("\n", "")
////    } catch (ex: Exception) {
////        ex.printStackTrace()
////    }
//
//
//    return ""
//}
//
//
//fun String.decrypt(): String {
////    val key = BuildConfig.KEY_AES  //128 bit key
////    val initVector = BuildConfig.RANDOM_INIT_VECTOR // 16 bytes IV
////    try {
////        val iv = IvParameterSpec(initVector.toByteArray(charset("UTF-8")))
////        val skeySpec = SecretKeySpec(key.toByteArray(charset("UTF-8")), "AES")
////
////        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
////        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv)
////
////        val original = cipher.doFinal(android.util.Base64.decode(this, 0))
////
////        return String(original)
////    } catch (ex: Exception) {
////        ex.printStackTrace()
////    }
//
//
//    return ""
//}
//
//fun String.authorization(): HashMap<String, String> {
//    val authHeader = java.util.HashMap<String, String>()
//    authHeader["Authorization"] = "Bearer $this"
//    return authHeader
//}
//
//fun String.isAlphanumericV2():Boolean{
//    val pattern =
//        //Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$")
//        Pattern.compile("^(?=.*[0-9])(?=.*[a-z]).{3,}$")
//    return pattern.matcher(this).matches()
//}
//
//fun String.removeBase64DataString() : String {
//    val base64Pattern = "data:image/jpeg;base64,"
//    return this.substring(base64Pattern.length)
//}