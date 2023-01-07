package lib.finpay.sdk.uikit.view.transfer

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.view.upgrade.UpgradeAccountActivity

class TransferActivity : AppCompatActivity()  {
    lateinit var btnBack: ImageView
    lateinit var btnToOther: LinearLayout
    lateinit var btnToBank: LinearLayout
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        btnToOther = findViewById(R.id.btnToOther)
        btnToBank = findViewById(R.id.btnToBank)
        btnBack = findViewById(R.id.btnBack)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)

        btnBack.setOnClickListener{
            onBackPressed()
        }

        btnToOther.setOnClickListener {
            val intent = Intent(this, TransferToOtherActivity::class.java)
            intent.putExtra("transNumber", transNumber)
            intent.putExtra("theme", finpayTheme)
            this.startActivity(intent)
            this.finish()
        }

        btnToBank.setOnClickListener {
            val intent = Intent(this, TransferToBankActivity::class.java)
            intent.putExtra("transNumber", transNumber)
            intent.putExtra("theme", finpayTheme)
            this.startActivity(intent)
            this.finish()
        }
    }
}