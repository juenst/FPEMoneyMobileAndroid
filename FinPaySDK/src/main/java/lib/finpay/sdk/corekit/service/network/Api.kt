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
    ): Call<Product>

    @Headers("Content-Type:application/json")
    @POST("getDenom/")
    fun getListSubProduct(
        @Body body: HashMap<String, Any>
    ): Call<SubProduct>

    @Headers("Content-Type:application/json")
    @POST("content/produk/{product_code}")
    fun getListOprProduct(
        @Body body: HashMap<String, String>
    ): Call<OprProduct>

    @Headers("Content-Type:application/json")
    @POST("getFee")
    fun getFee(
        @Body body: HashMap<String, String>
    ): Call<GetFee>

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
    ): Call<Transaction>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun mutasiBallance(
        @Body body: HashMap<String, String>
    ): Call<MutasiBallance>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun widgetProfile(
        @Body body: HashMap<String, String>
    ): Call<WidgetProfile>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun widgetTopUp(
        @Body body: HashMap<String, String>
    ): Call<WidgetTopUp>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun regisAccMerchant(
        @Body body: HashMap<String, String>
    ): Call<RegisAccMerchant>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun unpair(
        @Body body: HashMap<String, String>
    ): Call<Unpair>

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

    @Headers("Content-Type:application/json")
    @POST("security/pin/auth")
    fun authPin(
        @Body body: HashMap<String, String>
    ): Call<PinAuth>

    @Headers("Content-Type:application/json")
    @POST("transfer/sesama/inquiry")
    fun transferOtherInquiry(
        @Body body: HashMap<String, String>
    ): Call<TransferOtherInquiry>

    @Headers("Content-Type:application/json")
    @POST("transfer/bank/inquiry")
    fun transferBankInquiry(
        @Body body: HashMap<String, String>
    ): Call<TransferBankInquiry>

    @Headers("Content-Type:application/json")
    @POST("transfer/sesama/payment")
    fun transferOtherPayment(
        @Body body: HashMap<String, String>
    ): Call<TransferOtherPayment>

    @Headers("Content-Type:application/json")
    @POST("transfer/bank/payment")
    fun transferBankPayment(
        @Body body: HashMap<String, String>
    ): Call<TransferBankPayment>

    @Headers("Content-Type:application/json")
    @POST("content/bank")
    fun getListBank(
        @Body body: HashMap<String, String>
    ): Call<Bank>

    @Headers("Content-Type:application/json")
    @POST("security/pin/reset")
    fun resetPin(
        @Body body: HashMap<String, String>
    ): Call<PinReset>

    @Headers("Content-Type:application/json")
    @POST("security/pin/change/widget")
    fun changePin(
        @Body body: HashMap<String, String>
    ): Call<PinChange>

    @Headers("Content-Type:application/json")
    @POST("widget/api/topup")
    fun topup(
        @Body body: HashMap<String, String>
    ): Call<TopupInquiry>

    @Headers("Content-Type:application/json")
    @POST("checkProfile/")
    fun checkProfile(
        @Body body: HashMap<String, String>
    ): Call<Profile>
}
