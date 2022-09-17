package lib.finpay.sdk.model

data class TokenRequestModel(
    val requestType: String,
    val reqDtime: String,
    val transNumber: String,
    val signature: String
)