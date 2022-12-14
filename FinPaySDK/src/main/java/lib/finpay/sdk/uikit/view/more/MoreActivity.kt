package lib.finpay.sdk.uikit.view.more

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.Product
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.view.ppob.alfamart.AlfamartActivity
import lib.finpay.sdk.uikit.view.ppob.bpjs.BpjsActivity
import lib.finpay.sdk.uikit.view.ppob.bpjs.BpjsKesehatanActivity
import lib.finpay.sdk.uikit.view.ppob.telkomsel.BestTelkomselPackageActivity
import lib.finpay.sdk.uikit.view.ppob.finpay.FinpayActivity
import lib.finpay.sdk.uikit.view.ppob.instalment.InstalmentActivity
import lib.finpay.sdk.uikit.view.ppob.insurance.InsuranceActivity
import lib.finpay.sdk.uikit.view.ppob.internettvcable.InternetTvCableActivity
import lib.finpay.sdk.uikit.view.ppob.pascabayar.PascaBayarActivity
import lib.finpay.sdk.uikit.view.ppob.pdam.PDAMActivity
import lib.finpay.sdk.uikit.view.ppob.revenue.RevenueActivity
import lib.finpay.sdk.uikit.view.ppob.pegadaian.PegadaianActivity
import lib.finpay.sdk.uikit.view.ppob.pln.PLNActivity
import lib.finpay.sdk.uikit.view.ppob.telkom.TelkomActivity
import lib.finpay.sdk.uikit.view.ppob.voucher.VoucherDealsActivity

class MoreActivity : AppCompatActivity() {
    lateinit var appbar: androidx.appcompat.widget.Toolbar
    lateinit var appbarTitle: TextView
    lateinit var btnBack: ImageView
    private lateinit var btnAlfamart: LinearLayout
    private lateinit var btnInternetTvCable: LinearLayout
    private lateinit var btnStateRevenue: LinearLayout
    private lateinit var btnBestTelkomselPackage: LinearLayout
    private lateinit var btnPDAM: LinearLayout
    private lateinit var btnVoucherDeals: LinearLayout

    private lateinit var btnMenuCredit: LinearLayout
    private lateinit var btnMenuBpjs: LinearLayout
    private lateinit var btnMenuTelkom: LinearLayout
    private lateinit var btnInstalment: LinearLayout
    private lateinit var btnFinpay: LinearLayout
    private lateinit var btnInsurance: LinearLayout
    private lateinit var btnPegadaian: LinearLayout
    private lateinit var btnPln: LinearLayout
    private lateinit var btnPascaBayar: LinearLayout
    lateinit var txtEditTitle:TextView

