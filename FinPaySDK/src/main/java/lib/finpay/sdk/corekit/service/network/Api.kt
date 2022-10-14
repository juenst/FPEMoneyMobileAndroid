package lib.finpay.sdk.corekit.service.network

import lib.finpay.sdk.corekit.model.*
import retrofit2.Call
import retrofit2.http.*


interface Api {
    @Headers("Content-Type:application/json")
    @POST("security/getToken")
    fun getToken(
        @Body body: HashMap<String, String>
    ): Call<Token>

    @Headers("Content-Type:application/json")
    @POST("customer/activation")
    fun reqActivation(
        @Body body: HashMap<String, String>
    ): Call<Customer>

    @Headers("Content-Type:application/json")
    @POST("customer/confirmation")
    fun reqConfirmation(
        @Body body: HashMap<String, String>
    ): Call<Customer>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun getHistoryTransaction(
        @Body body: HashMap<String, String>
    ): Call<HistoryTransaction>

    @Headers("Content-Type:application/json")
    @POST("content/produk/")
    fun getListProduct(
        @Body body: HashMap<String, String>
    ): Call<ProductModel>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun getHistoryMasterTransaction(
        @Body body: HashMap<String, String>
    ): Call<HistoryTransaction>

    @Headers("Content-Type:application/json")
    @POST("checkProfile/cekSaldo")
    fun getUserBallance(
        @Body body: HashMap<String, String>
    ): Call<UserBalance>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun transaction(
        @Body body: HashMap<String, String>
    ): Call<TransactionModel>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun mutasiBallance(
        @Body body: HashMap<String, String>
    ): Call<MutasiBallanceModel>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun widgetProfile(
        @Body body: HashMap<String, String>
    ): Call<WidgetProfileModel>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun widgetTopUp(
        @Body body: HashMap<String, String>
    ): Call<WidgetTopUpModel>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun regisAccMerchant(
        @Body body: HashMap<String, String>
    ): Call<RegisAccMerchantModel>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun unpair(
        @Body body: HashMap<String, String>
    ): Call<UnpairModel>

    @Headers("Content-Type:application/json")
    @POST("upgradeAccount")
    fun upgradeAccount(
        @Body body: HashMap<String, String>
    ): Call<UpgradeAccount>

    @Headers("Content-Type:application/json")
    @POST("qris/Inquiry")
    fun qrisInquiry(
        @Body body: HashMap<String, String>
    ): Call<QrisInquiry>

    @Headers("Content-Type:application/json")
    @POST("qris/Payment")
    fun qrisPayment(
        @Body body: HashMap<String, String>
    ): Call<QrisPayment>

    @Headers("Content-Type:application/json")
    @POST("inqBill")
    fun ppobInquiry(
        @Body body: HashMap<String, String>
    ): Call<PpobInquiry>

    @Headers("Content-Type:application/json")
    @POST("paymentConf")
    fun ppobPayment(
        @Body body: HashMap<String, String>
    ): Call<PpobPayment>
}
