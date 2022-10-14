package lib.finpay.sdk.corekit.repository

import com.example.testing.Signature
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.model.OprProduct
import lib.finpay.sdk.corekit.model.ProductModel
import lib.finpay.sdk.corekit.model.SubProduct
import lib.finpay.sdk.corekit.service.BaseService
import lib.finpay.sdk.corekit.service.network.Api
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
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

        private lateinit var signature: Signature

        fun getListProduct(
            onResult: (ProductModel) -> Unit
        ){
            val sdf = SimpleDateFormat("yyyyMMdHHmmss")
            val currentDate = sdf.format(Date())
            val mapJson = mapOf(
                "requestType" to "getProduk",
                "reqDtime" to currentDate,
                "transNumber" to currentDate
            )
            signature = Signature()
            val signatureID = signature.createSignature(mapJson,"daYumnMb")
            val credential = Credentials.basic(
                Constant.userName,
                Constant.password
            )
            var header : HashMap<String, String> = hashMapOf()
            header["Authorization"] = credential
            val requestBody : HashMap<String, String> = hashMapOf()
            requestBody["requestType"] = "getProduk"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = currentDate
            requestBody["transNumber"] = currentDate

            val request = BaseService.getRetrofitInstance2().create(Api::class.java)

            request.getListProduct(requestBody).enqueue(object :
                Callback<ProductModel> {
                override fun onFailure(call: Call<ProductModel>, t: Throwable) {
                    println("response failure")
                    println(t.message)
                }
                override fun onResponse(
                    call: Call<ProductModel>,
                    response: Response<ProductModel>
                ) {
                    if (response.code() == 200) {
                        if (response.body()?.getStatusCode() == "000") {
                            onResult(response.body()!!)
//                            println("Data : " + response.body()!!.getDataProduct()!!.first().getProductDesc())
                        } else {
                            println("statusCode != 200")
                            println(response.body()?.getStatusDesc())
                        }
                    } else {
                        println("response code != 200")
                    }
                }
            })
        }

        fun getListSubProduct(
            listOpr: ArrayList<String>,
            onResult: (SubProduct) -> Unit
        ){
            val sdf = SimpleDateFormat("yyyyMMdHHmmss")
            val currentDate = sdf.format(Date())
            val mapJson = mapOf(
                "requestType" to "getDenom",
                "reqDtime" to currentDate,
                "transNumber" to currentDate,
                "tokenID" to tokenID,
                "phoneNumber" to phoneNumber,
                "opr" to listOpr
            )
            signature = Signature()
            val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)
            val credential = Credentials.basic(userName, password)

            var header : HashMap<String, String> = hashMapOf()
            header["Authorization"] = credential
            val requestBody : HashMap<String, Any> = hashMapOf()
            requestBody["requestType"] = "getDenom"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = currentDate
            requestBody["transNumber"] = currentDate
            requestBody["phoneNumber"] = phoneNumber
            requestBody["tokenID"] = tokenID
            requestBody["opr"] = listOpr

            val request = BaseService.getRetrofitInstance2().create(Api::class.java)

            request.getListSubProduct(requestBody).enqueue(object :
                Callback<SubProduct> {
                override fun onFailure(call: Call<SubProduct>, t: Throwable) {
                    println("response failure")
                    println(t.message)
                }
                override fun onResponse(
                    call: Call<SubProduct>,
                    response: Response<SubProduct>
                ) {
                    if (response.code() == 200) {
                        if (response.body()?.statusCode == "000") {
                            onResult(response.body()!!)
//                            println("Data : " + response.body()!!.getDataProduct()!!.first().getProductDesc())
                        } else {
                            println("statusCode != 200")
                            println(response.body()?.statusCode)
                        }
                    } else {
                        println("response code != 200")
                    }
                }
            })
        }

        fun getListOprProduct(
            productCode:String,
            onResult: (OprProduct) -> Unit
        ){
            val sdf = SimpleDateFormat("yyyyMMdHHmmss")
            val currentDate = sdf.format(Date())
            val mapJson = mapOf(
                "requestType" to "getOprProduk",
                "reqDtime" to currentDate,
                "transNumber" to currentDate
            )
            /*
            {
              "signature": "27620048843E41C486EDE9B29D78B91F81970B879CA3162B509CF3173940B2D5",
              "requestType": "getOprProduk",
              "transNumber": "xd7ayx2gs",
              "reqDtime": "20210929000000"
            }
             */
            signature = Signature()
            val signatureID = signature.createSignature(mapJson, secretKey)
            val credential = Credentials.basic(userName, password)

            var header : HashMap<String, String> = hashMapOf()
            header["Authorization"] = credential
            val requestBody : HashMap<String, String> = hashMapOf()
            requestBody["requestType"] = "getOprProduk"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = currentDate
            requestBody["transNumber"] = currentDate

            val request = BaseService.getRetrofitInstanceWithDynamicEndpoint(productCode).create(Api::class.java)

            request.getListOprProduct(requestBody).enqueue(object :
                Callback<OprProduct> {
                override fun onFailure(call: Call<OprProduct>, t: Throwable) {
                    println("response failure")
                    println(t.message)
                }
                override fun onResponse(
                    call: Call<OprProduct>,
                    response: Response<OprProduct>
                ) {
                    if (response.code() == 200) {
                        if (response.body()?.statusCode == "000") {
                            onResult(response.body()!!)
//                            println("Data : " + response.body()!!.getDataProduct()!!.first().getProductDesc())
                        } else {
                            println("statusCode != 200")
                            println(response.body()?.statusCode)
                        }
                    } else {
                        println("response code != 200")
                    }
                }
            })
        }
    }
}