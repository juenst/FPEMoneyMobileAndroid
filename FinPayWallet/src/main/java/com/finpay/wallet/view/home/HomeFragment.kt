package com.finpay.wallet.view.home

import android.R
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
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.finpay.wallet.databinding.FragmentHomeBinding
import com.finpay.wallet.view.home.banner.item.BannerListener
import com.finpay.wallet.view.home.banner.model.BannerPromo
//import com.midtrans.sdk.uikit.SdkUIFlowBuilder
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import lib.finpay.sdk.FinPaySDK
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

    private lateinit var iconVisibility: ImageView
    private lateinit var iconVisibilityOff: ImageView

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
        onCreateFragmentUI()
        //getBalance()

        iconVisibility.visibility = View.VISIBLE
        iconVisibilityOff.visibility = View.GONE

        val promos = listOf(
            BannerPromo(name = "Puncak badai uang",
                image = "https://s2.bukalapak.com/uploads/promo_partnerinfo_bloggy/2842/Bloggy_1_puncak.jpg"),
            BannerPromo(
                name = "hati hati ada guncangan badai uang",
                image = "https://s4.bukalapak.com/uploads/promo_partnerinfo_bloggy/5042/Bloggy_1.jpg"
            ),
            BannerPromo(name = "Puncak badai uang",
                image = "https://s2.bukalapak.com/uploads/promo_partnerinfo_bloggy/2842/Bloggy_1_puncak.jpg"),
            BannerPromo(
                name = "hati hati ada guncangan badai uang",
                image = "https://s4.bukalapak.com/uploads/promo_partnerinfo_bloggy/5042/Bloggy_1.jpg"
            ),
            BannerPromo(name = "Puncak badai uang",
                image = "https://s2.bukalapak.com/uploads/promo_partnerinfo_bloggy/2842/Bloggy_1_puncak.jpg"),
            BannerPromo(
                name = "hati hati ada guncangan badai uang",
                image = "https://s4.bukalapak.com/uploads/promo_partnerinfo_bloggy/5042/Bloggy_1.jpg"
            )
        )

        // declare banner carousel
//        val bannerCarouselItem = BannerCarouselItem(promos, supportFragmentManager, this)
//        groupAdapter.add(bannerCarouselItem)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun makePayment() {
//        SdkUIFlowBuilder.init()
//    }

    fun formatRupiah(number: Double): String{
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }

//    private fun getBalance() {
//        val userName = "MT77764DKM83N"
//        val password = "YJV3AM0y"
//        val secretKey = "daYumnMb"
//        finPaySDK.getBalance(
//            userName,
//            password,
//            secretKey,
//            "TRX1234567890",
//            "083815613839",
//            onSuccess = { userBalanceModel ->
//                txtSaldo.text = formatRupiah(userBalanceModel.getCustBalance()!!.toDouble())
//                txtUserName.text = userBalanceModel.getCustName()!!.split(" ").toTypedArray().first().toString().toUpperCase()
//            }
//        )
//    }

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
