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

class ProductPulsa {
    @SerializedName("productCode")
    var productCode: String? = null

    @SerializedName("productDesc")
    var productDesc: String? = null
}

class ProductAlfamart {
    @SerializedName("productCode")
    var productCode: String? = null

    @SerializedName("productDesc")
    var productDesc: String? = null
}

class ProductBestTelkomselPackage {
    @SerializedName("productCode")
    var productCode: String? = null

    @SerializedName("productDesc")
    var productDesc: String? = null
}

class ProductBpjs {
    @SerializedName("productCode")
    var productCode: String? = null

    @SerializedName("productDesc")
    var productDesc: String? = null
}

class ProductInstalment {
    @SerializedName("productCode")
    var productCode: String? = null

    @SerializedName("productDesc")
    var productDesc: String? = null
}

class ProductInsurance {
    @SerializedName("productCode")
    var productCode: String? = null

    @SerializedName("productDesc")
    var productDesc: String? = null
}

class ProductInternetTVCable {
    @SerializedName("productCode")
    var productCode: String? = null

    @SerializedName("productDesc")
    var productDesc: String? = null
}

class ProductPascaBayar {
    @SerializedName("productCode")
    var productCode: String? = null

    @SerializedName("productDesc")
    var productDesc: String? = null
}

class ProductPDAM {
    @SerializedName("productCode")
    var productCode: String? = null

    @SerializedName("productDesc")
    var productDesc: String? = null
}

class ProductPLN {
    @SerializedName("productCode")
    var productCode: String? = null

    @SerializedName("productDesc")
    var productDesc: String? = null
}

class ProductStateRevenue {
    @SerializedName("productCode")
    var productCode: String? = null

    @SerializedName("productDesc")
    var productDesc: String? = null
}

class ProductTelkom {
    @SerializedName("productCode")
    var productCode: String? = null

    @SerializedName("productDesc")
    var productDesc: String? = null
}

class ProductVoucher {
    @SerializedName("productCode")
    var productCode: String? = null

    @SerializedName("productDesc")
    var productDesc: String? = null
}