package com.finpay.wallet.view.upgrade_acc

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.finpay.wallet.databinding.FragmentStepperFirstBinding
import com.finpay.wallet.view.camera.CameraActivity
import com.finpay.wallet.view.camera.CameraResultActivity

class StepperFirstFragment : Fragment() {
    private var _binding: FragmentStepperFirstBinding? = null
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
            result.data?.getStringExtra(CameraResultActivity.EXTRA_RESULT)?.let {
                callback?.onFirstFr(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStepperFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnContinue.setOnClickListener {
            val intent = Intent(context, CameraActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}