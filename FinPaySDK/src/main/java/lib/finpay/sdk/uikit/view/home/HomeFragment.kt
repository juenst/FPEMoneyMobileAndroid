package lib.finpay.sdk.uikit.view.home

//import com.midtrans.sdk.uikit.SdkUIFlowBuilder
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.databinding.FragmentHomeBinding
import lib.finpay.sdk.uikit.FinpaySDKUI
import lib.finpay.sdk.uikit.constant.Credential
import lib.finpay.sdk.uikit.utilities.DialogUtils
import lib.finpay.sdk.uikit.utilities.TextUtils
import lib.finpay.sdk.uikit.view.more.MoreActivity
import lib.finpay.sdk.uikit.view.ppob.pulsa_data.PulsaDataActivity
import lib.finpay.sdk.uikit.view.topup.TopupActivity
import lib.finpay.sdk.uikit.view.transfer.TransferActivity
import lib.finpay.sdk.uikit.view.upgrade.UpgradeAccountActivity
import lib.finpay.sdk.uikit.view.wallet.WalletActivity


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var root: View
    private lateinit var scrollView: NestedScrollView
    private lateinit var txtUserName: TextView
    private lateinit var txtSaldo: TextView
    private lateinit var tvWarningBody: TextView
    private lateinit var sectionUpgradeAccount: LinearLayout
    private lateinit var finPaySDK: FinpaySDK
    lateinit var progressDialog: ProgressDialog

    private lateinit var btnTopUp: LinearLayout
    private lateinit var btnTransfer: LinearLayout
    private lateinit var btnHistoryTransaction: LinearLayout
    private lateinit var btnWallet: LinearLayout

    private lateinit var iconVisibility: ImageView
    private lateinit var iconVisibilityOff: ImageView


    private lateinit var btnPulsaData: LinearLayout
    private lateinit var btnMore: LinearLayout

    var saldo: String = "Rp0"

    private var groupAdapter = GroupAdapter<ViewHolder>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        finPaySDK = FinpaySDK()

        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        root = binding.root
        scrollView = binding.scrollView
        txtUserName = binding.txtUsername
        txtSaldo = binding.txtSaldo
        tvWarningBody = binding.tvBeFinpayPremiumBody
        sectionUpgradeAccount = binding.sectionUpgradeAccount
        iconVisibility = binding.iconVisibility
        iconVisibilityOff = binding.iconVisibilityOff
        progressDialog = ProgressDialog(requireContext())


        btnTopUp = binding.btnTopup
        btnTransfer = binding.btnTransfer
        btnHistoryTransaction = binding.btnHistoryTransaction
        btnWallet = binding.btnWallet

        btnPulsaData = binding.btnPulsaData
        btnMore = binding.btnMore

        txtSaldo.text = saldo
        onCreateFragmentUI()
        getBalance()

        val imageSlider: ArrayList<SlideModel> = arrayListOf(
            SlideModel(
                "https://demos.finpay.id/finpay/Intagram promo_ liburan sekolah_200622121303.jpg",
                ScaleTypes.FIT
            ),
            SlideModel(
                "https://demos.finpay.id/finpay/Promo Follow_111019033312.png",
                ScaleTypes.FIT
            ),
            SlideModel(
                "https://demos.finpay.id/finpay/Banner promo Finpay ID-02_180820083917_Helpdesk_RZ_140621025709.png",
                ScaleTypes.FIT
            )
        )
        binding.imageSlider.setImageList(imageSlider, ScaleTypes.FIT)

        iconVisibility.visibility = View.GONE
        iconVisibilityOff.visibility = View.VISIBLE

        iconVisibilityOff.setOnClickListener {
            txtSaldo.text = "*******"
            iconVisibility.visibility = View.VISIBLE
            iconVisibilityOff.visibility = View.GONE
        }

        iconVisibility.setOnClickListener {
            txtSaldo.text = saldo
            iconVisibility.visibility = View.GONE
            iconVisibilityOff.visibility = View.VISIBLE
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getBalance() {
        progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Mohon Menunggu")
        progressDialog.setMessage("Sedang Memuat ...")
        progressDialog.setCancelable(false)
        progressDialog.show()
        FinpaySDK.getUserBallance(requireContext(), {
            saldo = TextUtils.formatRupiah(it.amount!!.toDouble())
            txtSaldo.text = TextUtils.formatRupiah(it.amount!!.toDouble())
            progressDialog.dismiss()
        }, {
            DialogUtils.showDialogError(requireContext(), "", it)
            progressDialog.dismiss()
        })
    }

    private fun onCreateFragmentUI() {
        //crdWarning.setOnClickListener(this)
        setSpanText(
            64,
            87, Color.BLACK,
            "Dapatkan banyak keuntungan dengan menjadi Finpay Money Premium. Lihat Info Selengkapnya",
            tvWarningBody
        )

        iconVisibility.setOnClickListener {
            iconVisibility.visibility = View.GONE
            iconVisibilityOff.visibility = View.VISIBLE
        }

        iconVisibilityOff.setOnClickListener {
            iconVisibility.visibility = View.VISIBLE
            iconVisibilityOff.visibility = View.GONE
        }

        btnTopUp.setOnClickListener {
            //FinPaySDK().openTopUp(requireContext())
            val intent = Intent(requireContext(), TopupActivity::class.java)
            this.startActivity(intent)
        }

        btnTransfer.setOnClickListener {
            //FinPaySDK().openHistoryTransaction(requireContext())
            val intent = Intent(requireContext(), TransferActivity::class.java)
            this.startActivity(intent)
        }

        btnWallet.setOnClickListener {
            FinpaySDKUI.openWallet(requireContext(), Credential.credential(requireContext()))
        }

        btnHistoryTransaction.setOnClickListener {
            FinpaySDKUI.openHistoryTransaction(requireContext(), Credential.credential(requireContext()))
        }

        btnMore.setOnClickListener {
            //FinPaySDK().openMore(requireContext())
            val intent = Intent(requireContext(), MoreActivity::class.java)
            this.startActivity(intent)
        }

        btnPulsaData.setOnClickListener {
            //FinPaySDK().openMore(requireContext())
            val intent = Intent(requireContext(), PulsaDataActivity::class.java)
            this.startActivity(intent)
        }

        sectionUpgradeAccount.setOnClickListener {
            FinpaySDKUI.openUpgradeAccount(requireContext(), Credential.credential(requireContext()))
        }
    }

    private fun setSpanText(start: Int, end: Int, color: Int, word: String, textView: TextView) {
        val spannable = SpannableStringBuilder(word)
        spannable.setSpan(
            ForegroundColorSpan(color),
            start, // start
            end, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        textView.text = spannable
    }


}
