package com.finpay.wallet.view.home

//import com.midtrans.sdk.uikit.SdkUIFlowBuilder
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
import com.finpay.wallet.databinding.FragmentHomeBinding
import com.finpay.wallet.utilities.TextUtils
import com.finpay.wallet.view.home.banner.item.BannerListener
import com.finpay.wallet.view.home.banner.model.BannerPromo
import com.finpay.wallet.view.topup.TopupActivity
import com.finpay.wallet.view.transaction.history.TransactionHistoryActivity
import com.finpay.wallet.view.transfer.TransferActivity
import com.finpay.wallet.view.wallet.WalletActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import lib.finpay.sdk.FinPaySDK
import lib.finpay.sdk.model.Credential
import java.text.NumberFormat
import java.util.*


class HomeFragment : Fragment(), View.OnClickListener, BannerListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var root: View
    private lateinit var scrollView: NestedScrollView
    private lateinit var txtUserName: TextView
    private lateinit var txtSaldo: TextView
    private lateinit var tvWarningBody: TextView
    private lateinit var crdWarning: LinearLayout
    private lateinit var finPaySDK: FinPaySDK

    private lateinit var btnTopUp: LinearLayout
    private lateinit var btnTransfer: LinearLayout
    private lateinit var btnHistoryTransaction: LinearLayout
    private lateinit var btnWallet: LinearLayout

    private lateinit var iconVisibility: ImageView
    private lateinit var iconVisibilityOff: ImageView
    var saldo: String = "Rp0"

    private var groupAdapter = GroupAdapter<ViewHolder>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        finPaySDK = FinPaySDK()

        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        root = binding.root
        scrollView = binding.scrollView
        txtUserName = binding.txtUsername
        txtSaldo = binding.txtSaldo
        tvWarningBody = binding.tvBeFinpayPremiumBody
        crdWarning = binding.crdWarningPremium
        iconVisibility = binding.iconVisibility
        iconVisibilityOff = binding.iconVisibilityOff


        btnTopUp = binding.btnTopup
        btnTransfer = binding.btnTransfer
        btnHistoryTransaction = binding.btnHistoryTransaction
        btnWallet = binding.btnWallet

        txtSaldo.text = saldo
        onCreateFragmentUI()
        getBalance()

        iconVisibility.visibility = View.VISIBLE
        iconVisibilityOff.visibility = View.GONE

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

    fun credential(): Credential {
        val cd = Credential()
        cd.setUsername("")
        cd.setPassword("")
        cd.setSecretKey("")
        return cd
    }

    fun getBalance() {
        FinPaySDK().getUserBallance(requireContext(), "083815613839", {
            saldo = TextUtils().formatRupiah(it.getCustBalance()!!.toDouble())
            txtSaldo.text = TextUtils().formatRupiah(it.getCustBalance()!!.toDouble())
        },{
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG)
        })
    }

    private fun onCreateFragmentUI() {
        crdWarning.setOnClickListener(this)
        setSpanText(
            64,
            87, Color.BLACK,
            "Dapatkan banyak keuntungan dengan menjadi Finpay Money Premium. Lihat Info Selengkapnya",
            tvWarningBody
        )

        iconVisibility.setOnClickListener{
            iconVisibility.visibility = View.GONE
            iconVisibilityOff.visibility = View.VISIBLE
        }

        iconVisibilityOff.setOnClickListener{
            iconVisibility.visibility = View.VISIBLE
            iconVisibilityOff.visibility = View.GONE
        }

        btnTopUp.setOnClickListener{
            //FinPaySDK().openTopUp(requireContext())
            val intent = Intent(requireContext(), TopupActivity::class.java)
            this.startActivity(intent)
        }

        btnTransfer.setOnClickListener{
            //FinPaySDK().openHistoryTransaction(requireContext())
            val intent = Intent(requireContext(), TransferActivity::class.java)
            this.startActivity(intent)
        }

        btnWallet.setOnClickListener{
            //FinPaySDK().openWallet(requireContext())
            val intent = Intent(requireContext(), WalletActivity::class.java)
            this.startActivity(intent)
        }

        btnHistoryTransaction.setOnClickListener{
            //FinPaySDK().openHistoryTransaction(requireContext())
            val intent = Intent(requireContext(), TransactionHistoryActivity::class.java)
            this.startActivity(intent)
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

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    override fun onSeeAllPromoClick() {
        TODO("Not yet implemented")
    }

//    override fun onClick(v: View?) {
//        when (v?.id) {
//            R.id.crd_warning_premium -> {
//                val moveToPremiumInfo =
//                    Intent(view?.context, UpgradeInformationActivity::class.java)
//                startActivity(moveToPremiumInfo)
//            }
//        }
//    }


    override fun onBannerClick(promo: BannerPromo) {
    }
}
