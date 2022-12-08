package lib.finpay.sdk.corekit.repository

import lib.finpay.sdk.corekit.helper.Signature
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.helper.DateHelper
import lib.finpay.sdk.corekit.helper.TransactionHelper
import lib.finpay.sdk.corekit.model.MutasiBallance
import lib.finpay.sdk.corekit.service.BaseServices
import lib.finpay.sdk.corekit.service.network.Api
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class MutasiBallanceRepository() {

    companion object {
        var tokenID: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.TOKEN_ID)!!
        var phoneNumber: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)!!
        var userName: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_USERNAME)!!
        var password: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_PASSWORD)!!
        var secretKey: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_SECRET_KEY)!!

        fun mutasiBallance(
            transNumber: String,
            pin: String,
            custName: String,
            otp: String,
            custStatusCode: String,
            transAmount: String,
            transType: String,
            transDesc: String,
            startDate: String,
            endDate: String,
            onSuccess: (MutasiBallance) -> Unit,
            onFailed: (String) -> Unit
        )  {
                //create signature
                val mapJson = mapOf(
                    "requestType" to "mutasiBalance",
                    "phoneNumber" to phoneNumber,
                    "pin" to pin,
                    "reqDtime" to DateHelper.getCurrentDate(),
                    "custName" to custName,
                    "otp" to otp,
                    "custStatusCode" to custStatusCode,
                    "transNumber" to TransactionHelper.getTransNumber(transNumber),
                    "transAmount" to transAmount,
                    "transType" to transType,
                    "transDesc" to transDesc,
                    "startDate" to startDate,
                    "endDate" to endDate,
                    "tokenID" to tokenID,
                )
                FinpaySDK.signature = Signature()
                val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)

                //auth header
                val credential = Credentials.basic(userName, password)
                var header : HashMap<String, String> = hashMapOf()
                header["Authorization"] = credential

                //request body
                val requestBody : HashMap<String, String> = hashMapOf()
                requestBody["requestType"] = "mutasiBalance"
                requestBody["phoneNumber"] = phoneNumber
                requestBody["pin"] = pin
                requestBody["reqDtime"] = DateHelper.getCurrentDate()
                requestBody["custName"] = custName
                requestBody["otp"] = otp
                requestBody["custStatusCode"] = custStatusCode
                requestBody["transNumber"] = TransactionHelper.getTransNumber(transNumber)
                requestBody["transAmount"] = transAmount
                requestBody["transType"] = transType
                requestBody["transDesc"] = transDesc
                requestBody["startDate"] = startDate
                requestBody["endDate"] = endDate
                requestBody["tokenID"] = tokenID
                requestBody["signatureID"] = signatureID

                val request = BaseServices.getRetrofitInstance().create(Api::class.java)

                request.mutasiBallance(requestBody).enqueue(object : Callback<MutasiBallance> {
                    override fun onFailure(call: Call<MutasiBallance>, t: Throwable) {
                        onFailed(t.message.toString())
                    }
                    override fun onResponse(
                        call: Call<MutasiBallance>,
                        response: Response<MutasiBallance>
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