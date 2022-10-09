package com.finpay.wallet.view.upgrade_acc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.finpay.wallet.databinding.FragmentStepperThirdBinding

class StepperThirdFragment : Fragment() {
    private var _binding: FragmentStepperThirdBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val EXTRA_FIRST_DATA = "extra_first_data"
        const val EXTRA_SECOND_DATA = "extra_second_data"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStepperThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataFromFirst = arguments?.getString(EXTRA_FIRST_DATA)
        val dataFromSecond = arguments?.getString(EXTRA_SECOND_DATA)

        binding.tvWelcomeFragment1.text = "State 3 Data From First Fragment= $dataFromFirst"
        binding.tvWelcomeFragment2.text = "State 3 Data From Camera= $dataFromSecond"
        binding.btnFinish.setOnClickListener {
            Toast.makeText(context, "Finish Data and Hit API", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}