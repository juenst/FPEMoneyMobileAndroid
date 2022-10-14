package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class PpobPayment {
    @SerializedName("statusCode")
    var statusCode: String? = null
    @SerializedName("statusDesc")
    var statusDesc: String? = null
    @SerializedName("bit61")
    var bit61: String? = null
    @SerializedName("pajak")
    var pajak: Pajak? = Pajak()
    @SerializedName("bit61Parse")
    var bit61Parse: Bit61ParsePaymentPpob? = Bit61ParsePaymentPpob()
    @SerializedName("syslogno")
    var syslogno: String? = null
    @SerializedName("processingTime")
    var processingTime: Double? = null
}

class Pajak {
    @SerializedName("amountDpp")
    var amountDpp: Int? = null
    @SerializedName("amountPpn")
    var amountPpn: Int? = null
    @SerializedName("ppn")
    var ppn: String? = null
}

class BillInfo1PaymentPpob {
    @SerializedName("nomorReferensi")
    var nomorReferensi: String? = null
    @SerializedName("nilaiTagihan")
    var nilaiTagihan: String? = null
}

class Bit61ParsePaymentPpob {
    @SerializedName("kodeDivre")
    var kodeDivre: String? = null
    @SerializedName("kodeDatel")
    var kodeDatel: String? = null
    @SerializedName("jumlahBill")
    var jumlahBill: String? = null
    @SerializedName("billInfo1")
    var billInfo1: BillInfo1PaymentPpob? = BillInfo1PaymentPpob()
    @SerializedName("customerId")
    var customerId: String? = null
    @SerializedName("customerName")
    var customerName: String? = null
    @SerializedName("npwp")
    var npwp: String? = null
}