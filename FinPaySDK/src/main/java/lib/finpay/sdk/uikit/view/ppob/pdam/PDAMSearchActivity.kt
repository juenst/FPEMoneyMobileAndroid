package lib.finpay.sdk.uikit.view.ppob.pdam

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.Country
import lib.finpay.sdk.corekit.model.PDAMRegion
import lib.finpay.sdk.uikit.utilities.Utils.getJsonFromRaw
import lib.finpay.sdk.uikit.view.ppob.pdam.adapter.PDAMAdapter

class PDAMSearchActivity : AppCompatActivity() {
    private lateinit var txtSearchPDAM: SearchView
    private lateinit var rvWilayah: RecyclerView

    private var filteredPDAM: ArrayList<PDAMRegion> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdam_search)
        supportActionBar!!.hide()

        rvWilayah = findViewById(R.id.rvWilayah)
        txtSearchPDAM = findViewById(R.id.txtSearchPDAM)

        setupRecycler()
    }

    private fun setupRecycler() {
        val wilayahString: String = this.getJsonFromRaw(R.raw.country_data)
        val wilayahList = Gson().fromJson(wilayahString, Array<PDAMRegion>::class.java).toList()
        filteredPDAM.addAll(wilayahList)

        val cListAdapter = PDAMAdapter(filteredPDAM, onClickListener = { pdam ->
            val intent = Intent()
            intent.putExtra("pdamSelectedResult", pdam.name)
            setResult(RESULT_OK, intent)
            finish()
        })
        rvWilayah.setHasFixedSize(true)
        rvWilayah.apply {
            rvWilayah.layoutManager = LinearLayoutManager(this@PDAMSearchActivity)
            rvWilayah.adapter = cListAdapter
        }

        txtSearchPDAM.setIconifiedByDefault(false)
        txtSearchPDAM.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText!!.lowercase()
                if (searchText.isNotEmpty()) {
                    filteredPDAM.clear()
                    wilayahList.forEach { data ->
                        if (data.name!!.lowercase().contains(searchText)) {
                            filteredPDAM.add(data)
                        }
                    }
                    rvWilayah.adapter!!.notifyDataSetChanged()
                } else {
                    filteredPDAM.clear()
                    filteredPDAM.addAll(wilayahList)
                    rvWilayah.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })
    }
}