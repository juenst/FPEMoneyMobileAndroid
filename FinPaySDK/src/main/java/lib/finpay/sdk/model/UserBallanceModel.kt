package lib.finpay.sdk.model
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class UserBallanceModel(
    @Expose
    @SerializedName("statusCode")
    var statusCode: String?,

    @Expose
    @SerializedName("statusDesc")
    var statusDesc: String?,

    @Expose
    @SerializedName("validSIgnature")
    var validSIgnature: String?,


    @Expose
    @SerializedName("custName")
    var custName: String?,

    @Expose
    @SerializedName("custType")
    var custType: String?,

    @Expose
    @SerializedName("custBalance")
    var custBalance: String?,

    @Expose
    @SerializedName("listCard")
    var listCard: String?,

    @Expose
    @SerializedName("tokenization")
    var tokenization: String?
) : Parcelable