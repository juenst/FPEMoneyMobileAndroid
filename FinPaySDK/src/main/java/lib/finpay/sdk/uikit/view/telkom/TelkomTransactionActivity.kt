package lib.finpay.sdk.uikit.view.telkom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import lib.finpay.sdk.R

class TelkomTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telkom_transaction)
        supportActionBar!!.hide()
    }
}