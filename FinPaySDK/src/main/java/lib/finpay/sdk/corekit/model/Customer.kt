package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class Customer {
    @SerializedName("statusCode")
    var statusCode: String? = null

    @SerializedName("statusDesc")
    var statusDesc: String? = null

    @SerializedName("custStatusCode")
    var custStatusCode: String? = null

    @SerializedName("custStatusDesc")
    var custStatusDesc: String? = null
}