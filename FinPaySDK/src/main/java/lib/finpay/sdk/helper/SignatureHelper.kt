package com.example.testing

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter


class SignatureHelper {

    fun createSignature(): String {
        val sorted = param().toList().sortedBy { (key, _) -> key}.toMap()
        val joinedSorted =sorted.values.joinToString("")
        print(joinedSorted+""+sorted+"\n")
        val key = "daYumnMb"
        var key2 = bin2hex(key.toByteArray())
        print("key : $key2")
        val signature = digest(joinedSorted, key2)
        print("\n")
        print(signature.uppercase() + "\n")

        return signature.uppercase();
    }

    fun param(): Map<String, Any> {
        val sdf = SimpleDateFormat("yyyyMdHHmmss")
        val currentDate = sdf.format(Date())
//        08381561383920220917231022getToken20220917231022
        val mapJson = mapOf(
                "requestType" to "getToken",
                "phoneNumber" to "083815613839",
//            "reqDtime" to currentDate,
                "reqDtime" to "20220917231022",
                "transNumber" to "20220917231022"
        )
        return mapJson;
    }

    fun digest(
            data: String,
            key: String,
            alg: String = "HmacSHA256"
    ): String {
        val signingKey = SecretKeySpec(key.toByteArray(), alg)
        val mac = Mac.getInstance(alg)
        mac.init(signingKey)

        val bytes = mac.doFinal(data.toByteArray())
        return format(bytes)
    }

    private fun format(bytes: ByteArray): String {
        val formatter = Formatter()
        bytes.forEach { formatter.format("%02x", it) }
        return formatter.toString()
    }


    fun bin2hex(byteArray: ByteArray): String {
        return DatatypeConverter.printHexBinary(byteArray);
    }

    fun hex2bin(binary: String): ByteArray {
        return DatatypeConverter.parseHexBinary(binary)
    }

}