package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class TransferOtherInquiry {
    @SerializedName("statusCode")
    var statusCode: String? = null
    @SerializedName("statusDesc")
    var statusDesc: String? = null
    @SerializedName("billname")
    var billname: String? = null
    @SerializedName("processingTime")
    var processingTime: Double? = null
}