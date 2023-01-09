package lib.finpay.sdk.uikit.view.ppob.pdam

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
import lib.finpay.sdk.corekit.model.PDAMRegion
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.Utils.getJsonFromRaw
import lib.finpay.sdk.uikit.view.ppob.pdam.adapter.PDAMAdapter

class PDAMSearchActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    lateinit var btnBack: ImageView
    private lateinit var txtSearchPDAM: SearchView
    private lateinit var rvWilayah: RecyclerView
    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    private var filteredPDAM: ArrayList<PDAMRegion> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdam_search)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        rvWilayah = findViewById(R.id.rvWilayah)
        btnBack = findViewById(R.id.btnBack)
        txtSearchPDAM = findViewById(R.id.txtSearchPDAM)

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
    }

    private fun setupRecycler() {
        val wilayahString: String = this.getJsonFromRaw(R.raw.pdam_data)
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