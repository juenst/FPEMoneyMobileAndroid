package com.finpay.wallet.service.database

import androidx.room.TypeConverter
import com.finpay.wallet.utilities.extension.fromJson
import com.finpay.wallet.utilities.extension.toJson

class Converters {
    @TypeConverter
    fun fromList(value: String?): ArrayList<String>? = value?.let { value.fromJson() }

    @TypeConverter
    fun fromString(value: ArrayList<String>?): String? = value?.let { value.toJson() }
}