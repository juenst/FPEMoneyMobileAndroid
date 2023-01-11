package lib.finpay.sdk.uikit.view.topup

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.constant.ProductCode
import lib.finpay.sdk.corekit.model.Topup
import lib.finpay.sdk.uikit.FinpaySDKUI.Companion.progressDialog
import lib.finpay.sdk.uikit.constant.PaymentType
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.view.payment.PaymentActivity
import lib.finpay.sdk.uikit.view.topup.adapter.TopupAdapter

class TopupActivity : AppCompatActivity()  {
    lateinit var btnBack: ImageView
    lateinit var listChannel: ListView
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    lateinit var progressDialog: ProgressDialog

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topup)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        btnBack = findViewById(R.id.btnBack)
        listChannel = findViewById(R.id.list_channel)
        progressDialog = ProgressDialog(this@TopupActivity)

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)

        var list = mutableListOf<Topup>()
        list.add(Topup(R.drawable.ic_logo_bni, "BNI","vabni"))
        list.add(Topup(R.drawable.ic_logo_pos_indonesia, "Pos Indonesia","pos"))
        list.add(Topup(R.drawable.ic_logo_alfamart, "Alfamart","alfa"))
        list.add(Topup(R.drawable.ic_logo_mandiri, "Mandiri","vamandiri"))
        list.add(Topup(R.drawable.ic_logo_briva, "BRIVA","briva"))
        list.add(Topup(R.drawable.ic_logo_bca, "Bank Central Asia","vabca"))
        list.add(Topup(R.drawable.ic_logo_pegadaian, "Pegadaian","pegadaian"))
        list.add(Topup(R.drawable.ic_logo_permata_bank, "Permata Bank","vapermata"))
        list.add(Topup(R.drawable.ic_logo_bnc, "BNC Bank","vabnbc"))
        listChannel.adapter = TopupAdapter(this,R.layout.item_topup,list)
        listChannel.setOnItemClickListener{paymentAdapter, view, position, id ->

            var amounts:String = "0"
            var types:String = list.get(position).name

            if (position != 0 || position != 7 || position != 8){
                amounts = "0"
            }else{

            }

            progressDialog.setTitle("Mohon Menunggu")
            progressDialog.setMessage("Sedang Memuat ...")
            progressDialog.setCancelable(false)
            progressDialog.show()

            println("sof : ${list.get(position).sof}")


            FinpaySDK.topup(
                transNumber!!,
                this,
                amounts,
                list.get(position).sof, {
                    progressDialog.dismiss()
                    val intent = Intent(this, TopupGuidanceActivity::class.java)
                    intent.putExtra("result", it)
                    intent.putExtra("transNumber", transNumber!!)
                    intent.putExtra("type", types)
                    intent.putExtra("theme", finpayTheme)
                    startActivity(intent)
                    println("berhasil topup")
                }, {
                    progressDialog.dismiss()
                    DialogUtils.showDialogError(this, "", it, finpayTheme)
                }
            )

            //Toast.makeText(this@,"Anda memilih item ke $itemIdAtPos",Toast.LENGTH_SHORT).show()
        }

        btnBack.setOnClickListener{
            finish()
        }
    }

}