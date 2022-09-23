package com.finpay.wallet.view.app.ui.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.finpay.wallet.R
import com.finpay.wallet.databinding.FragmentHomeBinding
import com.finpay.wallet.view.upgrade_acc.UpgradeInformationActivity
import lib.finpay.sdk.FinPaySDK
import java.text.NumberFormat
import java.util.*

class HomeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var root: View
    private lateinit var scrollView: NestedScrollView
    private lateinit var txtUserName: TextView
    private lateinit var txtSaldo: TextView
    private lateinit var tvWarningBody: TextView
    private lateinit var crdWarning: CardView
    private lateinit var finPaySDK: FinPaySDK

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
        onCreateFragmentUI()
        getBalance()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun formatRupiah(number: Double): String{
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }

    private fun getBalance() {
        val userName = "MT77764DKM83N"
        val password = "YJV3AM0y"
        val secretKey = "daYumnMb"
        finPaySDK.getBalance(
            userName,
            password,
            secretKey,
            "TRX1234567890",
            "083815613839",
            onSuccess = { userBalanceModel ->
                txtSaldo.text = formatRupiah(userBalanceModel.getCustBalance()!!.toDouble())
                txtUserName.text = userBalanceModel.getCustName()!!.split(" ").toTypedArray().first().toString().toUpperCase()
            }
        )
    }

    private fun onCreateFragmentUI() {
        crdWarning.setOnClickListener(this)
        setSpanText(
            64,
            87, Color.BLACK,
            "Dapatkan banyak keuntungan dengan menjadi Finpay Money Premium. Lihat Info Selengkapnya",
            tvWarningBody
        )
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.crd_warning_premium -> {
                val moveToPremiumInfo =
                    Intent(activity, UpgradeInformationActivity::class.java)
                startActivity(moveToPremiumInfo)
            }
        }
    }


}
