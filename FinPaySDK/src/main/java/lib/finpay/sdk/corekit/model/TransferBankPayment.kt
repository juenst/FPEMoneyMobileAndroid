package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class TransferBankPayment {
    @SerializedName("statusCode")
    var statusCode: String? = null
    @SerializedName("statusDesc")
    var statusDesc: String? = null
    @SerializedName("syslogno")
    var syslogno: String? = null
    @SerializedName("fee")
    var fee: FeeBank? = FeeBank()
    @SerializedName("bit61Parse")
    var bit61Parse: Bit61ParseBank? = Bit61ParseBank()
    @SerializedName("pajak")
    var pajak: PajakBank? = PajakBank()
    @SerializedName("processingTime")
    var processingTime: Double? = null
}

class Bit61ParseBank {
    @SerializedName("bankAccName")
    var bankAccName: String? = null
    @SerializedName("bankName")
    var bankName: String? = null
    @SerializedName("desc")
    var desc: String? = null
    @SerializedName("category")
    var category: String? = null
    @SerializedName("phoneNumberDest")
    var phoneNumberDest: String? = null
    @SerializedName("bankAccNo")
    var bankAccNo: String? = null
}

class PajakBank {
    @SerializedName("amountDpp")
    var amountDpp: Int? = null
    @SerializedName("amountPpn")
    var amountPpn: Int? = null
    @SerializedName("ppn")
    var ppn: String? = null
}