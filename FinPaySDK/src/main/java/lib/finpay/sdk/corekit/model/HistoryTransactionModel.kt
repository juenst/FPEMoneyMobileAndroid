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
     var dateTime: String? = null

    @SerializedName("type")
     var type: String? = null

    @SerializedName("desc")
     var desc: String? = null

    @SerializedName("value")
     var value: String? = null

    @SerializedName("trxcode")
     var trxCode: String? = null

    @SerializedName("id")
     var id: String? = null

    @SerializedName("sum_in")
     var sumIn: String? = null

    @SerializedName("sum_out")
     var sumOut: String? = null

    @SerializedName("syslogno")
     var sysLogno: String? = null

    @SerializedName("channel_id")
     var channelId: String? = null

    @SerializedName("source")
     var source: String? = null

    @SerializedName("destination")
     var destination: String? = null

    @SerializedName("name")
     var name: String? = null

    @SerializedName("traxId")
     var traxId: String? = null

    @SerializedName("prodCode")
     var prodCode: String? = null

    @SerializedName("billingNo")
     var billingNo: String? = null

    @SerializedName("sofCode")
     var sofCode: String? = null

    @SerializedName("sofId")
     var sofId: String? = null
}