package lib.finpay.sdk.model
//
import com.google.gson.annotations.SerializedName
data class TokenModel(

    @SerializedName("statusCode")
    val statusCode: String?,

    @SerializedName("statusDesc")
    val statusDesc: String?,

    @SerializedName("tokenID")
    val tokenID: String?,

    @SerializedName("tokenExpiry")
    val tokenExpiry: String?,
)