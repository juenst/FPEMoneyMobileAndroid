package com.finpay.wallet.view.upgrade_acc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R
import com.shuhart.stepview.StepView

class UpgradeAccountActivity : AppCompatActivity() {
    private lateinit var stepView: StepView
    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_account)

        stepView = findViewById(R.id.step_view)
        stepView.state.animationType(StepView.ANIMATION_ALL).stepsNumber(3)
            .animationDuration(resources.getInteger(android.R.integer.config_shortAnimTime))
            .commit()

        val fragmentManager = supportFragmentManager
        val homeFragment = StepIdentityFragment()
        val fragment =
            fragmentManager.findFragmentByTag(StepIdentityFragment::class.java.simpleName)

        if (fragment !is StepIdentityFragment) {
            fragmentManager.beginTransaction()
                .add(
                    R.id.frame_container,
                    homeFragment,
                    StepIdentityFragment::class.java.simpleName
                )
                .commit()
        }
    }

}