    private lateinit var dataProduct: Product

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more)
        supportActionBar!!.hide()

        appbar = findViewById(R.id.appbar)
        appbarTitle = findViewById(R.id.appbar_title)
        btnBack = findViewById(R.id.btnBack)
        btnAlfamart = findViewById(R.id.btn_alfamart)
        btnInternetTvCable = findViewById(R.id.btn_internet_tv_cable)
        btnBestTelkomselPackage = findViewById(R.id.btn_best_telkomsel_package)
        btnPDAM = findViewById(R.id.btn_pdam)
        btnStateRevenue = findViewById(R.id.btnStateRevenue)
        btnVoucherDeals = findViewById(R.id.btn_voucher_deals)
        btnMenuCredit = findViewById(R.id.menuCredit)
        btnMenuBpjs = findViewById(R.id.menuBpjs)
        btnMenuTelkom = findViewById(R.id.menuTelkom)
        btnInstalment = findViewById(R.id.menuCicilan)
        btnFinpay = findViewById(R.id.btnFinpay)
        btnInsurance = findViewById(R.id.btnInsurance)
        btnPegadaian = findViewById(R.id.btnPegadaian)
        btnPln = findViewById(R.id.btn_pln)
        btnPascaBayar = findViewById(R.id.btn_pascabayar)
        txtEditTitle = findViewById(R.id.edit_title)

        dataProduct = Product()

        //theming
        appbar.setBackgroundColor(if(finpayTheme?.getAppBarBackgroundColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getAppBarBackgroundColor()!!)
        appbarTitle.setTextColor(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        btnBack.setColorFilter(if(finpayTheme?.getAppBarTextColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getAppBarTextColor()!!)
        txtEditTitle.setTextColor(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#00ACBA") else finpayTheme?.getPrimaryColor()!!)

        btnBack.setOnClickListener {
            finish()
        }

        btnFinpay.setOnClickListener {
            val intent = Intent(this, FinpayActivity::class.java)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            this.startActivity(intent)
        }

        btnMenuBpjs.setOnClickListener {
            val intent = Intent(this, BpjsKesehatanActivity::class.java)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            this.startActivity(intent)
        }

        btnInsurance.setOnClickListener {
            val intent = Intent(this, InsuranceActivity::class.java)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            this.startActivity(intent)
        }

        btnAlfamart.setOnClickListener {
            val intent = Intent(this, AlfamartActivity::class.java)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            this.startActivity(intent)
        }

        btnBestTelkomselPackage.setOnClickListener {
            val intent = Intent(this, BestTelkomselPackageActivity::class.java)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            this.startActivity(intent)
        }

        btnInternetTvCable.setOnClickListener {
            val intent = Intent(this, InternetTvCableActivity::class.java)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            this.startActivity(intent)
        }

        btnPDAM.setOnClickListener {
            val intent = Intent(this, PDAMActivity::class.java)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            this.startActivity(intent)
        }

        btnStateRevenue.setOnClickListener {
            val intent = Intent(this, RevenueActivity::class.java)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            this.startActivity(intent)
        }

        btnVoucherDeals.setOnClickListener {
            val intent = Intent(this, VoucherDealsActivity::class.java)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            this.startActivity(intent)
        }

        btnInstalment.setOnClickListener {
            val intent = Intent(this, InstalmentActivity::class.java)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            this.startActivity(intent)
        }

        btnPegadaian.setOnClickListener {
            val intent = Intent(this, PegadaianActivity::class.java)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            this.startActivity(intent)
        }

        btnPln.setOnClickListener {
            val intent = Intent(this, PLNActivity::class.java)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            this.startActivity(intent)
        }

        btnPascaBayar.setOnClickListener {
            val intent = Intent(this, PascaBayarActivity::class.java)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            this.startActivity(intent)
        }

        btnMenuTelkom.setOnClickListener {
            val intent = Intent(this, TelkomActivity::class.java)
            intent.putExtra("transNumber", transNumber!!)
            intent.putExtra("theme", finpayTheme)
            this.startActivity(intent)
        }
    }

//    fun changeMenu(nameMenu: String, button: LinearLayout) {
//
//        button.setOnClickListener {
//            when (nameMenu) {
////                "Finpay" -> {
////                    var listRandom =
////                        dataProduct.dataProduct!!.filter {
////                            it.productDesc!!.contains("Finpay")
////                        } as ArrayList<DetailProductModel>
////                    println("dataFinpay : " + listRandom.toString())
////                    val intent = Intent(this, FinpayActivity::class.java)
////                    intent.putExtra("dataFinpay", listRandom.toJson())
////                    startActivity(intent)
////                }
//                "Alfamart" -> {
//                    var listRandom =
//                        dataProduct.dataProduct!!.filter {
//                            it.productDesc!!.contains("Alfamart")
//                        } as ArrayList<DetailProductModel>
//                    println("dataAlfamart : " + listRandom.toString())
//                    val intent = Intent(this, AlfamartActivity::class.java)
//                    intent.putExtra("dataAlfamart", listRandom.toJson())
//                    startActivity(intent)
//                }
//                "Asuransi" -> {
//                    var listRandom =
//                        dataProduct.dataProduct!!.filter {
//                            it.productDesc!!.contains("Asuransi")
//                        } as ArrayList<DetailProductModel>
//                    println("dataAsuransi : " + listRandom.toString())
//                    val intent = Intent(this, AlfamartActivity::class.java)
//                    intent.putExtra("dataAsuransi", listRandom.toJson())
//                    startActivity(intent)
//                }
//                "Best Telkomsel" -> {
//                    var listRandom =
//                        dataProduct.dataProduct!!.filter {
//                            it.productDesc!!.contains("Best Telkomsel")
//                        } as ArrayList<DetailProductModel>
//                    println("dataBest Telkomsel : " + listRandom.toString())
//                    val intent = Intent(this, AlfamartActivity::class.java)
//                    intent.putExtra("dataBest Telkomsel", listRandom.toJson())
//                    startActivity(intent)
//                }
//                "Internet Tv Cable" -> {
//                    var listRandom =
//                        dataProduct.dataProduct!!.filter {
//                            it.productDesc!!.contains("Best Telkomsel")
//                        } as ArrayList<DetailProductModel>
//                    println("dataBest Telkomsel : " + listRandom.toString())
//                    val intent = Intent(this, AlfamartActivity::class.java)
//                    intent.putExtra("dataBest Telkomsel", listRandom.toJson())
//                    startActivity(intent)
//                }
//                "Credit" -> {
//                    var listRandom =
//                        dataProduct.dataProduct!!.filter {
//                            it.productDesc!!.contains("Data")
//                        } as ArrayList<DetailProductModel>
//                    println("dataCredit : " + listRandom.toString())
//                    val intent = Intent(this, PulsaDataActivity::class.java)
//                    intent.putExtra("dataCredit", listRandom.toJson())
//                    startActivity(intent)
//                }
//                "BPJS" -> {
//                    var listRandom =
//                        dataProduct.dataProduct!!.filter {
//                            it.productDesc!!.contains("BPJS")
//                        } as ArrayList<DetailProductModel>
//                    println("dataBPJS : " + listRandom.toString())
//                    val intent = Intent(this, BpjsActivity::class.java)
//                    intent.putExtra("dataBPJS", listRandom.toJson())
//                    startActivity(intent)
//                }
//                "Telkom" -> {
//                    var listRandom =
//                        dataProduct.dataProduct!!.filter {
//                            it.productDesc!!.contains("Telkom")
//                        } as ArrayList<DetailProductModel>
//                    println("dataTelkom : " + listRandom.toString())
//                    val intent = Intent(this, TelkomActivity::class.java)
//                    intent.putExtra("dataTelkom", listRandom.toJson())
//                    startActivity(intent)
//                }
//                "Cicilan" -> {
//                    var listRandom =
//                        dataProduct.dataProduct!!.filter {
//                            it.productDesc!!.contains("Cicilan")
//                        } as ArrayList<DetailProductModel>
//                    println("DataInstallMent : " + listRandom.toString())
//                    var intent = Intent(this, InstalmentActivity::class.java)
//                    intent.putExtra("dataInstalment", listRandom.toJson())
//                    startActivity(intent)
//
//                }
//            }
//        }
//    }

//    fun getListProduct() {
//        FinpaySDK.getListProduct(this, {
//            dataProduct = it
//            println("data product " + dataProduct.toJson())
//        }, {
//            Toast.makeText(this@MoreActivity, it, Toast.LENGTH_LONG)
//        })
//    }

}