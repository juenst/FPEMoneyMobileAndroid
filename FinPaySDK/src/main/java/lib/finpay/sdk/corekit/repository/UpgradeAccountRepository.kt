package lib.finpay.sdk.corekit.repository

import com.example.testing.Signature
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.model.UpgradeAccount
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


class UpgradeAccountRepository() {

    companion object {
        var tokenID: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.TOKEN_ID)!!
        var phoneNumber: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)!!
        var userName: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_USERNAME)!!
        var password: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_PASSWORD)!!
        var secretKey: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.MERCHANT_SECRET_KEY)!!

        fun upgradeAccount(imageIdentity: String, imageSelfie: String, motherName: String, noKK: String, nationality: String, email: String, onSuccess: (UpgradeAccount) -> Unit, onFailed: (String) -> Unit)  {
            //create signature
            val sdf = SimpleDateFormat("yyyyMMdHHmmss")
            val currentDate = sdf.format(Date())
            val mapJson = mapOf(
                "requestType" to "upgradeAccount",
                "reqDtime" to currentDate,
                "transNumber" to currentDate,
                "phoneNumber" to phoneNumber,
                "tokenID" to tokenID,
                "imgIdentitas" to imageIdentity,
                "imgSelfie" to imageSelfie,
                "noKK" to noKK,
                "namaIbuKandung" to motherName,
                "kewarganegaraan" to nationality,
                "email" to email,
                "chID" to ""
            )
            FinpaySDK.signature = Signature()
            val signatureID = FinpaySDK.signature.createSignature(mapJson, secretKey)

            //auth header
            val credential = Credentials.basic(userName, password)
            var header : HashMap<String, String> = hashMapOf()
            header["Authorization"] = credential

            //request body
            val requestBody : HashMap<String, String> = hashMapOf()
            requestBody["requestType"] = "upgradeAccount"
            requestBody["signature"] = signatureID
            requestBody["reqDtime"] = currentDate
            requestBody["transNumber"] = currentDate
            requestBody["phoneNumber"] = phoneNumber
            requestBody["tokenID"] = tokenID
            requestBody["imgIdentitas"] = imageIdentity
            requestBody["imgSelfie"] = imageSelfie
            requestBody["noKK"] = noKK
            requestBody["namaIbuKandung"] = motherName
            requestBody["kewarganegaraan"] = nationality
            requestBody["email"] = email
            requestBody["chID"] = ""

            val request = BaseServices.getRetrofitInstance().create(Api::class.java)
            request.upgradeAccount(requestBody).enqueue(object : Callback<UpgradeAccount> {
                override fun onFailure(call: Call<UpgradeAccount>, t: Throwable) {
                    onFailed(t.message.toString())
                }
                override fun onResponse(
                    call: Call<UpgradeAccount>,
                    response: Response<UpgradeAccount>
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