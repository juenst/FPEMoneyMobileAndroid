package lib.finpay.sdk.service.network

import io.reactivex.Single
import lib.finpay.sdk.model.*
import retrofit2.Call
import retrofit2.http.*


interface Api {
    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun getToken(
        @Body body: HashMap<String, String>
    ): Call<TokenModel>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun reqActivation(
        @Body body: HashMap<String, String>
    ): Call<ReqActivationModel>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun reqConfirmation(
        @Body body: HashMap<String, String>
    ): Call<ReqConfirmationModel>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun getHistoryTransaction(
        @Body body: HashMap<String, String>
    ): Call<HistoryTransactionModel>

    @Headers("Content-Type:application/json")
    @POST("content/produk/")
    fun getListProduct(
        @Body body: HashMap<String, String>
    ): Call<ProductModel>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun getHistoryMasterTransaction(
        @Body body: HashMap<String, String>
    ): Call<HistoryTransactionModel>

    @Headers("Content-Type:application/json")
    @POST("api.php")
    fun getUserBallance(
        @Body body: HashMap<String, String>
    ): Call<UserBallanceModel>

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
    @POST("api.php")
    fun upgradeAccount(
        @Body body: HashMap<String, String>
    ): Call<UpgradeAccountModel>

}
