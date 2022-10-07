package com.finpay.wallet.view.upgrade_acc

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.finpay.wallet.R
import com.finpay.wallet.databinding.FragmentStepperThirdBinding

class StepperThirdFragment : Fragment() {
    private var _binding: FragmentStepperThirdBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStepperThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFinish.setOnClickListener {
            Toast.makeText(context, "Finish Data and Hit API", Toast.LENGTH_SHORT).show()
//            val intent = Intent(activity, HomeActivity::class.java)
//            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}