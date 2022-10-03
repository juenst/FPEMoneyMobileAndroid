package com.finpay.wallet.view.app.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.finpay.wallet.R
import com.finpay.wallet.databinding.FragmentNotificationsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.FinPaySDK

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var txtUserName: TextView
    private lateinit var txtPhoneNumber: TextView
    private lateinit var txtStatus: TextView
    private lateinit var rlPhoto: RelativeLayout
    private lateinit var finPaySDK: FinPaySDK

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        finPaySDK = FinPaySDK()
        val notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        txtUserName = binding.txtUsername
        txtPhoneNumber = binding.txtPhoneNumber
        txtStatus = binding.txtStatus
        rlPhoto = binding.rlPhoto
        getBalance()

        rlPhoto.setOnClickListener {
            showDialogProfile()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
                txtPhoneNumber.text = "6283815613839"
                txtUserName.text = userBalanceModel.getCustName()
                var custType: String = "STANDAR"
                if(userBalanceModel.getCustType().toString() == "UNREGISTER") {
                    custType = "STANDAR"
                }else{
                    "PREMIUM"
                }
                txtStatus.text = custType
            }
        )
    }

    fun showDialogProfile() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.bottom_dialog_profile)
        val btnChangePhoto = dialog.findViewById<RelativeLayout>(R.id.rlChangePhoto)
        val btnQR = dialog.findViewById<RelativeLayout>(R.id.rlQR)

        btnChangePhoto?.setOnClickListener {

        }
        btnQR?.setOnClickListener {

        }
        dialog.show()
    }
}