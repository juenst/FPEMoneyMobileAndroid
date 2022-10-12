package lib.finpay.sdk.uikit.view.more

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R

import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.model.DetailProductModel
import lib.finpay.sdk.corekit.model.ProductModel
import lib.finpay.sdk.uikit.view.alfamart.AlfamartActivity
import lib.finpay.sdk.uikit.view.best.telkomsel.`package`.BestTelkomselPackageActivity
import lib.finpay.sdk.uikit.view.bpjs.ChooseBpjsActivity
import lib.finpay.sdk.uikit.view.instalment.ChooseInstalmentActivity
import lib.finpay.sdk.uikit.view.internet.tv.cable.InternetTvCableActivity
import lib.finpay.sdk.uikit.view.pdam.PDAMActivity
import lib.finpay.sdk.uikit.view.ppob.AsuransiActivity
import lib.finpay.sdk.uikit.view.ppob.FinpayActivity
import lib.finpay.sdk.uikit.view.pulsa.CreditTransactionActivity
import lib.finpay.sdk.uikit.view.state.revenue.StateRevenueActivity
import lib.finpay.sdk.uikit.view.telkom.TelkomTransactionActivity
import lib.finpay.sdk.uikit.view.voucher.VoucherDealsActivity
import lib.finpay.sdk.uikit.utilities.extension.toJson

class MoreActivity : AppCompatActivity() {
    lateinit var btnBack: ImageView
    private lateinit var btnAlfamart: LinearLayout
    private lateinit var btnInternetTvCable: LinearLayout
    private lateinit var btnStateRevenue: LinearLayout
    private lateinit var btnBestTelkomselPackage: LinearLayout
    private lateinit var btnPDAM: LinearLayout
    private lateinit var btnVoucherDeals: LinearLayout

    lateinit var btnMenuCredit: LinearLayout
    lateinit var btnMenuBpjs: LinearLayout
    lateinit var btnMenuTelkom: LinearLayout
    lateinit var btnMenuCicilan: LinearLayout

    lateinit var dataProduct: ProductModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more)
        supportActionBar!!.hide()

        btnBack = findViewById(R.id.btnBack)
        btnAlfamart = findViewById(R.id.btn_alfamart)
        btnInternetTvCable = findViewById(R.id.btn_internet_tv_cable)
        btnBestTelkomselPackage = findViewById(R.id.btn_best_telkomsel_package)
        btnPDAM = findViewById(R.id.btn_pdam)
        btnStateRevenue = findViewById(R.id.btn_state_revenue)
        btnVoucherDeals = findViewById(R.id.btn_voucher_deals)
        val btnFinpay = findViewById<LinearLayout>(R.id.btnFinpay)
        val btnAsuransi = findViewById<LinearLayout>(R.id.btnAsuransi)

        btnFinpay.setOnClickListener {
            val intent = Intent(this, FinpayActivity::class.java)
            this.startActivity(intent)
        }

        btnAsuransi.setOnClickListener {
            val intent = Intent(this, AsuransiActivity::class.java)
            this.startActivity(intent)
        }

        btnBack.setOnClickListener {
            finish()
        }

        btnAlfamart.setOnClickListener {
            val intent = Intent(this, AlfamartActivity::class.java)
            this.startActivity(intent)
        }

        btnBestTelkomselPackage.setOnClickListener {
            val intent = Intent(this, BestTelkomselPackageActivity::class.java)
            this.startActivity(intent)
        }

        btnInternetTvCable.setOnClickListener {
            val intent = Intent(this, InternetTvCableActivity::class.java)
            this.startActivity(intent)
        }

        btnPDAM.setOnClickListener {
            val intent = Intent(this, PDAMActivity::class.java)
            this.startActivity(intent)
        }

        btnStateRevenue.setOnClickListener {
            val intent = Intent(this, StateRevenueActivity::class.java)
            this.startActivity(intent)
        }

        btnVoucherDeals.setOnClickListener {
            val intent = Intent(this, VoucherDealsActivity::class.java)
            this.startActivity(intent)
        }

        btnMenuCredit = findViewById(R.id.menuCredit)
        btnMenuBpjs = findViewById(R.id.menuBpjs)
        btnMenuTelkom = findViewById(R.id.menuTelkom)
        btnMenuCicilan = findViewById(R.id.menuCicilan)

        btnBack.setOnClickListener{
            finish()
        }

        changeMenu("Credit",btnMenuCredit)
        changeMenu("BPJS",btnMenuBpjs)
        changeMenu("Telkom",btnMenuTelkom)
        changeMenu("Cicilan",btnMenuCicilan)

        getListProduct {
            values ->
            dataProduct = values
            println("data product " + dataProduct.toJson())
        }


    }

    fun changeMenu(nameMenu:String,button:LinearLayout){

        button.setOnClickListener{
            when(nameMenu){
                "Credit"->{
                    var listRandom =
                        dataProduct.getDataProduct()!!.filter {
                            it.getProductDesc()!!.contains("Data")
                        } as ArrayList<DetailProductModel>
                    println("dataCredit : " + listRandom.toString())
                    val intent = Intent(this, CreditTransactionActivity::class.java)
                    intent.putExtra("dataCredit", listRandom.toJson())
                    startActivity(intent)
                }
                "BPJS"->{
                    var listRandom =
                        dataProduct.getDataProduct()!!.filter {
                            it.getProductDesc()!!.contains("BPJS")
                        } as ArrayList<DetailProductModel>
                    println("dataBPJS : " + listRandom.toString())
                    val intent = Intent(this, ChooseBpjsActivity::class.java)
                    intent.putExtra("dataBPJS", listRandom.toJson())
                    startActivity(intent)
                }
                "Telkom"->{
                    var listRandom =
                        dataProduct.getDataProduct()!!.filter {
                            it.getProductDesc()!!.contains("Telkom")
                        } as ArrayList<DetailProductModel>
                    println("dataTelkom : " + listRandom.toString())
                    val intent = Intent(this, TelkomTransactionActivity::class.java)
                    intent.putExtra("dataTelkom", listRandom.toJson())
                    startActivity(intent)
                }
                "Cicilan"->{
                    var listRandom =
                        dataProduct.getDataProduct()!!.filter {
                            it.getProductDesc()!!.contains("Cicilan")
                        } as ArrayList<DetailProductModel>
                    println("DataInstallMent : " + listRandom.toString())
                    var intent = Intent(this, ChooseInstalmentActivity::class.java)
                    intent.putExtra("dataInstalment", listRandom.toJson())
                    startActivity(intent)

                }
            }
        }
    }

    fun getListProduct(
        onResult: (ProductModel)-> Unit
    ) {
        FinpaySDK().getListProduct {
            onResult(it)
        }
    }

}