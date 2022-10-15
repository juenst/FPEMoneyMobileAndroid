package lib.finpay.sdk.corekit.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Country(
    val name: String?,
    val code: String?,
) : Parcelable