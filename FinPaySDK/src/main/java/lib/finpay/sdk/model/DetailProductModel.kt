package lib.finpay.sdk.model

import com.google.gson.annotations.SerializedName

class DetailProductModel {
    @SerializedName("productCode")
    private var productCode: String? = null

    @SerializedName("productDesc")
    private var productDesc: String? = null

    fun ProductModel(
        productCode: String?,
        productDesc: String?,
    ){
        this.productDesc = productDesc
        this.productCode = productCode
    }

    fun getProductCode(): String? {
        return productCode
    }

    fun setProductCode(productCode: String?) {
        this.productCode = productCode
    }

    fun getProductDesc(): String? {
        return productDesc
    }

    fun setProductDesc(productDesc: String?) {
        this.productDesc = productDesc
    }
}