package lib.finpay.sdk.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import lib.finpay.sdk.databinding.ViewMovieItemBinding
import lib.finpay.sdk.presentation.base.BaseViewHolder
import lib.finpay.sdk.presentation.model.Movie

class MovieHolder(private val binding: ViewMovieItemBinding) :
    BaseViewHolder<Movie>(binding.root) {

    companion object {
        fun create(parent: ViewGroup): BaseViewHolder<Movie> {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ViewMovieItemBinding.inflate(layoutInflater, parent, false)
            return MovieHolder(binding)
        }
    }

    override fun bind(model: Movie) {
        binding.data = model
        binding.executePendingBindings()
    }
}