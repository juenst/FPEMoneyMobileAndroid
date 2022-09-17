package lib.finpay.sdk.helper

//import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.binary.Hex
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
//import org.apache.commons.codec.binary.Hex


class SignatureHelper {
    @RequiresApi(Build.VERSION_CODES.O)
    fun createSignature(): String {
        val sorted = param().toList().sortedBy { (key, _) -> key}.toMap()
        val joinedSorted =sorted.values.joinToString("").uppercase()
        val key = "daYumnMb"
        val signature = createSignatures(joinedSorted, key)
        print(joinedSorted)
        print(signature)
        return signature;
    }

    fun param(): Map<String, Any> {
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun createSignatures(data: String, key: String): String {
        val sha256Hmac = Mac.getInstance("HmacSHA256")
        val secretKey = SecretKeySpec(key.toByteArray(), "HmacSHA256")
        sha256Hmac.init(secretKey)

//        return Hex.encodeHexString(sha256Hmac.doFinal(data.toByteArray()))

        // For base64
         return Base64.getEncoder().encodeToString(sha256Hmac.doFinal(data.toByteArray()))
    }
}