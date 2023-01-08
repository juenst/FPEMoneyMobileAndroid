package lib.finpay.sdk.uikit.view.topup

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.Topup
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.view.topup.adapter.TopupAdapter

class TopupActivity : AppCompatActivity()  {
    lateinit var btnBack: ImageView
    lateinit var listChannel: ListView
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topup)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        btnBack = findViewById(R.id.btnBack)
        listChannel = findViewById(R.id.list_channel)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)

        var list = mutableListOf<Topup>()
        list.add(Topup(R.drawable.ic_logo_pos_indonesia, "Pos Indonesia"))
        list.add(Topup(R.drawable.ic_logo_alfamart, "Alfamart"))
        list.add(Topup(R.drawable.ic_logo_mandiri, "Mandiri"))
        list.add(Topup(R.drawable.ic_logo_briva, "BRIVA"))
        list.add(Topup(R.drawable.ic_logo_bca, "Bank Central Asia"))
        list.add(Topup(R.drawable.ic_logo_pegadaian, "Pegadaian"))
        list.add(Topup(R.drawable.ic_logo_permata_bank, "Permata Bank"))
        list.add(Topup(R.drawable.ic_logo_bnc, "BNC Bank"))
        listChannel.adapter = TopupAdapter(this,R.layout.item_topup,list)
        listChannel.setOnItemClickListener{paymentAdapter, view, position, id ->
            val selectedItem = paymentAdapter.getItemAtPosition(position)
            val itemIdAtPos = paymentAdapter.getItemIdAtPosition(position)

            //Toast.makeText(this@,"Anda memilih item ke $itemIdAtPos",Toast.LENGTH_SHORT).show()
        }

        btnBack.setOnClickListener{
            finish()
        }
    }

}