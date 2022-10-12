package lib.finpay.sdk.corekit.repository

import com.example.testing.Signature
import lib.finpay.sdk.corekit.constant.Constant
import lib.finpay.sdk.corekit.model.UpgradeAccountModel
import lib.finpay.sdk.corekit.service.BaseService
import lib.finpay.sdk.corekit.service.network.Api
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class UpgradeAccountRepository() {

    companion object {
        private lateinit var signature: Signature

        fun upgradeAccount(
            phoneNumber: String,
            tokenID: String,
            imageIdentity: String,
            imageSelfie: String,
            motherName: String,
            noKK: String,
            nationality: String,
            email: String,
            onSuccess: (UpgradeAccountModel) -> Unit,
            onFailed: (String) -> Unit
        )  {
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
                signature = Signature()
                val signatureID = signature.createSignature(mapJson,"daYumnMb")
                val credential = Credentials.basic(
                    Constant.userName,
                    Constant.password
                )
                var header : HashMap<String, String> = hashMapOf()
                header["Authorization"] = credential
                val requestBody : HashMap<String, String> = hashMapOf()
                requestBody["requestType"] = "getToken"
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

                val request = BaseService.getRetrofitInstance().create(Api::class.java)

                request.upgradeAccount(requestBody).enqueue(object : Callback<UpgradeAccountModel> {
                    override fun onFailure(call: Call<UpgradeAccountModel>, t: Throwable) {
                        println("response failure")
                        println(t.message)
                    }
                    override fun onResponse(
                        call: Call<UpgradeAccountModel>,
                        response: Response<UpgradeAccountModel>
                    ) {
                        if (response.code() == 200) {
                            if (response.body()?.getStatusCode() == "000") {
                                onSuccess(response.body()!!)
                            } else {
                                onFailed(response.body()?.getStatusDesc().toString())
                            }
                        } else {
                            onFailed("Oppss.. something wrong")
                        }
                    }
                })
        }
    }
}