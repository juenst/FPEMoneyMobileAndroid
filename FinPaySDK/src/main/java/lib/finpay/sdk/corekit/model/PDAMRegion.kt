package lib.finpay.sdk.corekit.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PDAMRegion(
    val name: String?,
    val code: String?,
) : Parcelable