package lib.finpay.sdk.uikit.view.transfer

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.DataBank
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.Utils.getJsonFromRaw

class BankActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    private lateinit var btnBack: ImageView
    private lateinit var searchViewBank: SearchView
    private lateinit var recyclerView: RecyclerView
    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }

    private var filteredBank: ArrayList<DataBank> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        btnBack = findViewById(R.id.btnBack)
        recyclerView = findViewById(R.id.rv)
        searchViewBank = findViewById(R.id.searchBank)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)


        setupRecycler()

        btnBack.setOnClickListener {
            onBackPressed()
        }

//        txtWni.setOnClickListener {
//            val intent = Intent()
//            intent.putExtra("countrySelectedResult", "Indonesia")
//            intent.putExtra("theme", finpayTheme)
//            setResult(RESULT_OK, intent)
//            finish()
//        }
    }

    private fun setupRecycler() {
        val bankString: String = this.getJsonFromRaw(R.raw.bank_data)
        val bankList = Gson().fromJson(bankString, Array<DataBank>::class.java).toList()
        filteredBank.addAll(bankList)

        val cListAdapter = BankListAdapter(filteredBank, onClickListener = { bank ->
            val intent = Intent()
            intent.putExtra("bankSelectedResult", bank.bank)
            setResult(RESULT_OK, intent)
            finish()
        })
        recyclerView.setHasFixedSize(true)
        recyclerView.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@BankActivity)
            recyclerView.adapter = cListAdapter
        }

        searchViewBank.setIconifiedByDefault(false)
        searchViewBank.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText!!.lowercase()
                if (searchText.isNotEmpty()) {
                    filteredBank.clear()
                    bankList.forEach { data ->
                        if (data.bank!!.lowercase().contains(searchText)) {
                            filteredBank.add(data)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    filteredBank.clear()
                    filteredBank.addAll(bankList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })
    }
}