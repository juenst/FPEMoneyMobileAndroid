package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class Bank {
    @SerializedName("statusCode")
    var statusCode: String? = null
    @SerializedName("statusDesc")
    var statusDesc: String? = null
    @SerializedName("data")
    var data: ArrayList<DataBank> = arrayListOf()
    @SerializedName("processingTime")
    var processingTime: Double? = null
}

class DataBank {
    @SerializedName("kodeBank")
    var kodeBank: String? = null
    @SerializedName("Bank")
    var Bank: String? = null
}