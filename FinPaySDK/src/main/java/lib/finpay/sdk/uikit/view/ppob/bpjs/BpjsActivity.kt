package lib.finpay.sdk.uikit.view.ppob.bpjs

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import lib.finpay.sdk.R
import com.google.gson.Gson
import lib.finpay.sdk.corekit.model.DetailProductModel
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.view.ppob.bpjs.adapter.BPJSAdapter
import org.json.JSONArray

class BpjsActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView

    lateinit var listBPJS: ListView

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bpjs_choose)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)

        btnBack = findViewById(R.id.btnBack)

        listBPJS = findViewById(R.id.list_bpjs)

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
            val intent = Intent(this, BpjsKesehatanActivity::class.java)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            startActivity(intent)
        }


    }

}