package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class Product {
    @SerializedName("statusCode")
    var statusCode: String? = null

    @SerializedName("statusDesc")
    var statusDesc: String? = null

    @SerializedName("data")
    var dataProduct: MutableList<DetailProductModel>? = null
}

class DetailProductModel {
    @SerializedName("productCode")
    var productCode: String? = null

    @SerializedName("productDesc")
    var productDesc: String? = null
}