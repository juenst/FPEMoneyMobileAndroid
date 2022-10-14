package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class SubProduct {
    @SerializedName("statusCode")
    var statusCode: String? = null
    @SerializedName("statusDesc")
    var statusDesc: String? = null
    @SerializedName("data")
    var dataSubProduct: ArrayList<DataSubProduct> = arrayListOf()
}

class DataSubProduct {
    @SerializedName("denom")
    var denom: String? = null

    @SerializedName("provider")
    var provider: String? = null

    @SerializedName("info")
    var info: String? = null

    @SerializedName("subInfo1")
    var subInfo1: String? = null

    @SerializedName("subInfo2")
    var subInfo2: String? = null
}