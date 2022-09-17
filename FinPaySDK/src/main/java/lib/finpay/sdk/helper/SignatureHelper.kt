package com.example.testing

import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter

class SignatureHelper {
    fun createSignature(data: Map<String, Any>): String {
        val dataMapSorted = data.toList().sortedBy { (key, _) -> key }.toMap()
        val mapValue = dataMapSorted.values.joinToString("")
        val key = bin2hex("daYumnMb".toByteArray())
        return digest(mapValue, key).uppercase()
    }

    private fun digest(
        data: String,
        key: String,
        alg: String = "HmacSHA256",
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


    private fun bin2hex(byteArray: ByteArray): String {
        return DatatypeConverter.printHexBinary(byteArray);
    }

    private fun hex2bin(binary: String): ByteArray {
        return DatatypeConverter.parseHexBinary(binary)
    }
}