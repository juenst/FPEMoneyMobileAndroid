package lib.finpay.sdk.uikit.utilities

import java.text.NumberFormat
import java.util.*

public class TextUtils {
    companion object {
        public fun formatRupiah(number: Double): String{
            val localeID =  Locale("in", "ID")
            val numberFormat = NumberFormat.getCurrencyInstance(localeID)
            return numberFormat.format(number).toString().replace(",00", "")
        }

        public fun clearFormat(text: String): String {
            var string: String = text.replace("Rp", "").replace(",","").replace(".","")
            return string
        }
    }
}