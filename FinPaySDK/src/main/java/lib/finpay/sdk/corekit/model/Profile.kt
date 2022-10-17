package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class Profile {
    @SerializedName("statusCode")
    var statusCode: String? = null
    @SerializedName("statusDesc")
    var statusDesc: String? = null
    @SerializedName("data")
    var data: DataProfile? = DataProfile()
    @SerializedName("processingTime")
    var processingTime: Double? = null
}

 class History {
     @SerializedName("trxDTime")
     var trxDTime: String? = null
     @SerializedName("trxType")
     var trxType: String? = null
     @SerializedName("trxAction")
     var trxAction: String? = null
     @SerializedName("trxDesc")
     var trxDesc: String? = null
     @SerializedName("trxAmount")
     var trxAmount: String? = null
 }

class DataProfile {
    @SerializedName("balance")
    var balance: String? = null
    @SerializedName("nama")
    var nama: String? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("tipeCust")
    var tipeCust: String? = null
    @SerializedName("evaID")
    var evaID: String? = null
    @SerializedName("lastTrx")
    var lastTrx: String? = null
    @SerializedName("history")
    var history: ArrayList<History> = arrayListOf()
}