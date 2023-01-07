package lib.finpay.sdk.uikit.view.wallet

import android.R.attr.bitmap
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.uikit.FinpaySDKUI
import lib.finpay.sdk.uikit.constant.Credential
import lib.finpay.sdk.uikit.helper.FinpayTheme
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.SharedPrefKeys
import lib.finpay.sdk.uikit.utilities.TextUtils
import lib.finpay.sdk.uikit.view.topup.TopupActivity
import lib.finpay.sdk.uikit.view.transaction.adapter.WalletHistoryAdapter
import lib.finpay.sdk.uikit.view.transfer.TransferActivity
import lib.finpay.sdk.uikit.view.upgrade.UpgradeAccountActivity
import java.util.*


class WalletSDKActivity : AppCompatActivity() {
    lateinit var txtUsername: TextView
    lateinit var txtSaldo: TextView
    lateinit var btnTopup: LinearLayout
    lateinit var btnTransfer: LinearLayout
    lateinit var btnHistoryTransaction: LinearLayout
    lateinit var btnWallet: LinearLayout

    lateinit var sectionUpgradeAccount: LinearLayout
    lateinit var txtSeeAll: TextView
    lateinit var btnVisible: ImageView
    lateinit var btnVisibleOff: ImageView
    lateinit var btnMore: ImageView
    lateinit var listHistoryTransaction: ListView
    lateinit var emptyState: LinearLayout
    lateinit var progressDialog: ProgressDialog
    var saldo: String = "0"
    var name: String = ""

    val finpayTheme: FinpayTheme? by lazy { if(intent.getSerializableExtra("theme") == null) null else intent.getSerializableExtra("theme") as FinpayTheme }
    val transNumber: String? by lazy { if(intent.getStringExtra("transNumber") == null) "" else intent.getStringExtra("transNumber")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_sdk)
        supportActionBar!!.hide()

        //initialize
        txtUsername = findViewById(R.id.txtUsername)
        txtSaldo = findViewById(R.id.txtSaldo)
        btnTopup = findViewById(R.id.btnTopup)
        btnTransfer = findViewById(R.id.btnTransfer)
        btnHistoryTransaction = findViewById(R.id.btnHistoryTransaction)
        btnWallet = findViewById(R.id.btnWallet)
        sectionUpgradeAccount = findViewById(R.id.sectionUpgradeAccount)
        txtSeeAll = findViewById(R.id.txtSeeAll)
        btnMore = findViewById(R.id.btnMore)
        btnVisible = findViewById(R.id.icon_visibility)
        btnVisibleOff = findViewById(R.id.icon_visibility_off)
        btnVisible.visibility = View.GONE
        btnVisibleOff.visibility = View.VISIBLE
        listHistoryTransaction = findViewById(R.id.list_history_transaction)
        emptyState = findViewById(R.id.emptyState)
        progressDialog = ProgressDialog(this@WalletSDKActivity)
        listHistoryTransaction.setNestedScrollingEnabled(true)

        txtSeeAll.setTextColor(if(finpayTheme?.getPrimaryColor() == null)  Color.parseColor("#FFFFFF") else finpayTheme?.getPrimaryColor()!!)

        FinpaySDK.init(this)
        checkProfile()

        btnVisibleOff.setOnClickListener {
            txtSaldo.text = "*******"
            btnVisible.visibility = View.VISIBLE
            btnVisibleOff.visibility = View.GONE
        }

        btnVisible.setOnClickListener {
            txtSaldo.text = saldo
            btnVisible.visibility = View.GONE
            btnVisibleOff.visibility = View.VISIBLE
        }

        btnTopup.setOnClickListener{
            val intent = Intent(this, TopupActivity::class.java)
            this.startActivity(intent)
        }

        btnTransfer.setOnClickListener{
            val intent = Intent(this, TransferActivity::class.java)
            this.startActivity(intent)
        }

        txtSeeAll.setOnClickListener {
            FinpaySDKUI.historyTransactionUIBuilder(java.util.UUID.randomUUID().toString(), this@WalletSDKActivity, Credential.credential(this@WalletSDKActivity))
        }

        val showPopUp = PopupMenu(
            this,
            btnMore
        )

        showPopUp.menu.add(Menu.NONE, 0, 0, "Putuskan Koneksi")
        showPopUp.menu.add(Menu.NONE, 1, 1, "Lihat Profil QR")

        showPopUp.setOnMenuItemClickListener { menuItem ->
            val  id = menuItem.itemId
            if (id==0){
                FinpaySDKUI.logout(this, {
                    this@WalletSDKActivity.finish()
                })
            }
            else if(id==1){
                openQrDialog()
            }
            false
        }

        btnMore.setOnClickListener {
            showPopUp.show()
        }

        sectionUpgradeAccount.setOnClickListener {
            val intent = Intent(this, UpgradeAccountActivity::class.java)
            this.startActivity(intent)
        }
    }

    fun checkProfile() {
        progressDialog.setTitle("Mohon Menunggu")
        progressDialog.setMessage("Sedang Memuat ...")
        progressDialog.setCancelable(false) // blocks UI interaction
        progressDialog.show()
        FinpaySDK.checkProfile("1234567890", this, {
            saldo = TextUtils.formatRupiah(it.data!!.balance!!.toDouble())
            txtSaldo.text = TextUtils.formatRupiah(it.data!!.balance!!.toDouble())
            txtUsername.text = it.data!!.nama
            name = it.data!!.nama!!+" - "+FinpaySDK.prefHelper.getStringFromShared(SharedPrefKeys.USER_PHONE_NUMBER)!!
            if(it.data!!.tipeCust == "03") {
                sectionUpgradeAccount.visibility = View.GONE
            }
            if(it.data!!.history.isEmpty() || it.data!!.history.count() == 0) {
                listHistoryTransaction.visibility = View.GONE
                emptyState.visibility = View.VISIBLE
            } else {
                listHistoryTransaction.visibility = View.VISIBLE
                emptyState.visibility = View.GONE
            }
            listHistoryTransaction.adapter = WalletHistoryAdapter(this, R.layout.item_history_transaction, it.data!!.history)
            progressDialog.dismiss()
        }, {
            progressDialog.dismiss()
            DialogUtils.showDialogError(this@WalletSDKActivity, "", it)
        })
    }

    private fun openQrDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_share_qr)

        val txtName = dialog.findViewById<TextView>(R.id.txtName)
        val qrImage = dialog.findViewById<ImageView>(R.id.qr_image)
        qrImage.setImageBitmap(getQrCodeBitmap())
        txtName.text = name

        val btnShare = dialog.findViewById<Button>(R.id.btn_share_qr)
        btnShare.setOnClickListener {
            val mDrawable: Drawable = qrImage.drawable
            val mBitmap = (mDrawable as BitmapDrawable).bitmap
            val path = MediaStore.Images.Media.insertImage(
                this.contentResolver,
                getQrCodeBitmap(),
                "Finpay ${name}",
                null
            )
            val uri = Uri.parse(path)
            val share = Intent(Intent.ACTION_SEND)
            share.type = "image/*"
            share.putExtra(Intent.EXTRA_STREAM, uri)
            startActivity(Intent.createChooser(share, "Bagikan QR kode"))
        }
        dialog.show()
    }

    fun getQrCodeBitmap(): Bitmap {
        val size = 512
        val qrCodeContent = "FINPAY:${name};"
        val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 }
        val bits = QRCodeWriter().encode(qrCodeContent, BarcodeFormat.QR_CODE, size, size)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }
}