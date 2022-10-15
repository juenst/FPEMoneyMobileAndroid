package lib.finpay.sdk.corekit.repository

import com.example.testing.Signature
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.model.AuthPin
import lib.finpay.sdk.corekit.model.PpobInquiry
import lib.finpay.sdk.corekit.service.BaseServices
import lib.finpay.sdk.corekit.service.network.Api
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class PinRepository {
    companion object {
        var tokenID: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.TOKEN_ID)!!
        var phoneNumber: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)!!
        var userName: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_USERNAME)!!
        var password: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_PASSWORD)!!
        var secretKey: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_SECRET_KEY)!!

        fun authPin(
            amount: String,
            productCode: String,
            onSuccess: (AuthPin) -> Unit,
            onFailed: (String) -> Unit)  {
                //create signature
                val sdf = SimpleDateFormat("yyyyMMdHHmmss")
                val currentDate = sdf.format(Date())
                val mapJson = mapOf(
                    "requestType" to "authPin",
                    "reqDtime" to currentDate,
                    "transNumber" to currentDate,
                    "phoneNumber" to PpobRepository.phoneNumber,
                    "tokenID" to PpobRepository.tokenID,
                    "amount" to amount,
                    "productCode" to productCode
                )
                FinpaySDK.signature = Signature()
                val signatureID = FinpaySDK.signature.createSignature(mapJson, PpobRepository.secretKey)

                //auth header
                val credential = Credentials.basic(PpobRepository.userName, PpobRepository.password)
                var header : HashMap<String, String> = hashMapOf()
                header["Authorization"] = credential

                //request body
                val requestBody : HashMap<String, String> = hashMapOf()
                requestBody["requestType"] = "authPin"
                requestBody["signature"] = signatureID
                requestBody["reqDtime"] = currentDate
                requestBody["transNumber"] = currentDate
                requestBody["phoneNumber"] = PpobRepository.phoneNumber
                requestBody["tokenID"] = PpobRepository.tokenID
                requestBody["amount"] = amount
                requestBody["productCode"] = productCode

                val request = BaseServices.getRetrofitInstance().create(Api::class.java)
                request.authPin(requestBody).enqueue(object : Callback<AuthPin> {
                    override fun onFailure(call: Call<AuthPin>, t: Throwable) {
                        onFailed(t.message.toString())
                    }
                    override fun onResponse(
                        call: Call<AuthPin>,
                        response: Response<AuthPin>
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