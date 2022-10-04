package lib.finpay.sdk.helper

import lib.finpay.sdk.BuildConfig
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object StringFormat {

    fun String.encrypt(): String {
        val key = BuildConfig.KEY_AES  //128 bit key
        val initVector = BuildConfig.RANDOM_INIT_VECTOR // 16 bytes IV
        try {
            val iv = IvParameterSpec(initVector.toByteArray(charset("UTF-8")))
            val skeySpec = SecretKeySpec(key.toByteArray(charset("UTF-8")), "AES")

            val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)

            val encrypted = cipher.doFinal(this.toByteArray())
            return android.util.Base64.encodeToString(encrypted, 0).replace("\n", "")
        } catch (ex: Exception) {
            ex.printStackTrace()
        }


        return ""
    }

    fun String.decrypt(): String {
        val key = BuildConfig.KEY_AES  //128 bit key
        val initVector = BuildConfig.RANDOM_INIT_VECTOR // 16 bytes IV
        try {
            val iv = IvParameterSpec(initVector.toByteArray(charset("UTF-8")))
            val skeySpec = SecretKeySpec(key.toByteArray(charset("UTF-8")), "AES")

            val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv)

            val original = cipher.doFinal(android.util.Base64.decode(this, 0))

            return String(original)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }


        return ""
    }
}