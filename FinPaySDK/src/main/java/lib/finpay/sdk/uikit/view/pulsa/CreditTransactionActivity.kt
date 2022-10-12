package lib.finpay.sdk.uikit.view.pulsa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import lib.finpay.sdk.R

class CreditTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit_transaction)
        supportActionBar!!.hide()
    }
}