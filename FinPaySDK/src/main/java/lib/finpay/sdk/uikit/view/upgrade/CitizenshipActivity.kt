package lib.finpay.sdk.uikit.view.upgrade

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.Country
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.Utils.getJsonFromRaw

class CitizenshipActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    private lateinit var btnBack: ImageView
    private lateinit var searchViewNationality: SearchView
    private lateinit var txtWni: TextView
    private lateinit var recyclerViewCountry: RecyclerView
    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    private var filteredCountry: ArrayList<Country> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_citizenship)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        txtWni = findViewById(R.id.txtWni)
        btnBack = findViewById(R.id.btnBack)
        recyclerViewCountry = findViewById(R.id.rvCountry)
        searchViewNationality = findViewById(R.id.searchNationality)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if(finpayTheme?.getAppBarBackgroundColor() == null) {
                window.setStatusBarColor(Color.parseColor("#333333"))
            } else {
                window.setStatusBarColor(finpayTheme?.getAppBarBackgroundColor()!!)
            }
        }

        setupRecycler()

        btnBack.setOnClickListener {
            onBackPressed()
        }

        txtWni.setOnClickListener {
            val intent = Intent()
            intent.putExtra("countrySelectedResult", "Indonesia")
            intent.putExtra("transNumber", transNumber)
            intent.putExtra("theme", finpayTheme)
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