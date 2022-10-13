package lib.finpay.sdk.corekit.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

class HistoryTransaction {
    @SerializedName("statusCode")
    var statusCode: String? = null

    @SerializedName("statusDesc")
    var statusDesc: String? = null

    @SerializedName("detailHist")
    var data: String? = null

    var listData : MutableList<DataHistoryTransaction>? = getListHistory()

    fun getListHistory(): MutableList<DataHistoryTransaction>? {
        val listTemp :MutableList<DataHistoryTransaction> = mutableListOf()
        if (this.data != null){
            val answer = JSONObject(this.data)
            for (i in 0..(answer.length()-1)){
                val gson = Gson()
                val dataDetail = gson.fromJson(JSONObject(answer.getJSONObject("$i").toString()).toString(), DataHistoryTransaction::class.java)
                listTemp.add(dataDetail)
            }
        }
        listData = listTemp
        return listData
    }
}

class DataHistoryTransaction {
    @SerializedName("tanggal")
    private var dateTime: String? = null

    @SerializedName("type")
    private var type: String? = null

    @SerializedName("desc")
    private var desc: String? = null

    @SerializedName("value")
    private var value: String? = null

    @SerializedName("trxcode")
    private var trxCode: String? = null

    @SerializedName("id")
    private var id: String? = null

    @SerializedName("sum_in")
    private var sumIn: String? = null

    @SerializedName("sum_out")
    private var sumOut: String? = null

    @SerializedName("syslogno")
    private var sysLogno: String? = null

    @SerializedName("channel_id")
    private var channelId: String? = null

    @SerializedName("source")
    private var source: String? = null

    @SerializedName("destination")
    private var destination: String? = null

    @SerializedName("name")
    private var name: String? = null

    @SerializedName("traxId")
    private var traxId: String? = null

    @SerializedName("prodCode")
    private var prodCode: String? = null

    @SerializedName("billingNo")
    private var billingNo: String? = null

    @SerializedName("sofCode")
    private var sofCode: String? = null

    @SerializedName("sofId")
    private var sofId: String? = null
}