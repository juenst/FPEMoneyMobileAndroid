package lib.finpay.sdk.corekit.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WidgetTopUpModel {
    @SerializedName("statusCode")
    private var statusCode: String? = null

    @SerializedName("statusDesc")
    private var statusDesc: String? = null

    @SerializedName("widgetURL")
    private var widgetURL: String? = null

    fun WidgetTopUpModel(
        statusCode: String?,
        statusDesc: String?,
        widgetURL: String?,
    ) {
        this.statusCode = statusCode
        this.statusDesc = statusDesc
        this.widgetURL = widgetURL
    }

    fun getStatusCode(): String? {
        return statusCode
    }

    fun setStatusCode(statusCode: String?) {
        this.statusCode = statusCode
    }

    fun getStatusDesc(): String? {
        return statusDesc
    }

    fun setStatusDesc(statusDesc: String?) {
        this.statusDesc = statusDesc
    }

    fun getWidgetURL(): String? {
        return widgetURL
    }

    fun setWidgetURL(widgetURL: String?) {
        this.widgetURL = widgetURL
    }
}