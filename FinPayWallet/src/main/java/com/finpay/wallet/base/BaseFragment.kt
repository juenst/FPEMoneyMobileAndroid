package com.finpay.wallet.base

import android.app.Activity
import androidx.fragment.app.Fragment
import com.finpay.wallet.utilities.dialog.CustomDialog
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open class BaseFragment : DaggerFragment(), HasAndroidInjector {
    @Inject
    lateinit var dispactchingActivityInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispactchingActivityInjector

    @Inject
    lateinit var prefHelper: PrefHelper


    fun loadingDialog(activity: Activity) = CustomDialog.showLoadingDialog(activity, true)
}