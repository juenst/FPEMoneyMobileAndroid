package lib.finpay.sdk.helper

import java.text.SimpleDateFormat
import java.util.*

class SignatureHelper {
    public fun createSignature(): String {
        val sorted = param().toSortedMap()
        return sorted.toString();
    }

    public fun param(): Map<String, Any> {
        val sdf = SimpleDateFormat("yyyyMdhhmmss")
        val currentDate = sdf.format(Date())
        val mapJson = mapOf(
            "requestType" to "getToken",
            "phoneNumber" to "083815613839",
            "reqDtime" to currentDate,
            "transNumber" to currentDate
        )
        return mapJson;
    }
}