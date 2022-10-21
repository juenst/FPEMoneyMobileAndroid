package lib.finpay.sdk.corekit.repository

import lib.finpay.sdk.corekit.helper.Signature
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.model.*
import lib.finpay.sdk.corekit.service.BaseServices
import lib.finpay.sdk.corekit.service.network.Api
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class TransferRepository() {

    companion object {
        var tokenID: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.TOKEN_ID)!!
        var phoneNumber: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)!!
        var userName: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_USERNAME)!!
        var password: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_PASSWORD)!!
        var secretKey: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_SECRET_KEY)!!

        fun inquiryOthers(
            phoneNumberDest: String,
            onSuccess: (TransferOtherInquiry) -> Unit,
            onFailed: (String) ->Unit
        )  {
                //create signature
                val sdf = SimpleDateFormat("yyyyMMdHHmmss")
                val currentDate = sdf.format(Date())
                val mapJson = mapOf(
                    "requestType" to "trfSesamaInquiry",
                    "reqDtime" to currentDate,
                    "transNumber" to currentDate,
                    "phoneNumber" to phoneNumber,
                    "phoneNumberDest" to phoneNumberDest,
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
                requestBody["requestType"] = "transaction"
                requestBody["signature"] = signatureID
                requestBody["reqDtime"] = currentDate
                requestBody["transNumber"] = currentDate
                requestBody["phoneNumber"] = phoneNumber
                requestBody["phoneNumberDest"] = phoneNumberDest
                requestBody["tokenID"] = tokenID

                val request = BaseServices.getRetrofitInstance().create(Api::class.java)

                request.transferOtherInquiry(requestBody).enqueue(object : Callback<TransferOtherInquiry> {
                    override fun onFailure(call: Call<TransferOtherInquiry>, t: Throwable) {
                        onFailed(t.message.toString())
                    }
                    override fun onResponse(
                        call: Call<TransferOtherInquiry>,
                        response: Response<TransferOtherInquiry>
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

        fun inquiryBank(
            bankCode: String,
            bankNo: String,
            amount: String,
            onSuccess: (TransferBankInquiry) -> Unit,
            onFailed: (String) ->Unit
        )  {
            //create signature
            val sdf = SimpleDateFormat("yyyyMMdHHmmss")
            val currentDate = sdf.format(Date())
            val mapJson = mapOf(
                "requestType" to "trfBankInquiry",
                "reqDtime" to currentDate,
                "transNumber" to currentDate,
                "phoneNumber" to phoneNumber,
                "bankCode" to bankCode,
                "bankNo" to bankNo,
                "amount" to amount,
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
            requestBody["requestType"] = "transaction"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = currentDate
            requestBody["transNumber"] = currentDate
            requestBody["phoneNumber"] = phoneNumber
            requestBody["bankCode"] = bankCode
            requestBody["bankNo"] = bankNo
            requestBody["amount"] = amount
            requestBody["tokenID"] = tokenID

            val request = BaseServices.getRetrofitInstance().create(Api::class.java)

            request.transferBankInquiry(requestBody).enqueue(object : Callback<TransferBankInquiry> {
                override fun onFailure(call: Call<TransferBankInquiry>, t: Throwable) {
                    onFailed(t.message.toString())
                }
                override fun onResponse(
                    call: Call<TransferBankInquiry>,
                    response: Response<TransferBankInquiry>
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

        fun paymentOthers(
            phoneNumberDest: String,
            amount: String,
            desc: String,
            pinToken: String,
            onSuccess: (TransferOtherPayment) -> Unit,
            onFailed: (String) ->Unit
        )  {
            //create signature
            val sdf = SimpleDateFormat("yyyyMMdHHmmss")
            val currentDate = sdf.format(Date())
            val mapJson = mapOf(
                "requestType" to "trfSesamaPayment",
                "reqDtime" to currentDate,
                "transNumber" to currentDate,
                "phoneNumber" to phoneNumber,
                "phoneNumberDest" to phoneNumberDest,
                "amount" to amount,
                "desc" to desc,
                "pinToken" to pinToken,
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
            requestBody["requestType"] = "trfSesamaPayment"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = currentDate
            requestBody["transNumber"] = currentDate
            requestBody["phoneNumber"] = phoneNumber
            requestBody["phoneNumberDest"] = phoneNumberDest
            requestBody["amount"] = amount
            requestBody["desc"] = desc
            requestBody["pinToken"] = pinToken
            requestBody["tokenID"] = tokenID

            val request = BaseServices.getRetrofitInstance().create(Api::class.java)

            request.transferOtherPayment(requestBody).enqueue(object : Callback<TransferOtherPayment> {
                override fun onFailure(call: Call<TransferOtherPayment>, t: Throwable) {
                    onFailed(t.message.toString())
                }
                override fun onResponse(
                    call: Call<TransferOtherPayment>,
                    response: Response<TransferOtherPayment>
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


        fun paymentBank(
            phoneNumberDest: String,
            reffFlag: String,
            reffTrx: String,
            category: String,
            pinToken: String,
            desc: String,
            onSuccess: (TransferBankPayment) -> Unit,
            onFailed: (String) ->Unit
        )  {
            //create signature
            val sdf = SimpleDateFormat("yyyyMMdHHmmss")
            val currentDate = sdf.format(Date())
            val mapJson = mapOf(
                "requestType" to "trfBankPayment",
                "reqDtime" to currentDate,
                "transNumber" to currentDate,
                "phoneNumber" to phoneNumber,
                "phoneNumberDest" to phoneNumberDest,
                "reffFlag" to reffFlag,
                "reffTrx" to reffTrx,
                "category" to category,
                "pinToken" to pinToken,
                "desc" to desc,
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
            requestBody["requestType"] = "trfBankPayment"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = currentDate
            requestBody["transNumber"] = currentDate
            requestBody["phoneNumber"] = phoneNumber
            requestBody["phoneNumberDest"] = phoneNumberDest
            requestBody["reffFlag"] = reffFlag
            requestBody["reffTrx"] = reffTrx
            requestBody["category"] = category
            requestBody["desc"] = desc
            requestBody["pinToken"] = pinToken
            requestBody["tokenID"] = tokenID

            val request = BaseServices.getRetrofitInstance().create(Api::class.java)

            request.transferBankPayment(requestBody).enqueue(object : Callback<TransferBankPayment> {
                override fun onFailure(call: Call<TransferBankPayment>, t: Throwable) {
                    onFailed(t.message.toString())
                }
                override fun onResponse(
                    call: Call<TransferBankPayment>,
                    response: Response<TransferBankPayment>
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

        fun getListBank(
            onSuccess: (Bank) -> Unit,
            onFailed: (String) ->Unit
        )  {
            //create signature
            val sdf = SimpleDateFormat("yyyyMMdHHmmss")
            val currentDate = sdf.format(Date())
            val mapJson = mapOf(
                "requestType" to "getBank",
                "reqDtime" to currentDate,
                "transNumber" to currentDate,
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
            requestBody["requestType"] = "getBank"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = currentDate
            requestBody["transNumber"] = currentDate
            requestBody["tokenID"] = tokenID

            val request = BaseServices.getRetrofitInstance().create(Api::class.java)

            request.getListBank(requestBody).enqueue(object : Callback<Bank> {
                override fun onFailure(call: Call<Bank>, t: Throwable) {
                    onFailed(t.message.toString())
                }
                override fun onResponse(
                    call: Call<Bank>,
                    response: Response<Bank>
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