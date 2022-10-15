package lib.finpay.sdk.uikit.view.topup

import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.Topup
import lib.finpay.sdk.uikit.view.topup.adapter.TopupAdapter

class TopupActivity : AppCompatActivity()  {
    lateinit var btnBack: ImageView
    lateinit var listChannel: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topup)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        listChannel = findViewById(R.id.list_channel)
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