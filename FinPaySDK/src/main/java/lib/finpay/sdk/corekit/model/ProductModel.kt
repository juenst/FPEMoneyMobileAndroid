package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class ProductModel {
    @SerializedName("statusCode")
    private var statusCode: String? = null

    @SerializedName("statusDesc")
    private var statusDesc: String? = null

    @SerializedName("data")
    private var dataProduct: MutableList<DetailProductModel>? = null

    fun ProductModel(
        statusCode: String?,
        statusDesc: String?,
        dataProduct: MutableList<DetailProductModel>
    ){
        this.statusCode = statusCode
        this.statusDesc = statusDesc
        this.dataProduct = dataProduct
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

    fun getDataProduct(): MutableList<DetailProductModel>? {
        return dataProduct
    }

    fun setDataProduct(dataProduct: MutableList<DetailProductModel>?) {
        this.dataProduct = dataProduct
    }
}