package lib.finpay.sdk.uikit.view.pulsa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK

class CreditTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit_transaction)
        supportActionBar!!.hide()

        val listOpr: ArrayList<String> = arrayListOf()

        FinpaySDK.getListSubProduct(
            listOpr
        ) {
            println("Data List subProduct " + it)
        }

        FinpaySDK.getListOprProduct(
            "503000"
        ){
            println("data List Opr Product code 503000 : " + it)
        }
    }
}