package lib.finpay.sdk.uikit.view.ppob.instalment

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import com.google.gson.Gson
import lib.finpay.sdk.corekit.model.DetailProductModel
import lib.finpay.sdk.uikit.view.ppob.instalment.adapter.InstalmentAdapter
import org.json.JSONArray


class ChooseInstalmentActivity : AppCompatActivity() {
    lateinit var listInstalment: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_instalment)
        supportActionBar!!.hide()

        listInstalment = findViewById(R.id.list_instalment)

        var objList: MutableList<DetailProductModel> = ArrayList()
        val intent = getIntent()
        val getData =  intent.getStringExtra("dataInstalment")
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

        listInstalment.adapter = InstalmentAdapter(this, R.layout.item_instalment, objList)

        listInstalment.setOnItemClickListener { parent, view, position, id ->
            val myItem = parent.getItemAtPosition(position) as DetailProductModel
            val intent = Intent(this, InstalmentTransactionActivity::class.java)
            startActivity(intent)
        }

    }
}