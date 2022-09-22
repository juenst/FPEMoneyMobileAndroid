package lib.finpay.sdk.model

import com.google.gson.annotations.SerializedName

class UpgradeAccountModel {
    @SerializedName("statusCode")
    private var statusCode: String? = null

    @SerializedName("statusDesc")
    private var statusDesc: String? = null

    @SerializedName("detailHist")
    private var data: String? = null

    fun UpgradeAccountModel(
        statusCode: String?,
        statusDesc: String?,
        data: String,
    ) {
        this.statusCode = statusCode
        this.statusDesc = statusDesc
        this.data = data

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