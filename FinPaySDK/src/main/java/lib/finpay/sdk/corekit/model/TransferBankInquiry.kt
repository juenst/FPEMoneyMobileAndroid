package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class TransferBankInquiry {
    @SerializedName("statusCode")
    var statusCode: String? = null
    @SerializedName("statusDesc")
    var statusDesc: String? = null
    @SerializedName("billname")
    var billname: String? = null
    @SerializedName("fee")
    var fee: ArrayList<FeeBank> = arrayListOf()
    @SerializedName("conf")
    var conf: String? = null
    @SerializedName("reffTrx")
    var reffTrx: String? = null
    @SerializedName("processingTime")
    var processingTime: Double? = null
}

class FeeBank {
    @SerializedName("sof")
    var sof: String? = null
    @SerializedName("fee")
    var fee: Int? = null
    @SerializedName("total")
    var total: Int? = null
}