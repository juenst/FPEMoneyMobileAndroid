package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class PpobInquiry {
    @SerializedName("statusCode")
    var statusCode: String? = null
    @SerializedName("statusDesc")
    var statusDesc: String? = null
    @SerializedName("fee")
    var fee: ArrayList<FeePpob> = arrayListOf()
    @SerializedName("conf")
    var conf: String? = null
    @SerializedName("billname")
    var billname: String? = null
    @SerializedName("bit61")
    var bit61: String? = null
    @SerializedName("bit61Parse")
    var bit61Parse: Bit61ParsePPob? = Bit61ParsePPob()
    @SerializedName("tagihan")
    var tagihan: Int? = null
    @SerializedName("traxId")
    var traxId: Int? = null
    @SerializedName("processingTime")
    var processingTime: Double? = null
}

class ListFeePbob{
    @SerializedName("statusCode")
    var statusCode: String? = null
    @SerializedName("statusDesc")
    var statusDesc: String? = null
    @SerializedName("data")
    var dataListFee: ArrayList<FeePpob> = arrayListOf()
}

class FeePpob {
    @SerializedName("sof")
    var sof: String? = null
    @SerializedName("fee")
    var fee: Int? = null
    @SerializedName("total")
    var total: Int? = null
}

class BillInfo1 {
    @SerializedName("nomorReferensi")
    var nomorReferensi: String? = null
    @SerializedName("nilaiTagihan")
    var nilaiTagihan: String? = null
}

class Bit61ParsePPob {
    @SerializedName("kodeDivre")
    var kodeDivre: String? = null
    @SerializedName("kodeDatel")
    var kodeDatel: String? = null
    @SerializedName("jumlahBill")
    var jumlahBill: String? = null
    @SerializedName("billInfo1")
    var billInfo1: BillInfo1? = BillInfo1()
    @SerializedName("customerId")
    var customerId: String? = null
    @SerializedName("customerName")
    var customerName: String? = null
    @SerializedName("npwp")
    var npwp: String? = null
}