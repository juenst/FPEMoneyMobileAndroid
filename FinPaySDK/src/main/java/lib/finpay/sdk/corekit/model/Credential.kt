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

    @SerializedName("phoneNumber")
    private var phoneNumber: String? = null

    @SerializedName("custName")
    private var custName: String? = null

    @SerializedName("otp")
    private var otp: String? = null

    fun Credential(
        username: String?,
        password: String?,
        secretKey: String?,
        phoneNumber: String?,
        custName: String?,
        otp: String?
    ) {
        this.username = username
        this.password = password
        this.secretKey = secretKey
        this.phoneNumber = phoneNumber
        this.custName = custName
        this.otp = otp
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

    fun getPhoneNumber(): String? {
        return phoneNumber
    }

    fun setPhoneNumber(phoneNumber: String?) {
        this.phoneNumber = phoneNumber
    }

    fun getCustName(): String? {
        return custName
    }

    fun setCustName(custName: String?) {
        this.custName = custName
    }

    fun getOtp(): String? {
        return otp
    }

    fun setOtp(otp: String?) {
        this.otp = otp
    }
}