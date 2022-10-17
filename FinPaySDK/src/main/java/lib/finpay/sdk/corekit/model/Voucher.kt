package lib.finpay.sdk.corekit.model

import com.google.gson.annotations.SerializedName

class Voucher(imageSrc : String,
              voucherName:String) {
    @SerializedName("image")
    private var image: String = imageSrc

    @SerializedName("name")
    private var name: String = voucherName

    fun getImage(): String {
        return image
    }

    fun getName():String {
        return name
    }
}