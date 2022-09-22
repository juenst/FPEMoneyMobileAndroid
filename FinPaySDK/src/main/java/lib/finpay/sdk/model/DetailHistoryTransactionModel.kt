package lib.finpay.sdk.model
import com.google.gson.annotations.SerializedName

class DetailHistoryTransactionModel {

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

    fun DetailHistoryTransactionModel(
        dateTime : String,
        type:String,
        desc:String,
        value:String,
        trxCode:String,
        id:String,
        sumIn:String,
        sumOut:String,
        sysLogno:String,
        channelId:String,
        source:String,
        destination:String,
        name:String,
        traxId:String,
        prodCode:String,
        billingNo:String,
        sofCode:String,
        sofId:String
    ){
        this.dateTime=dateTime;
        this.type = type;
        this.desc = desc;
        this.value = value;
        this.trxCode = trxCode;
        this.id = id;
        this.sumIn = sumIn;
        this.sumOut = sumOut;
        this.sysLogno = sysLogno;
        this.channelId = channelId;
        this.source = source;
        this.destination = destination;
        this.name = name;
        this.traxId = traxId;
        this.prodCode = prodCode;
        this.billingNo = billingNo;
        this.sofCode = sofCode;
        this.sofId = sofId;
    }

    fun getDateTime(): String? {
        return dateTime
    }

    fun setDateTime(dateTimes: String?) {
        this.dateTime = dateTimes
    }

    fun getType(): String? {
        return type
    }

    fun setTypes(types: String?) {
        this.type = types
    }

    fun getDesc(): String? {
        return desc
    }

    fun setDesc(descs: String?) {
        this.desc = descs
    }

    fun getValues(): String? {
        return value
    }

    fun setValues(values: String?) {
        this.value = values
    }

    fun getTrxCode(): String? {
        return trxCode
    }

    fun setTrxCode(trxCodes: String?) {
        this.trxCode = trxCodes
    }

    fun getIds(): String? {
        return id
    }

    fun setIds(ids: String?) {
        this.id = ids
    }

    fun getSumIn(): String? {
        return sumIn
    }

    fun setSumIn(sumIns: String?) {
        this.sumIn = sumIns
    }

    fun getSumOut(): String? {
        return sumOut
    }

    fun setSumOut(sumOuts: String?) {
        this.sumOut = sumOuts
    }

    fun getSysLogno(): String? {
        return sysLogno
    }

    fun setSysLognos(sysLognos: String?) {
        this.sysLogno = sysLognos
    }

    fun getChannelId(): String? {
        return channelId
    }

    fun setChannelIds(channelIds: String?) {
        this.channelId = channelIds
    }

    fun getSources(): String? {
        return source
    }

    fun setSources(sources: String?) {
        this.source = sources
    }

    fun getDestination(): String? {
        return destination
    }

    fun setDestination(destinations: String?) {
        this.destination = destinations
    }

    fun getNames(): String? {
        return name
    }

    fun setNames(names: String?) {
        this.name = names
    }

    fun getTraxId(): String? {
        return traxId
    }

    fun setTraxIds(traxIds: String?) {
        this.traxId = traxIds
    }

    fun getProdCode(): String? {
        return prodCode
    }

    fun setProdCode(prodCodes: String?) {
        this.prodCode = prodCodes
    }

    fun getBillingNo(): String? {
        return billingNo
    }

    fun setBillingNo(billingNos: String?) {
        this.billingNo = billingNos
    }

    fun getSofCode(): String? {
        return sofCode
    }

    fun setSofCode(sofCodes: String?) {
        this.sofCode = sofCodes
    }

    fun getSofId(): String? {
        return sofId
    }

    fun setSofIds(sofIds: String?) {
        this.sofId = sofIds
    }
}