package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class GetFee {
    @SerializedName("statusCode")
    var statusCode: String? = null
    @SerializedName("statusDesc")
    var statusDesc: String? = null
    @SerializedName("data")
    var data: ArrayList<DataFee> = arrayListOf()
}

class DataFee {
    @SerializedName("sof")
    var sof: String? = null
    @SerializedName("fee")
    var fee: Int? = null
    @SerializedName("total")
    var total: Int? = null
}