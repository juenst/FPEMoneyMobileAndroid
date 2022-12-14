package lib.finpay.sdk.uikit.view.upgrade

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
import lib.finpay.sdk.uikit.utilities.Utils.getJsonFromRaw

class CitizenshipActivity : AppCompatActivity() {
    private lateinit var searchViewNationality: SearchView
    private lateinit var txtWni: TextView
    private lateinit var recyclerViewCountry: RecyclerView

    private var filteredCountry: ArrayList<Country> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_citizenship)
        supportActionBar!!.hide()

        txtWni = findViewById(R.id.txtWni)
        recyclerViewCountry = findViewById(R.id.rvCountry)
        searchViewNationality = findViewById(R.id.searchNationality)


        setupRecycler()

        txtWni.setOnClickListener {
            val intent = Intent()
            intent.putExtra("countrySelectedResult", "Indonesia")
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun setupRecycler() {
        val countryString: String = this.getJsonFromRaw(R.raw.country_data)
        val countryList = Gson().fromJson(countryString, Array<Country>::class.java).toList()
        filteredCountry.addAll(countryList)

        val cListAdapter = CitizenshipListAdapter(filteredCountry, onClickListener = { country ->
            val intent = Intent()
            intent.putExtra("countrySelectedResult", country.name)
            setResult(RESULT_OK, intent)
            finish()
        })
        recyclerViewCountry.setHasFixedSize(true)
        recyclerViewCountry.apply {
            recyclerViewCountry.layoutManager = LinearLayoutManager(this@CitizenshipActivity)
            recyclerViewCountry.adapter = cListAdapter
        }

        searchViewNationality.setIconifiedByDefault(false)
        searchViewNationality.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText!!.lowercase()
                if (searchText.isNotEmpty()) {
                    filteredCountry.clear()
                    countryList.forEach { data ->
                        if (data.name!!.lowercase().contains(searchText)) {
                            filteredCountry.add(data)
                        }
                    }
                    recyclerViewCountry.adapter!!.notifyDataSetChanged()
                } else {
                    filteredCountry.clear()
                    filteredCountry.addAll(countryList)
                    recyclerViewCountry.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })
    }
}