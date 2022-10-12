package lib.finpay.sdk.uikit.view.instalment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import lib.finpay.sdk.R

class InstalmentTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instalment_transaction)
        supportActionBar!!.hide()
    }
}