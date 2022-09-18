package lib.finpay.sdk.model
import com.google.gson.annotations.SerializedName

class UserBallanceModel {
    @SerializedName("statusCode")
    private var statusCode: String? = null

    @SerializedName("validSIgnature")
    private var validSIgnature: String? = null

    @SerializedName("statusDesc")
    private var statusDesc: String? = null

    @SerializedName("custName")
    private var custName: String? = null

    @SerializedName("custType")
    private var custType: String? = null

    @SerializedName("custBalance")
    private var custBalance: String? = null

    @SerializedName("listCard")
    private var listCard: String? = null

    @SerializedName("tokenization")
    private var tokenization: String? = null

    fun UserBallanceModel(
        statusCode: String?,
        validSIgnature: String?,
        statusDesc: String?,
        custName: String?,
        custBalance: String?,
        custType: String?,
        listCard: String?,
        tokenization: String?
    ) {
        this.statusCode = statusCode
        this.validSIgnature = validSIgnature
        this.statusDesc = statusDesc
        this.custName = custName
        this.custBalance = custBalance
        this.custType = custType
        this.listCard = listCard
        this.tokenization = tokenization
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

    fun getCustName(): String? {
        return custName
    }

    fun setCustName(custName: String?) {
        this.custName = custName
    }

    fun getCustBalance(): String? {
        return custBalance
    }

    fun setCustBalance(custBalance: String?) {
        this.custBalance = custBalance
    }

    fun getCustType(): String? {
        return custType
    }

    fun setCustType(custType: String) {
        this.custType = custType
    }

    fun getListCard(): String? {
        return listCard
    }

    fun setListCard(listCard: String?) {
        this.listCard = listCard
    }

    fun getTokenization(): String? {
        return tokenization
    }

    fun setTokenization(tokenization: String?) {
        this.tokenization = tokenization
    }

}