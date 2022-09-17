package com.finpay.sdk

import com.example.testing.SignatureHelper
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var signature: SignatureHelper
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun checkSignature() {
        signature = SignatureHelper()
        val sdf = SimpleDateFormat("yyyyMdHHmmss")
        val currentDate = sdf.format(Date())
        val mapJson = mapOf(
            "requestType" to "getToken",
            "phoneNumber" to "083815613839",
            "reqDtime" to currentDate,
            "transNumber" to currentDate
        )
        val key = signature.createSignature(mapJson)
        println(key)
    }
}