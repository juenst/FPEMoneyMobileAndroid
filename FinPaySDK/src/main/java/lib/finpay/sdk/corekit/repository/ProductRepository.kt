package lib.finpay.sdk.corekit.repository

import lib.finpay.sdk.corekit.helper.Signature
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.helper.DateHelper
import lib.finpay.sdk.corekit.helper.TransactionHelper
import lib.finpay.sdk.corekit.model.OprProduct
import lib.finpay.sdk.corekit.model.Product
import lib.finpay.sdk.corekit.model.SubProduct
import lib.finpay.sdk.corekit.service.BaseServices
import lib.finpay.sdk.corekit.service.network.Api
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import lib.finpay.sdk.uikit.utilities.extension.toJson
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ProductRepository()  {

    companion object{
        var tokenID: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.TOKEN_ID)!!
        var phoneNumber: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)!!
        var userName: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_USERNAME)!!
        var password: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_PASSWORD)!!
        var secretKey: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_SECRET_KEY)!!

        fun getListProduct(
            transNumber: String,
            onSuccess: (Product) -> Unit,
            onFailed: (String) -> Unit
        ){
            //create signature
var transactionNumber = TransactionHelper.getTransNumber(transNumber)
            val mapJson = mapOf(
                "requestType" to "getProduk",
                "reqDtime" to DateHelper.getCurrentDate(),
                "transNumber" to transactionNumber
            )
            FinpaySDK.signature = Signature()
            val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)

            //auth header
            val credential = Credentials.basic(userName, password)
            var header : HashMap<String, String> = hashMapOf()
            header["Authorization"] = credential

            //request body
            val requestBody : HashMap<String, String> = hashMapOf()
            requestBody["requestType"] = "getProduk"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = DateHelper.getCurrentDate()
            requestBody["transNumber"] = transactionNumber

            val request = BaseServices.getRetrofitInstanceCoBrand().create(Api::class.java)
            request.getListProduct(requestBody).enqueue(object :
                Callback<Product> {
                override fun onFailure(call: Call<Product>, t: Throwable) {
                    onFailed(t.message.toString())
                }
                override fun onResponse(
                    call: Call<Product>,
                    response: Response<Product>
                ) {
                    if (response.code() == 200) {
                        if (response.body()?.statusCode == "000") {
                            onSuccess(response.body()!!)
                        } else {
                            onFailed(response.body()?.statusDesc.toString())
                        }
                    } else {
                        onFailed(Constant.defaultErrorMessage)
                    }
                }
            })
        }

        fun getListSubProduct(
            transNumber: String,
            phoneNumber: String,
            listOpr: ArrayList<String>,
            onSuccess: (SubProduct) -> Unit,
            onFailed: (String) -> Unit
        ){
            //create signature
var transactionNumber = TransactionHelper.getTransNumber(transNumber)
            val mapJson = mapOf(
                "requestType" to "getDenom",
                "reqDtime" to DateHelper.getCurrentDate(),
                "transNumber" to transactionNumber,
                "tokenID" to tokenID,
                "phoneNumber" to phoneNumber,
                "opr" to listOpr.toJson()
            )
            println("masuk sini")
            println(mapJson)
            FinpaySDK.signature = Signature()
            val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)

            //auth header
            val credential = Credentials.basic(userName, password)
            var header : HashMap<String, String> = hashMapOf()
            header["Authorization"] = credential

            //request body
            val requestBody : HashMap<String, Any> = hashMapOf()
            requestBody["requestType"] = "getDenom"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = DateHelper.getCurrentDate()
            requestBody["transNumber"] = transactionNumber
            requestBody["phoneNumber"] = phoneNumber
            requestBody["tokenID"] = tokenID
            requestBody["opr"] = listOpr

            val request = BaseServices.getRetrofitInstanceCoBrand().create(Api::class.java)
            request.getListSubProduct(requestBody).enqueue(object :
                Callback<SubProduct> {
                override fun onFailure(call: Call<SubProduct>, t: Throwable) {
                    onFailed(t.message.toString())
                }
                override fun onResponse(
                    call: Call<SubProduct>,
                    response: Response<SubProduct>
                ) {
                    if (response.code() == 200) {
                        if (response.body()?.statusCode == "000") {
                            onSuccess(response.body()!!)
                        } else {
                            onFailed(response.body()?.statusDesc.toString())
                        }
                    } else {
                       onFailed(Constant.defaultErrorMessage)
                    }
                }
            })
        }

        fun getListOprProduct(
            transNumber: String,
            onSuccess: (OprProduct) -> Unit,
            onFailed: (String) -> Unit
        ){
            //create signature
var transactionNumber = TransactionHelper.getTransNumber(transNumber)
            val mapJson = mapOf(
                "requestType" to "getOprProduk",
                "reqDtime" to DateHelper.getCurrentDate(),
                "transNumber" to transactionNumber
            )
            FinpaySDK.signature = Signature()
            val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)
            val credential = Credentials.basic(userName, password)

            var header : HashMap<String, String> = hashMapOf()
            header["Authorization"] = credential
            val requestBody : HashMap<String, String> = hashMapOf()
            requestBody["requestType"] = "getOprProduk"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = DateHelper.getCurrentDate()
            requestBody["transNumber"] = transactionNumber

            val request = BaseServices.getRetrofitInstanceCoBrand().create(Api::class.java)
            request.getListOprProduct(requestBody).enqueue(object :
                Callback<OprProduct> {
                override fun onFailure(call: Call<OprProduct>, t: Throwable) {
                    onFailed(t.message.toString())
                }
                override fun onResponse(
                    call: Call<OprProduct>,
                    response: Response<OprProduct>
                ) {
                    if (response.code() == 200) {
                        if (response.body()?.statusCode == "000") {
                            onSuccess(response.body()!!)
                        } else {
                            onFailed(response.body()?.statusDesc.toString())
                        }
                    } else {
                        onFailed(Constant.defaultErrorMessage)
                    }
                }
            })
        }
    }
}