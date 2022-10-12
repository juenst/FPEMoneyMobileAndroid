package lib.finpay.sdk.corekit.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UpgradeAccountModel {
    @SerializedName("statusCode")
    private var statusCode: String? = null

    @SerializedName("statusDesc")
    private var statusDesc: String? = null

    fun UpgradeAccountModel(
        statusCode: String?,
        statusDesc: String?,
    ) {
        this.statusCode = statusCode
        this.statusDesc = statusDesc
    }

    fun getStatusCode(): String? {
        return statusCode
    }

    fun setStatusCode(statusCode: String?) {
        this.statusCode = statusCode
    }

    fun getStatusDesc(): String? {
        return statusDesc
    }

    fun setStatusDesc(statusDesc: String?) {
        this.statusDesc = statusDesc
    }
}