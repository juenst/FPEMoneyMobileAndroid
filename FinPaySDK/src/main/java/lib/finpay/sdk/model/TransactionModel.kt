package lib.finpay.sdk.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TransactionModel {
    @SerializedName("statusCode")
    private var statusCode: String? = null

    @SerializedName("statusDesc")
    private var statusDesc: String? = null

    @SerializedName("appCode")
    private var appCode: String? = null

    fun TransactionModel(
        statusCode: String?,
        statusDesc: String?,
        appCode: String?,
    ) {
        this.statusCode = statusCode
        this.statusDesc = statusDesc
        this.appCode = appCode
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

    fun getAppCode(): String? {
        return appCode
    }

    fun setAppCode(appCode: String?) {
        this.appCode = appCode
    }
}