package lib.finpay.sdk.model
import com.google.gson.annotations.SerializedName


data class ApiResponse<T>(
    @SerializedName("statusCode")
    var statusCode: String,

    @SerializedName("statusDesc")
    var statusDesc: String,

    @SerializedName("data")
    val content: T
)