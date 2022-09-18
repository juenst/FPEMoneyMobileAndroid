package lib.finpay.sdk.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserBallanceModel {
    @SerializedName("statusCode")
    @Expose
    var statusCode: String? = null

    @SerializedName("validSIgnature")
    @Expose
    var validSIgnature: String? = null

    @SerializedName("statusDesc")
    @Expose
    var statusDesc: String? = null

    @SerializedName("custName")
    @Expose
    var custName: String? = null

    @SerializedName("custBalance")
    @Expose
    var custBalance: String? = null

    @SerializedName("listCard")
    @Expose
    var listCard: String? = null

    @SerializedName("tokenization")
    @Expose
    var tokenization: String? = null
}