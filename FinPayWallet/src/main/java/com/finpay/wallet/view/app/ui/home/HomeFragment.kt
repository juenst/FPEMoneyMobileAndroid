package com.finpay.wallet.view.app.ui.home

import android.content.Intent
import android.os.Bundle
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
import com.finpay.wallet.view.app.AppActivity
import com.finpay.wallet.view.app.ui.history.HistoryTransactionActivity
import lib.finpay.sdk.FinPaySDK

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val scrollView: NestedScrollView = binding.scrollView
        val txtUserName: TextView = binding.txtUsername
        val txtSaldo: TextView = binding.txtSaldo

        val history: LinearLayout = binding.linearHistory

        val userName: String = "MT77764DKM83N"
        val password: String = "YJV3AM0y"
        val secretKey: String = "daYumnMb"

        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
            txtUserName.text = it
            txtSaldo.text = it
        }

        history.setOnClickListener(){
            val intent = Intent (getActivity(), HistoryTransactionActivity::class.java)
            getActivity()?.startActivity(intent)
        }

        FinPaySDK().getBalance(
            userName,
            password,
            secretKey,
            "TRX1234567890",
            "083815613839",
            onSuccess = {
                    userBallanceModel ->
                txtSaldo.setText(userBallanceModel.getCustBalance())
                txtUserName.setText(userBallanceModel.getCustName())
            }
        )

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}