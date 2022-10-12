package lib.finpay.sdk.corekit.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TokenModel {
    @SerializedName("statusCode")
    private var statusCode: String? = null

    @SerializedName("statusDesc")
    private var statusDesc: String? = null

    @SerializedName("tokenID")
    private var tokenID: String? = null

    @SerializedName("tokenExpiry")
    private var tokenExpiry: String? = null

    fun TokenModel(
        statusCode: String?,
        statusDesc: String?,
        tokenID: String?,
        tokenExpiry: String?,
    ) {
        this.statusCode = statusCode
        this.statusDesc = statusDesc
        this.tokenID = tokenID
        this.tokenExpiry = tokenExpiry
    }

    fun getStatusCode(): String? {
        return statusCode
    }

    fun setStatusCode(statusCode: String?) {
        this.statusCode = statusCode
    }

    fun getStatusDesc(): String? {
        return statusDesc
    }

    fun setStatusDesc(statusDesc: String?) {
        this.statusDesc = statusDesc
    }

    fun getTokenID(): String? {
        return tokenID
    }

    fun setTokenID(tokenID: String?) {
        this.tokenID = tokenID
    }

    fun getTokenExpiry(): String? {
        return tokenExpiry
    }

    fun setTokenExpiry(tokenExpiry: String?) {
        this.tokenExpiry = tokenExpiry
    }
}