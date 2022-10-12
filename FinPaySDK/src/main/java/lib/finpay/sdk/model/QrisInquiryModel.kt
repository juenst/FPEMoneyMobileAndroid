package lib.finpay.sdk.model

import com.google.gson.annotations.SerializedName

class QrisInquiryModel {
    @SerializedName("statusCode")
    var statusCode: String? = null

    @SerializedName("statusDesc")
    var statusDesc: String? = null

    @SerializedName("fee")
    var fee: ArrayList<Fee> = arrayListOf()

    @SerializedName("conf")
    var conf: String? = null

    @SerializedName("billname")
    var billname: String? = null

    @SerializedName("bit61")
    var bit61: String? = null

    @SerializedName("bit61Parse")
    var bit61Parse: Bit61Parse? = Bit61Parse()

    @SerializedName("tagihan")
    var tagihan: Int? = null

    @SerializedName("processingTime")
    var processingTime: Double? = null
}

class Fee {
    @SerializedName("sof")
    var sof: String? = null

    @SerializedName("fee")
    var fee: Int? = null

    @SerializedName("total")
    var total: Int? = null
}

class Bit61Parse {
    @SerializedName("merchantName")
    var merchantName: String? = null
    @SerializedName("merchantId")
    var merchantId: String? = null
    @SerializedName("nevaNumber")
    var nevaNumber: String? = null
    @SerializedName("amount")
    var amount: String? = null
    @SerializedName("paymentCode")
    var paymentCode: String? = null
    @SerializedName("pointOfMethod")
    var pointOfMethod: String? = null
    @SerializedName("tipsType")
    var tipsType: String? = null
    @SerializedName("tipsAmount")
    var tipsAmount: String? = null
    @SerializedName("tipsPercentage")
    var tipsPercentage: String? = null
    @SerializedName("acquirerName")
    var acquirerName: String? = null
    @SerializedName("merchantLocation")
    var merchantLocation: String? = null
    @SerializedName("merchantPAN")
    var merchantPAN: String? = null
    @SerializedName("terminalID")
    var terminalID: String? = null
    @SerializedName("isOnUs")
    var isOnUs: String? = null

}