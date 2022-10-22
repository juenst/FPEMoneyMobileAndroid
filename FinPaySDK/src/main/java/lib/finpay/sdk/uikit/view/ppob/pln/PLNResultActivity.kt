package lib.finpay.sdk.uikit.view.ppob.pln

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.model.DataSubProduct
import lib.finpay.sdk.uikit.constant.PaymentType
import lib.finpay.sdk.uikit.utilities.ButtonUtils
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.TextUtils
import lib.finpay.sdk.uikit.utilities.Utils
import lib.finpay.sdk.uikit.view.payment.PaymentActivity
import lib.finpay.sdk.uikit.view.ppob.pulsa_data.adapter.PulsaDataAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class PLNResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pln_result)
        supportActionBar!!.hide()
    }
}