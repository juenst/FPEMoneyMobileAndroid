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
    var listData : MutableList<DetailProductModel>? = null
}

class ProductAlfamart {
    var listData : MutableList<DetailProductModel>? = null
}

class ProductBestTelkomselPackage {
    var listData : MutableList<DetailProductModel>? = null
}

class ProductBpjs {
    var listData : MutableList<DetailProductModel>? = null
}

class ProductInstalment {
    var listData : MutableList<DetailProductModel>? = null
}

class ProductInsurance {
    var listData : MutableList<DetailProductModel>? = null
}

class ProductInternetTVCable {
    var listData : MutableList<DetailProductModel>? = null
}

class ProductPascaBayar {
    var listData : MutableList<DetailProductModel>? = null
}

class ProductPDAM {
    var listData : MutableList<DetailProductModel>? = null
}

class ProductPLN {
    var listData : MutableList<DetailProductModel>? = null
}

class ProductStateRevenue {
    var listData : MutableList<DetailProductModel>? = null
}

class ProductTelkom {
    var listData : MutableList<DetailProductModel>? = null
}

class ProductVoucher {
    var listData : MutableList<DetailProductModel>? = null
}