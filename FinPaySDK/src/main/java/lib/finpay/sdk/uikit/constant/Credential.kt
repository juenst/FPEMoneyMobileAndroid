package lib.finpay.sdk.uikit.constant

import android.content.Context
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.model.Credential
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys

class Credential {
    companion object{
        fun credential(context: Context): Credential {
            FinpaySDK.init(context)
            var phoneNumber: String = FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)!!
            val cd = Credential()
            cd.setUsername(Constant.userName)
            cd.setPassword(Constant.password)
            cd.setSecretKey(Constant.secretKey)
            cd.setPhoneNumber(phoneNumber)
            cd.setCustName("Widiyanto Ramadhan")

            return cd
        }
    }
}