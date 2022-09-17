package lib.finpay.sdk.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TokenModel {
    @SerializedName("statusCode")
    @Expose
    var statusCode: String? = null

    @SerializedName("validSIgnature")
    @Expose
    var validSIgnature: String? = null

    @SerializedName("statusDesc")
    @Expose
    var statusDesc: String? = null

    @SerializedName("tokenID")
    @Expose
    var tokenID: String? = null

    @SerializedName("tokenExpiry")
    @Expose
    var tokenExpiry: String? = null

    @SerializedName("processingTime")
    @Expose
    var processingTime: Double? = null
}