package com.finpay.wallet.view.bpjs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import com.finpay.wallet.R
import com.finpay.wallet.view.bpjs.adapter.BPJSAdapter
import com.finpay.wallet.view.instalment.ChooseInstalmentActivity
import com.finpay.wallet.view.instalment.InstalmentTransactionActivity
import com.finpay.wallet.view.instalment.adapter.InstalmentAdapter
import com.finpay.wallet.view.pulsa.CreditTransactionActivity
import com.finpay.wallet.view.telkom.TelkomTransactionActivity
import com.google.gson.Gson
import lib.finpay.sdk.model.DetailProductModel
import org.json.JSONArray

class ChooseBpjsActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView

    lateinit var listBPJS: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_bpjs)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)

        listBPJS = findViewById(R.id.list_bpjs)

        btnBack.setOnClickListener{
            finish()
        }

        var objList: MutableList<DetailProductModel> = ArrayList()
        val intent = getIntent()
        val getData =  intent.getStringExtra("dataBPJS")
        if (getData != null){
            val jArray = JSONArray(getData)
            if (jArray!= null){
                for (i in 0..(jArray.length()-1)){
                    val gson = Gson()
                    val dataDetail = gson.fromJson(jArray[i].toString(), DetailProductModel::class.java)
                    objList.add(dataDetail)
                }
            }
        }

        listBPJS.adapter = BPJSAdapter(this, R.layout.item_bpjs, objList)

        listBPJS.setOnItemClickListener { parent, view, position, id ->
            val myItem = parent.getItemAtPosition(position) as DetailProductModel
            val intent = Intent(this, BpjsTransactionActivity::class.java)
            startActivity(intent)
        }


    }

}