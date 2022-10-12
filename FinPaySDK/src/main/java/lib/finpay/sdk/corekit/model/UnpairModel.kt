package lib.finpay.sdk.corekit.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UnpairModel {
    @SerializedName("statusCode")
    private var statusCode: String? = null

    @SerializedName("statusDesc")
    private var statusDesc: String? = null

    @SerializedName("unpairStatus")
    private var unpairStatus: String? = null

    fun UnpairModel(
        statusCode: String?,
        statusDesc: String?,
        widgetURL: String?,
    ) {
        this.statusCode = statusCode
        this.statusDesc = statusDesc
        this.unpairStatus = unpairStatus
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

    fun getUnpairStatus(): String? {
        return unpairStatus
    }

    fun setUnpairStatusL(unpairStatus: String?) {
        this.unpairStatus = unpairStatus
    }
}