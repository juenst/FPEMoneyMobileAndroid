package lib.finpay.sdk.uikit.helper

import android.graphics.Color
import com.google.gson.annotations.SerializedName

class FinpayTheme : java.io.Serializable {
    @SerializedName("primaryColor")
    private var primaryColor: Int? = null

    @SerializedName("secondaryColor")
    private var secondaryColor: Int? = null

    @SerializedName("appBarBackgroundColor")
    private var appBarBackgroundColor: Int? = null

    @SerializedName("appBarTextColor")
    private var appBarTextColor: Int? = null

    fun FinpayTheme(
        primaryColor: Int?,
        secondaryColor: Int?,
    ) {
        this.primaryColor = primaryColor
        this.secondaryColor = secondaryColor
        this.appBarBackgroundColor = appBarBackgroundColor
        this.appBarTextColor = appBarTextColor
    }

    fun getPrimaryColor(): Int? {
        return primaryColor
    }

    fun setPrimaryColor(primaryColor: Int?) {
        this.primaryColor = primaryColor
    }

    fun getSecondaryColor(): Int? {
        return secondaryColor
    }

    fun setSecondaryColor(secondaryColor: Int?) {
        this.secondaryColor = secondaryColor
    }

    fun getAppBarBackgroundColor(): Int? {
        return appBarBackgroundColor
    }

    fun setAppBarBackgroundColor(appBarBackgroundColor: Int?) {
        this.appBarBackgroundColor = appBarBackgroundColor
    }

    fun getAppBarTextColor(): Int? {
        return appBarTextColor
    }

    fun setAppBarTextColor(appBarTextColor: Int?) {
        this.appBarTextColor = appBarTextColor
    }
}