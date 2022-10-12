package lib.finpay.sdk.corekit.model
import com.google.gson.annotations.SerializedName

class UserBalance {
    @SerializedName("statusCode")
    var statusCode: String? = null

    @SerializedName("validSIgnature")
    var validSIgnature: String? = null

    @SerializedName("statusDesc")
    var statusDesc: String? = null

    @SerializedName("custName")
    var custName: String? = null

    @SerializedName("custType")
    var custType: String? = null

    @SerializedName("custBalance")
    var custBalance: String? = null

    @SerializedName("amount")
    var amount: String? = null

    @SerializedName("listCard")
    var listCard: String? = null

    @SerializedName("tokenization")
    var tokenization: String? = null
}