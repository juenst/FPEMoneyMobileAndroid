package lib.finpay.sdk.corekit.repository

import com.example.testing.Signature
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.model.Customer
import lib.finpay.sdk.corekit.model.ReqConfirmationModel
import lib.finpay.sdk.corekit.service.BaseService
import lib.finpay.sdk.corekit.service.BaseServices
import lib.finpay.sdk.corekit.service.network.Api
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class CustomerRepository() {

    companion object {
        var tokenID: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.TOKEN_ID)!!
        var phoneNumber: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)!!
        var userName: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_USERNAME)!!
        var password: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_PASSWORD)!!
        var secretKey: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_SECRET_KEY)!!

        fun reqActivation(phoneNumber: String, onSuccess: (Customer) -> Unit, onFailed: (String) -> Unit)  {
            //create signature
            val sdf = SimpleDateFormat("yyyyMMdHHmmss")
            val currentDate = sdf.format(Date())
            val mapJson = mapOf(
                "requestType" to "reqActivation",
                "reqDtime" to currentDate,
                "transNumber" to currentDate,
                "phoneNumber" to phoneNumber,
                "tokenID" to tokenID
            )
            FinpaySDK.signature = Signature()
            val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)

            //auth header
            val credential = Credentials.basic(userName, password)
            var header : HashMap<String, String> = hashMapOf()
            header["Authorization"] = credential

            //request body
            val requestBody : HashMap<String, String> = hashMapOf()
            requestBody["requestType"] = "reqActivation"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = currentDate
            requestBody["transNumber"] = currentDate
            requestBody["phoneNumber"] = phoneNumber
            requestBody["tokenID"] = tokenID

            val request = BaseServices.getRetrofitInstance().create(Api::class.java)
            request.reqActivation(requestBody).enqueue(object : Callback<Customer> {
                override fun onFailure(call: Call<Customer>, t: Throwable) {
                    onFailed(t.message.toString())
                }
                override fun onResponse(
                    call: Call<Customer>,
                    response: Response<Customer>
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

        fun reqConfirmation(
            phoneNumber: String,
            custName: String,
            otp: String,
            custStatusCode: String,
            onSuccess: (Customer) -> Unit,
            onFailed: (String) -> Unit)  {
                //create signature
                val sdf = SimpleDateFormat("yyyyMMdHHmmss")
                val currentDate = sdf.format(Date())
                val mapJson = mapOf(
                    "requestType" to "reqConfirmation",
                    "reqDtime" to currentDate,
                    "transNumber" to currentDate,
                    "phoneNumber" to phoneNumber,
                    "tokenID" to tokenID,
                    "custName" to custName,
                    "otp" to otp,
                    "custStatusCode" to custStatusCode
                )
                FinpaySDK.signature = Signature()
                val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)

                //auth header
                val credential = Credentials.basic(userName, password)
                var header : HashMap<String, String> = hashMapOf()
                header["Authorization"] = credential

                //request body
                val requestBody : HashMap<String, String> = hashMapOf()
                requestBody["requestType"] = "reqConfirmation"
                requestBody["signature"] = signatureID
                requestBody["reqDtime"] = currentDate
                requestBody["transNumber"] = currentDate
                requestBody["phoneNumber"] = phoneNumber
                requestBody["tokenID"] = tokenID
                requestBody["custName"] = custName
                requestBody["otp"] = otp
                requestBody["custStatusCode"] = custStatusCode

                val request = BaseServices.getRetrofitInstance().create(Api::class.java)
                request.reqConfirmation(requestBody).enqueue(object : Callback<Customer> {
                    override fun onFailure(call: Call<Customer>, t: Throwable) {
                        onFailed(t.message.toString())
                    }
                    override fun onResponse(
                        call: Call<Customer>,
                        response: Response<Customer>
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