package com.finpay.wallet.view.upgrade_acc

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.finpay.wallet.R
import com.finpay.wallet.databinding.FragmentStepperFirstBinding
import com.finpay.wallet.view.camera.CameraActivity

class StepperFirstFragment : Fragment() {
    private var _binding: FragmentStepperFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStepperFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.btnContinue.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_firstStepFragment_to_secondStepFragment))
        binding.btnContinue.setOnClickListener{
            val mIntent = Intent(requireActivity(), CameraActivity::class.java)
            startActivity(mIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}