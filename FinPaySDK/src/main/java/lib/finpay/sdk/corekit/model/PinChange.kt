package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class PinChange {
    @SerializedName("statusCode")
    var statusCode: String? = null
    @SerializedName("statusDesc")
    var statusDesc: String? = null
    @SerializedName("widgetURL")
    var widgetURL: String? = null
    @SerializedName("processingTime")
    var processingTime: Double? = null
}