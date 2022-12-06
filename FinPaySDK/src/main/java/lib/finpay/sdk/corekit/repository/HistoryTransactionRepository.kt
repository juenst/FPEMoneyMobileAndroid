package lib.finpay.sdk.corekit.repository

import lib.finpay.sdk.corekit.helper.Signature
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.model.HistoryTransaction
import lib.finpay.sdk.corekit.service.BaseServices
import lib.finpay.sdk.corekit.service.network.Api
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class HistoryTransactionRepository() {

    companion object {
        var tokenID: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.TOKEN_ID)!!
        var phoneNumber: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)!!
        var userName: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_USERNAME)!!
        var password: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_PASSWORD)!!
        var secretKey: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_SECRET_KEY)!!

        fun getHistoryTransaction(
            startDate: String,
            endDate: String,
            onSuccess: (HistoryTransaction) -> Unit,
            onFailed: (String) -> Unit
        )  {
            //create signature
            val sdf = SimpleDateFormat("yyyyMMdHHmmss", Locale.ENGLISH)
            val currentDate = sdf.format(Date())
            val mapJson = mapOf(
                "requestType" to "getHist",
                "reqDtime" to currentDate,
                "transNumber" to currentDate,
                "phoneNumber" to phoneNumber,
                "tokenID" to tokenID,
                "startDate" to startDate,
                "endDate" to endDate
            )
            FinpaySDK.signature = Signature()
            val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)

            //auth header
            val credential = Credentials.basic(userName, password)
            var header : HashMap<String, String> = hashMapOf()
            header["Authorization"] = credential

            //request body
            val requestBody : HashMap<String, String> = hashMapOf()
            requestBody["requestType"] = "getHist"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = currentDate
            requestBody["transNumber"] = currentDate
            requestBody["phoneNumber"] = phoneNumber
            requestBody["tokenID"] = tokenID
            requestBody["startDate"] = startDate
            requestBody["endDate"] = endDate

            val request = BaseServices.getRetrofitInstance().create(Api::class.java)
            request.getHistoryTransaction(requestBody).enqueue(object : Callback<HistoryTransaction> {
                override fun onFailure(call: Call<HistoryTransaction>, t: Throwable) {
                    onFailed(t.message.toString())
                }
                override fun onResponse(
                    call: Call<HistoryTransaction>,
                    response: Response<HistoryTransaction>
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

        fun getHistoryMasterTransaction(
            transType: String,
            startDate: String,
            endDate: String,
            onSuccess: (HistoryTransaction) -> Unit,
            onFailed: (String) -> Unit
        )  {
            //create signature
            val sdf = SimpleDateFormat("yyyyMMdHHmmss", Locale.ENGLISH)
            val currentDate = sdf.format(Date())
            val mapJson = mapOf(
                "requestType" to "getHistMaster",
                "reqDtime" to currentDate,
                "transNumber" to currentDate,
                "phoneNumber" to phoneNumber,
                "tokenID" to tokenID,
                "transType" to transType,
                "startDate" to startDate,
                "endDate" to endDate
            )
            FinpaySDK.signature = Signature()
            val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)

            //auth header
            val credential = Credentials.basic(userName, password)
            var header : HashMap<String, String> = hashMapOf()
            header["Authorization"] = credential

            //request body
            val requestBody : HashMap<String, String> = hashMapOf()
            requestBody["requestType"] = "getHistMaster"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = currentDate
            requestBody["transNumber"] = currentDate
            requestBody["phoneNumber"] = phoneNumber
            requestBody["tokenID"] = tokenID
            requestBody["transType"] = transType
            requestBody["startDate"] = startDate
            requestBody["endDate"] = endDate

            val request = BaseServices.getRetrofitInstance().create(Api::class.java)
            request.getHistoryMasterTransaction(requestBody).enqueue(object : Callback<HistoryTransaction> {
                override fun onFailure(call: Call<HistoryTransaction>, t: Throwable) {
                    onFailed(t.message.toString())
                }
                override fun onResponse(
                    call: Call<HistoryTransaction>,
                    response: Response<HistoryTransaction>
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