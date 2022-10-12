package lib.finpay.sdk.corekit.model
import com.google.gson.annotations.SerializedName

class Token {
    @SerializedName("statusCode")
    var statusCode: String? = null

    @SerializedName("statusDesc")
    var statusDesc: String? = null

    @SerializedName("tokenID")
    var tokenID: String? = null

    @SerializedName("tokenExpiry")
    var tokenExpiry: String? = null
}