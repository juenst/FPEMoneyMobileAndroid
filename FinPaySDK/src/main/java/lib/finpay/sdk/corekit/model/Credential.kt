package lib.finpay.sdk.corekit.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Credential {
    @SerializedName("username")
    private var username: String? = null

    @SerializedName("password")
    private var password: String? = null

    @SerializedName("secretKey")
    private var secretKey: String? = null

    fun Credential(
        username: String?,
        password: String?,
        secretKey: String?,
    ) {
        this.username = username
        this.password = password
        this.secretKey = secretKey
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun getSecretKey(): String? {
        return secretKey
    }

    fun setSecretKey(secretKey: String?) {
        this.secretKey = secretKey
    }
}