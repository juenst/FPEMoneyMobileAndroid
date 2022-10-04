package lib.finpay.sdk.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReqActivationModel {
    @SerializedName("statusCode")
    private var statusCode: String? = null

    @SerializedName("statusDesc")
    private var statusDesc: String? = null

    @SerializedName("custStatusCode")
    private var custStatusCode: String? = null

    @SerializedName("custStatusDesc")
    private var custStatusDesc: String? = null

    fun ReqActivationModel(
        statusCode: String?,
        statusDesc: String?,
        custStatusCode: String?,
        custStatusDesc: String?,
    ) {
        this.statusCode = statusCode
        this.statusDesc = statusDesc
        this.custStatusCode = custStatusCode
        this.custStatusDesc = custStatusDesc
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

    fun getCustStatusCode(): String? {
        return custStatusCode
    }

    fun setCustStatusCode(custStatusCode: String?) {
        this.custStatusCode = custStatusCode
    }

    fun getCustStatusDesc(): String? {
        return custStatusDesc
    }

    fun setCustStatusDesc(custStatusDesc: String?) {
        this.custStatusDesc = custStatusDesc
    }
}