package lib.finpay.sdk.data.preference

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import lib.finpay.sdk.di.scope.ApplicationScope
import lib.finpay.sdk.domain.preference.PrefHelper
import lib.finpay.sdk.utils.AppConstants.PREF_FILE_NAME
import javax.inject.Inject

@ApplicationScope
class PrefHelperImpl @Inject constructor(ctx: Application) : PrefHelper {

    private val mPrefs: SharedPreferences =
        ctx.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    private val editor = mPrefs.edit()

    override fun saveToString(prefName: String, prefValue: String) {
        editor.putString(prefName, prefValue)
        editor.apply()
    }

    override fun saveToBoolean(prefName: String, prefValue: Boolean) {
        editor.putBoolean(prefName, prefValue)
        editor.apply()
    }

    override fun saveToInteger(prefName: String, prefValue: Int) {
        editor.putInt(prefName, prefValue)
        editor.apply()
    }

    override fun readString(prefName: String, defValue: String): String? =
        mPrefs.getString(prefName, defValue)

    override fun readBoolean(prefName: String, defValue: Boolean): Boolean =
        mPrefs.getBoolean(prefName, defValue)

    override fun readInteger(prefName: String, defValue: Int): Int =
        mPrefs.getInt(prefName, defValue)
}
