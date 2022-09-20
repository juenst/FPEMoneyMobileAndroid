package lib.finpay.sdk

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lib.finpay.sdk.helper.Signature
import lib.finpay.sdk.networks.ApiConfig
import java.text.SimpleDateFormat
import java.util.*


class FinPaySDK {
    private lateinit var signature: Signature

    fun getToken(
        merchantUsername: String,
        merchantPassword: String,
        merchantSecretKey: String,
        transNumber: String,
    ): String {
        val sdf = SimpleDateFormat("yyyyMMdHHmmss")
        val currentDate = sdf.format(Date())
        val mapJson = mapOf(
            "requestType" to "getToken",
            "reqDtime" to currentDate,
            "transNumber" to transNumber
        )
        signature = Signature()
        val signatureID = signature.createSignature(merchantSecretKey, mapJson)

        val requestBody: HashMap<String, String> = hashMapOf()
        requestBody["requestType"] = "getToken"
        requestBody["signature"] = signatureID
        requestBody["reqDtime"] = currentDate
        requestBody["transNumber"] = transNumber

        var tokenID: String = ""
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiConfig.apiService.getTokenAsync(requestBody)
            CoroutineScope(Dispatchers.Main).launch {
                if (response.isSuccessful) {
                    tokenID = if (response.body()?.statusCode == "000") {
                        println("response ok")
                        println(response.body()?.tokenID)
                        response.body()?.tokenID!!
                    } else {
                        println("statusCode != 200")
                        println(response.body()?.statusDesc)
                        ""
                    }
                }
            }
        }
        return tokenID
    }

    fun getBalance(
        merchantUsername: String,
        merchantPassword: String,
        merchantSecretKey: String,
        transNumber: String,
        phoneNumber: String,
    ) {
        val sdf = SimpleDateFormat("yyyyMMdHHmmss")
        val currentDate = sdf.format(Date())
        val mapJson = mapOf(
            "requestType" to "getBalance",
            "reqDtime" to currentDate,
            "transNumber" to transNumber,
            "phoneNumber" to phoneNumber,
            "tokenID" to ""
        )

        signature = Signature()
        val signatureID = signature.createSignature(merchantSecretKey, mapJson)

        val requestBody: HashMap<String, String> = hashMapOf()
        requestBody["requestType"] = "getToken"
        requestBody["signature"] = signatureID
        requestBody["reqDtime"] = currentDate
        requestBody["transNumber"] = transNumber
        requestBody["phoneNumber"] = phoneNumber
        requestBody["tokenID"] = transNumber

    }
}