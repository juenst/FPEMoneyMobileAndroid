package com.finpay.wallet.view.upgrade

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.finpay.wallet.R
import com.finpay.wallet.view.upgrade.stepper.StepperFirstFragment
import com.finpay.wallet.view.upgrade.stepper.StepperSecondFragment
import com.finpay.wallet.view.upgrade.stepper.StepperThirdFragment

class UpgradeAccountStepperActivity : AppCompatActivity(), FragmentCallback {
    private lateinit var appbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_accounts)

        val mFragmentManager = supportFragmentManager
        val mFirstStepFragment = StepperFirstFragment()
        val fragment = mFragmentManager.findFragmentByTag(StepperFirstFragment::class.java.simpleName)
        if (fragment !is StepperFirstFragment) {
            mFragmentManager
                .beginTransaction()
                .add(
                    R.id.frame_upgrade_acc,
                    mFirstStepFragment,
                    StepperFirstFragment::class.java.simpleName
                )
                .commit()
        }

    }

    override fun onFirstFr(uri: String) {
        val bundle = Bundle()
        bundle.putString(StepperSecondFragment.EXTRA_DATA, uri)

        val mFragmentManager = supportFragmentManager
        val mSecondStepFragment = StepperSecondFragment()
        mSecondStepFragment.arguments = bundle

        val fragment =
            mFragmentManager.findFragmentByTag(StepperSecondFragment::class.java.simpleName)

        if (fragment !is StepperSecondFragment) {
            mFragmentManager
                .beginTransaction()
                .replace(
                    R.id.frame_upgrade_acc,
                    mSecondStepFragment,
                    StepperSecondFragment::class.java.simpleName
                )
                .commit()
        }
    }

    override fun onSecondFr(firstData: String?, uri: String) {
        val bundle = Bundle()
        bundle.putString(StepperThirdFragment.EXTRA_FIRST_DATA, firstData)
        bundle.putString(StepperThirdFragment.EXTRA_SECOND_DATA, uri)

        val mFragmentManager = supportFragmentManager
        val mThirdStepFragment = StepperThirdFragment()
        mThirdStepFragment.arguments = bundle

        val fragment =
            mFragmentManager.findFragmentByTag(StepperThirdFragment::class.java.simpleName)
        if (fragment !is StepperThirdFragment) {
            mFragmentManager
                .beginTransaction()
                .replace(
                    R.id.frame_upgrade_acc,
                    mThirdStepFragment,
                    StepperThirdFragment::class.java.simpleName
                )
                .commit()
        }
    }
}

interface FragmentCallback {
    fun onFirstFr(uri: String)
    fun onSecondFr(firstData: String?, uri: String)
}
