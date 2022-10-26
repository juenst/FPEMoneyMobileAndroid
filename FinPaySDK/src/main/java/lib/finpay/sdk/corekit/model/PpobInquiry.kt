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
    var traxId: String? = null
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
    @SerializedName("period")
    var period: String? = null
    @SerializedName("periodEndDate")
    var periodEndDate: String? = null
    @SerializedName("meterReadDate")
    var meterReadDate: String? = null
    @SerializedName("electricityBill")
    var electricityBill: String? = null
    @SerializedName("incentive")
    var incentive: String? = null
    @SerializedName("ppn")
    var ppn: String? = null
    @SerializedName("penalty")
    var penalty: String? = null
    @SerializedName("pastMeter")
    var pastMeter: String? = null
    @SerializedName("presentMeter")
    var presentMeter: String? = null
    @SerializedName("total")
    var total: String? = null
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
    @SerializedName("bill")
    var bill: String? = null
    @SerializedName("billRef")
    var billRef: String? = null
    @SerializedName("billAmount")
    var billAmount: String? = null
    @SerializedName("plnReferenceNumber")
    var plnRef : String? = null
    @SerializedName("swReferenceNumber")
    var swRef : String? = null
    @SerializedName("meterId")
    var meterId : String? = null
    @SerializedName("subscriberId")
    var subscriberId : String? = null
    @SerializedName("subscriberSegmen")
    var subscriberSegmen : String? = null
    @SerializedName("power")
    var power : String? = null
    @SerializedName("distCode")
    var distCode : Int? = null
    @SerializedName("upCode")
    var upCode : String? = null
    @SerializedName("upPhone")
    var upPhone : String? = null
    @SerializedName("kwhMaximum")
    var kwhMaximum : String? = null
    @SerializedName("electricityBill")
    var electricityBill : String? = null
    @SerializedName("electricityBillTotal")
    var electricityBillTotal : String? = null
    @SerializedName("feeTotal")
    var feeTotal : String? = null
    @SerializedName("unsold")
    var unsold : String? = null
    @SerializedName("unsold1")
    var unsold1 : String? = null
    @SerializedName("unsold2")
    var unsold2 : String? = null
}