package lib.finpay.sdk.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import lib.finpay.sdk.databinding.ViewFooterItemBinding
import lib.finpay.sdk.presentation.base.BaseViewHolder
import lib.finpay.sdk.presentation.common.LoadingState

class FooterHolder(private val binding: ViewFooterItemBinding) :
    BaseViewHolder<LoadingState>(binding.root) {

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): BaseViewHolder<LoadingState> {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ViewFooterItemBinding.inflate(layoutInflater, parent, false)
            binding.footerError.setOnClickListener {
                binding.showLoading = true
                binding.showError = false
                retry.invoke()
            }
            return FooterHolder(binding)
        }
    }

    override fun bind(model: LoadingState) {
        binding.showLoading = model is LoadingState.OnLoading
        binding.showError = model is LoadingState.OnError
        binding.executePendingBindings()
    }
}
