package lib.finpay.sdk.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisAccMerchantModel {
    @SerializedName("statusCode")
    private var statusCode: String? = null

    @SerializedName("statusDesc")
    private var statusDesc: String? = null

    @SerializedName("transType")
    private var transType: String? = null

    fun RegisAccMerchantModel(
        statusCode: String?,
        statusDesc: String?,
        transType: String?,
    ) {
        this.statusCode = statusCode
        this.statusDesc = statusDesc
        this.transType = transType
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

    fun getTransType(): String? {
        return transType
    }

    fun setTransType(transType: String?) {
        this.transType = transType
    }
}