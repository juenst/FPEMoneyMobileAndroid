package lib.finpay.sdk.model

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName

class VoucherModel(imageSrc : String,
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