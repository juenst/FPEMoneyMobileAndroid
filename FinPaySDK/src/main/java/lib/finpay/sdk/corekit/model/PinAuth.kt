package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class PinAuth {
    @SerializedName("statusCode")
    var statusCode: String? = null
    @SerializedName("statusDesc")
    var statusDesc: String? = null
    @SerializedName("widgetURL")
    var widgetURL: String? = null
    @SerializedName("pinToken")
    var pinToken: String? = null
    @SerializedName("expiryDtime")
    var expiryDtime: String? = null
    @SerializedName("processingTime")
    var processingTime: Double? = null
}