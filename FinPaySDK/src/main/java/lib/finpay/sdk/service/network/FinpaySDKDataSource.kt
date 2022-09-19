package lib.finpay.sdk.service.network

class FinpaySDKDataSource constructor(private val api: FinpaySDKApi){
    private  suspend fun getToken(request: HashMap<String, String>) : String{
        val response = api.getTokenAsync(request).await()
        if(response.isSuccessful){
            if(response.body()?.statusCode == "000"){
                val tokenId = response.body()!!.tokenID
                return tokenId!!
            }
        }
        println("statusCode != 200")
        println(response.body()?.statusDesc)
        return ""
    }
}