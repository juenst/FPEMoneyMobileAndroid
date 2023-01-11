package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class TopupInquiry : java.io.Serializable{
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

class Vabni  : java.io.Serializable {
    @SerializedName("atm")
    var atm: ArrayList<String> = arrayListOf()
    @SerializedName("mbanking")
    var mbanking: ArrayList<String> = arrayListOf()
    @SerializedName("ibanking")
    var ibanking: ArrayList<String> = arrayListOf()
}

class Vamandiri : java.io.Serializable {
    @SerializedName("atm")
    var atm: ArrayList<String> = arrayListOf()
    @SerializedName("mbanking")
    var mbanking: ArrayList<String> = arrayListOf()
    @SerializedName("ibanking")
    var ibanking: ArrayList<String> = arrayListOf()
}

class Briva  : java.io.Serializable {
    @SerializedName("atm")
    var atm: ArrayList<String> = arrayListOf()
    @SerializedName("mbanking")
    var mbanking: ArrayList<String> = arrayListOf()
    @SerializedName("ibanking")
    var ibanking: ArrayList<String> = arrayListOf()
}

class Vabca  : java.io.Serializable {
    @SerializedName("atm")
    var atm: ArrayList<String> = arrayListOf()
    @SerializedName("mbanking")
    var mbanking: ArrayList<String> = arrayListOf()
    @SerializedName("ibanking")
    var ibanking: ArrayList<String> = arrayListOf()
}

class Pos  : java.io.Serializable {
    @SerializedName("outlet")
    var outlet: ArrayList<String> = arrayListOf()
    @SerializedName("atm")
    var atm: ArrayList<String> = arrayListOf()
    @SerializedName("mbanking")
    var mbanking: ArrayList<String> = arrayListOf()
    @SerializedName("ibanking")
    var ibanking: ArrayList<String> = arrayListOf()
}

class Alfamart  : java.io.Serializable {
    @SerializedName("outlet")
    var outlet: ArrayList<String> = arrayListOf()
    @SerializedName("atm")
    var atm: ArrayList<String> = arrayListOf()
    @SerializedName("mbanking")
    var mbanking: ArrayList<String> = arrayListOf()
    @SerializedName("ibanking")
    var ibanking: ArrayList<String> = arrayListOf()
}

class Pegadaians  : java.io.Serializable {
    @SerializedName("outlet")
    var outlet: ArrayList<String> = arrayListOf()
    @SerializedName("atm")
    var atm: ArrayList<String> = arrayListOf()
    @SerializedName("mbanking")
    var mbanking: ArrayList<String> = arrayListOf()
    @SerializedName("ibanking")
    var ibanking: ArrayList<String> = arrayListOf()
}

class Guidence : java.io.Serializable  {
    @SerializedName("vabni")
    var vabni: Vabni? = Vabni()

    @SerializedName("pos")
    var post: Pos? = Pos()

    @SerializedName("alfa")
    var alfamarts: Alfamart? = Alfamart()

    @SerializedName("vamandiri")
    var vamandiris: Vamandiri? = Vamandiri()

    @SerializedName("briva")
    var brivas: Briva? = Briva()

    @SerializedName("vabca")
    var vabcas: Vabca? = Vabca()

    @SerializedName("pegadaian")
    var pegadaians: Pegadaians? = Pegadaians()

}