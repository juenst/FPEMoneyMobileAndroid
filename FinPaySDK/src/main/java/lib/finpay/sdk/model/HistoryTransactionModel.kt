package lib.finpay.sdk.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import java.util.HashMap

class HistoryTransactionModel {
    @SerializedName("statusCode")
    private var statusCode: String? = null

    @SerializedName("statusDesc")
    private var statusDesc: String? = null

    @SerializedName("detailHist")
    private var data: String? = null

    @SerializedName("0")
    var dataDetail: DetailHistoryTransactionModel? =null

    private var listData : MutableList<DetailHistoryTransactionModel>? = null

    fun HistoryTransactionModel(
        statusCode: String?,
        statusDesc: String?,
        data : String,
        detail: DetailHistoryTransactionModel,
        listData : MutableList<DetailHistoryTransactionModel>
    ){
        this.statusCode = statusCode
        this.statusDesc = statusDesc
        this.data = data
        this.listData = listData
        this.dataDetail = detail

    }

    fun getStatusCode(): String? {
        return statusCode
    }

    fun setStatusCode(statusCode: String?) {
        this.statusCode = statusCode
    }

    fun getStatusDesc(): String? {
        return statusDesc
    }

    fun setStatusDesc(statusDesc: String?) {
        this.statusDesc = statusDesc
    }

    fun getDataHistory(): String? {
        return data
    }

    fun setDataHistory(data: String?) {
        this.data = data
    }

    fun getListHistory(): MutableList<DetailHistoryTransactionModel>? {
        val listTemp :MutableList<DetailHistoryTransactionModel> = mutableListOf()
        if (this.data != null){
            val answer = JSONObject(this.data)
            for (i in 0..(answer.length()-1)){
                val gson = Gson()
                val dataDetail = gson.fromJson(JSONObject(answer.getJSONObject("$i").toString()).toString(), DetailHistoryTransactionModel::class.java)
                listTemp.add(dataDetail)
            }
        }
        listData = listTemp
        return listData
    }

    fun setListHistory(data:DetailHistoryTransactionModel) {
        this.listData!!.toMutableList().add(data)
    }

    fun getDetailDataHistory(): DetailHistoryTransactionModel? {
        return dataDetail
    }


}