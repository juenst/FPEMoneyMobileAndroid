package com.finpay.wallet.view.upgrade_acc

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.finpay.wallet.R

class UpgradeAccountActivity : AppCompatActivity(), FragmentCallback {
    private lateinit var appbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_account)
        appbar = findViewById(R.id.appbar_main)
        appbarSettings(appbar)

        val mFragmentManager = supportFragmentManager
        val mFirstStepFragment = StepperFirstFragment()
        val fragment =
            mFragmentManager.findFragmentByTag(StepperFirstFragment::class.java.simpleName)
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

    private fun appbarSettings(appbar: Toolbar) {
        appbar.setTitleTextColor(Color.parseColor("#182156"))
        appbar.setBackgroundColor(Color.parseColor("#F3F4F9"))
        setSupportActionBar(appbar)
        supportActionBar?.title = "Hello World From Upgrade"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
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
