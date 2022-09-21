
import com.google.gson.annotations.SerializedName


class ApiResponse<T>(
    @field:SerializedName("statusCode")
    var statusCode: String,

    @field:SerializedName("statusDesc")
    var statusDesc: String,

    @field:SerializedName("data")
    val content: T
)