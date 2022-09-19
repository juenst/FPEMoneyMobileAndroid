package lib.finpay.sdk.model
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TokenModel(
    @Expose
    @SerializedName("statusCode")
    var statusCode: String?,

    @Expose
    @SerializedName("statusDesc")
    var statusDesc: String?,

    @Expose
    @SerializedName("tokenID")
    var tokenID: String?,

    @Expose
    @SerializedName("tokenExpiry")
    var tokenExpiry: String?,
) : Parcelable