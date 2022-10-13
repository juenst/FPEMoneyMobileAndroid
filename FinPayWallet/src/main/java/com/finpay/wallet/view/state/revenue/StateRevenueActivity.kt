package com.finpay.wallet.view.state.revenue

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.finpay.wallet.R
import com.finpay.wallet.view.instalment.InstalmentTransactionActivity
import com.finpay.wallet.view.instalment.adapter.InstalmentAdapter
import com.finpay.wallet.view.state.revenue.adapter.StateRevenueAdapter
import com.google.gson.Gson
import lib.finpay.sdk.corekit.model.DetailProductModel
import org.json.JSONArray

class StateRevenueActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    private lateinit var listViewStateRevenue: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_state_revenue)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        listViewStateRevenue = findViewById(R.id.listview_state_revenue)

        btnBack.setOnClickListener {
            finish()
        }

        val objList: MutableList<DetailProductModel> = ArrayList()
        val intent = getIntent()
        val getData = intent.getStringExtra("dataInstalment")
        if (getData != null) {
            val jArray = JSONArray(getData)
            for (i in 0..(jArray.length() - 1)) {
                val gson = Gson()
                val dataDetail =
                    gson.fromJson(jArray[i].toString(), DetailProductModel::class.java)
                objList.add(dataDetail)
            }
        }

        listViewStateRevenue.adapter =
            StateRevenueAdapter(this, R.layout.item_menu_state_revenue, objList)

        listViewStateRevenue.setOnItemClickListener { parent, view, position, id ->
            val myItem = parent.getItemAtPosition(position) as DetailProductModel
            val intent = Intent(this, InstalmentTransactionActivity::class.java)
            startActivity(intent)
        }

    }
}