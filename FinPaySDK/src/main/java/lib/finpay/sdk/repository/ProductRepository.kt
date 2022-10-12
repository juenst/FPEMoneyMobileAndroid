package lib.finpay.sdk.repository

import com.example.testing.Signature
import lib.finpay.sdk.constant.Constant
import lib.finpay.sdk.model.DetailProductModel
import lib.finpay.sdk.model.HistoryTransactionModel
import lib.finpay.sdk.model.ProductModel
import lib.finpay.sdk.service.BaseService
import lib.finpay.sdk.service.network.Api
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class ProductRepository()  {

    companion object{
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
            val signatureID = signature.createSignature(mapJson)
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
    }
}