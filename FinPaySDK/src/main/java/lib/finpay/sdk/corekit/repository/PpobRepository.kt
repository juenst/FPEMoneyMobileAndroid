package lib.finpay.sdk.corekit.repository

import lib.finpay.sdk.corekit.helper.Signature
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.helper.DateHelper
import lib.finpay.sdk.corekit.helper.TransactionHelper
import lib.finpay.sdk.corekit.model.*
import lib.finpay.sdk.corekit.service.BaseServices
import lib.finpay.sdk.corekit.service.network.Api
import lib.finpay.sdk.uikit.utilities.PrefHelper
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class PpobRepository() {

    companion object {
        var tokenID: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.TOKEN_ID)!!
        var phoneNumber: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)!!
        var userName: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_USERNAME)!!
        var password: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_PASSWORD)!!
        var secretKey: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_SECRET_KEY)!!

        fun inquiry(
            transNumber: String,
            billingId: String,
            productCode: String,
            billingAmount: String,
            onSuccess: (PpobInquiry) -> Unit,
            onFailed: (String) -> Unit)  {
                //create signature
var transactionNumber = TransactionHelper.getTransNumber(transNumber)
                val mapJson = mapOf(
                    "requestType" to "inqBill",
                    "reqDtime" to DateHelper.getCurrentDate(),
                    "transNumber" to transactionNumber,
                    "phoneNumber" to phoneNumber,
                    "tokenID" to tokenID,
                    "billingId" to billingId,
                    "productCode" to productCode,
                    "billingAmount" to billingAmount
                )
                FinpaySDK.signature = Signature()
                val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)

                //auth header
                val credential = Credentials.basic(userName, password)
                var header : HashMap<String, String> = hashMapOf()
                header["Authorization"] = credential

                //request body
                val requestBody : HashMap<String, String> = hashMapOf()
                requestBody["requestType"] = "inqBill"
                requestBody["signature"] = signatureID
                requestBody["reqDtime"] = DateHelper.getCurrentDate()
                requestBody["transNumber"] = transactionNumber
                requestBody["phoneNumber"] = phoneNumber
                requestBody["tokenID"] = tokenID
                requestBody["billingId"] = billingId
                requestBody["productCode"] = productCode
                requestBody["billingAmount"] = billingAmount

                val request = BaseServices.getRetrofitInstanceCoBrand().create(Api::class.java)
                request.ppobInquiry(requestBody).enqueue(object : Callback<PpobInquiry> {
                    override fun onFailure(call: Call<PpobInquiry>, t: Throwable) {
                        onFailed(t.message.toString())
                    }
                    override fun onResponse(
                        call: Call<PpobInquiry>,
                        response: Response<PpobInquiry>
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

        fun payment(
            transNumber: String,
            phoneNumber: String,
            sof: String,
            payType: String,
            denom: String,
            amount: String,
            billingId: String,
            productCode: String,
            reffFlag: String,
            activationDate: String,
            pinToken: String,
            onSuccess: (PpobPayment) -> Unit,
            onFailed: (String) -> Unit)  {
                //create signature
var transactionNumber = TransactionHelper.getTransNumber(transNumber)
                val mapJson = mapOf(
                    "requestType" to "paymentConf",
                    "reqDtime" to DateHelper.getCurrentDate(),
                    "transNumber" to transactionNumber,
                    "phoneNumber" to phoneNumber,
                    "tokenID" to tokenID,
                    "sof" to sof,
                    "payType" to payType,
                    "denom" to denom,
                    "amount" to amount,
                    "billingId" to billingId,
                    "productCode" to productCode,
                    "reffFlag" to reffFlag,
                    "activationDate" to activationDate,
                    "pinToken" to pinToken,
                )
                FinpaySDK.signature = Signature()
                val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)

                //auth header
                val credential = Credentials.basic(userName, password)
                var header : HashMap<String, String> = hashMapOf()
                header["Authorization"] = credential

                //request body
                val requestBody : HashMap<String, String> = hashMapOf()
                requestBody["requestType"] = "paymentConf"
                requestBody["signature"] = signatureID
                requestBody["reqDtime"] = DateHelper.getCurrentDate()
                requestBody["transNumber"] = transactionNumber
                requestBody["phoneNumber"] = phoneNumber
                requestBody["tokenID"] = tokenID
                requestBody["sof"] = sof
                requestBody["payType"] = payType
                requestBody["denom"] = denom
                requestBody["amount"] = amount
                requestBody["billingId"] = billingId
                requestBody["productCode"] = productCode
                requestBody["reffFlag"] = reffFlag
                requestBody["activationDate"] = activationDate
                requestBody["pinToken"] = pinToken

                val request = BaseServices.getRetrofitInstanceCoBrand().create(Api::class.java)
                request.ppobPayment(requestBody).enqueue(object : Callback<PpobPayment> {
                    override fun onFailure(call: Call<PpobPayment>, t: Throwable) {
                        onFailed(t.message.toString())
                    }
                    override fun onResponse(
                        call: Call<PpobPayment>,
                        response: Response<PpobPayment>
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

        fun getFeePpob(
            transNumber: String,
            phoneNumber: String,
            payType: String,
            billingId: String,
            productCode: String,
            denom: String,
            onSuccess: (GetFee) -> Unit,
            onFailed: (String) -> Unit
        ){
            //create signature
var transactionNumber = TransactionHelper.getTransNumber(transNumber)
            val mapJson = mapOf(
                "requestType" to "getFee",
                "reqDtime" to DateHelper.getCurrentDate(),
                "transNumber" to transactionNumber,
                "phoneNumber" to phoneNumber,
                "tokenID" to tokenID,
                "payType" to payType,
                "billingId" to billingId,
                "productCode" to productCode,
                "denom" to denom
            )
            FinpaySDK.signature = Signature()
            val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)

            //auth header
            val credential = Credentials.basic(userName, password)
            var header : HashMap<String, String> = hashMapOf()
            header["Authorization"] = credential

            //request body
            val requestBody : HashMap<String, String> = hashMapOf()
            requestBody["requestType"] = "getFee"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = DateHelper.getCurrentDate()
            requestBody["transNumber"] = transactionNumber
            requestBody["phoneNumber"] = phoneNumber
            requestBody["tokenID"] = tokenID
            requestBody["payType"] = payType
            requestBody["billingId"] = billingId
            requestBody["productCode"] = productCode
            requestBody["denom"] = denom

            val request = BaseServices.getRetrofitInstanceCoBrand().create(Api::class.java)
            request.getFee(requestBody).enqueue(object : Callback<GetFee> {
                override fun onFailure(call: Call<GetFee>, t: Throwable) {
                    onFailed(t.message.toString())
                }
                override fun onResponse(
                    call: Call<GetFee>,
                    response: Response<GetFee>
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