package lib.finpay.sdk.corekit.model
import com.google.gson.annotations.SerializedName

class UpgradeAccount {
    @SerializedName("statusCode")
    var statusCode: String? = null

    @SerializedName("statusDesc")
    var statusDesc: String? = null
}