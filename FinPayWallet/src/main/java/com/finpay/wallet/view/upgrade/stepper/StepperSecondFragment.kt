package com.finpay.wallet.view.upgrade.stepper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.finpay.wallet.databinding.FragmentStepperSecondBinding
import com.finpay.wallet.view.camera.SelfieActivity
import com.finpay.wallet.view.camera.SelfieResultActivity
import com.finpay.wallet.view.upgrade.FragmentCallback

class StepperSecondFragment : Fragment() {
    private var _binding: FragmentStepperSecondBinding? = null
    private val binding get() = _binding!!

    private var callback: FragmentCallback? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callback = context as? FragmentCallback
        } catch (_: Exception) {

        }
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.getStringExtra(SelfieResultActivity.EXTRA_RESULT)?.let {
                val uri = arguments?.getString(EXTRA_DATA)
                callback?.onSecondFr(uri, it)
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_result_data"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStepperSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            val intent = Intent(context, SelfieActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}