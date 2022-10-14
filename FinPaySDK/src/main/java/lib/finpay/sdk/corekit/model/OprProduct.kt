package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class OprProduct {
    @SerializedName("statusCode")
    var statusCode: String? = null
    @SerializedName("statusDesc")
    var statusDesc: String? = null
    @SerializedName("data")
    var dataSubProduct: ArrayList<GroupOpr> = arrayListOf()
}

class GroupOpr {
    @SerializedName("groupOpr")
    var denom: String? = null
}