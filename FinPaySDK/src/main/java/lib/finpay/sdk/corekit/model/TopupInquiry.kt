package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class TopupInquiry {
    @SerializedName("statusCode")
    var statusCode: String? = null
    @SerializedName("statusDesc")
    var statusDesc: String? = null
    @SerializedName("payment_code")
    var paymentCode: String? = null
    @SerializedName("expiryDtime")
    var expiryDtime: String? = null
    @SerializedName("guidence")
    var guidence: Guidence? = Guidence()
    @SerializedName("processingTime")
    var processingTime: Double? = null
}

class Vabni {
    @SerializedName("atm")
    var atm: ArrayList<String> = arrayListOf()
    @SerializedName("mbanking")
    var mbanking: ArrayList<String> = arrayListOf()
    @SerializedName("ibanking")
    var ibanking: ArrayList<String> = arrayListOf()
}

class Guidence {
    @SerializedName("vabni")
    var vabni: Vabni? = Vabni()
}