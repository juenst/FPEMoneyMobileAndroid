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
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.finpay.wallet.databinding.FragmentHomeBinding
import com.finpay.wallet.utilities.TextUtils
import com.finpay.wallet.view.topup.TopupActivity
import com.finpay.wallet.view.transaction.history.TransactionHistoryActivity
import com.finpay.wallet.view.transfer.TransferActivity
import com.finpay.wallet.view.wallet.WalletActivity
import lib.finpay.sdk.FinPaySDK
import lib.finpay.sdk.model.Credential


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var finPaySDK: FinPaySDK

    var saldo: String = "Rp0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        finPaySDK = FinPaySDK()
        onCreateFragmentUI()
        getBalance()

        val imageSlider: ArrayList<SlideModel> = arrayListOf(
            SlideModel(
                "https://c4.wallpaperflare.com/wallpaper/816/451/655/sphere-art-artwork-1980s-wallpaper-preview.jpg",
                ScaleTypes.FIT
            ),
            SlideModel(
                "https://c4.wallpaperflare.com/wallpaper/816/451/655/sphere-art-artwork-1980s-wallpaper-preview.jpg",
                ScaleTypes.FIT
            ),
            SlideModel(
                "https://c4.wallpaperflare.com/wallpaper/816/451/655/sphere-art-artwork-1980s-wallpaper-preview.jpg",
                ScaleTypes.FIT
            ),
            SlideModel(
                "https://c4.wallpaperflare.com/wallpaper/816/451/655/sphere-art-artwork-1980s-wallpaper-preview.jpg",
                ScaleTypes.FIT
            )
        )
        binding.imageSlider.setImageList(imageSlider, ScaleTypes.FIT)

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

    private fun getBalance() {
        FinPaySDK().getUserBallance(requireContext(), "083815613839", {
            saldo = TextUtils().formatRupiah(it.getCustBalance()!!.toDouble())
            binding.txtSaldo.text = TextUtils().formatRupiah(it.getCustBalance()!!.toDouble())
        }, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG)
        })
    }

    private fun onCreateFragmentUI() {
        setSpanText(
            64,
            87, Color.BLACK,
            "Dapatkan banyak keuntungan dengan menjadi Finpay Money Premium. Lihat Info Selengkapnya",
            binding.tvBeFinpayPremiumBody
        )

        binding.iconVisibility.setOnClickListener {
            binding.iconVisibility.visibility = View.GONE
            binding.iconVisibilityOff.visibility = View.VISIBLE
        }

        binding.iconVisibilityOff.setOnClickListener {
            binding.iconVisibility.visibility = View.VISIBLE
            binding.iconVisibilityOff.visibility = View.GONE
        }

        binding.btnTopup.setOnClickListener {
            val intent = Intent(requireContext(), TopupActivity::class.java)
            this.startActivity(intent)
        }

        binding.btnTransfer.setOnClickListener {
            val intent = Intent(requireContext(), TransferActivity::class.java)
            this.startActivity(intent)
        }

        binding.btnWallet.setOnClickListener {
            val intent = Intent(requireContext(), WalletActivity::class.java)
            this.startActivity(intent)
        }

        binding.btnHistoryTransaction.setOnClickListener {
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
}
