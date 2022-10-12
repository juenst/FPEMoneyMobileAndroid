package lib.finpay.sdk.uikit.utilities

import lib.finpay.sdk.uikit.constant.Constant
import lib.finpay.sdk.corekit.model.Credential

class Credential {
    companion object {
        fun credential(): Credential {
            val cd = Credential()
            cd.setUsername(Constant.userName)
            cd.setPassword(Constant.password)
            cd.setSecretKey(Constant.secretKey)
            cd.setPhoneNumber("083815613839")
            cd.setCustName("Widiyanto Ramadhan")
            return cd
        }
    }
}