package lib.finpay.sdk.corekit.model
import com.google.gson.annotations.SerializedName

class Unpair {
    @SerializedName("statusCode")
    var statusCode: String? = null

    @SerializedName("statusDesc")
    var statusDesc: String? = null

    @SerializedName("unpairStatus")
    var unpairStatus: String? = null
}