//package lib.finpay.sdk.ui.binding
//
//import android.widget.ImageView
//import androidx.databinding.BindingAdapter
//import com.bumptech.glide.Glide
//import lib.finpay.sdk.BuildConfig
//import lib.finpay.sdk.presentation.common.LoadingState
//import lib.finpay.sdk.ui.widget.LoadingView
//
//@BindingAdapter("imagePoster")
//fun bindImage(imgView: ImageView, url: String) {
//    Glide.with(imgView.context)
//        .load(BuildConfig.BASE_IMAGE + url)
//        .into(imgView)
//}
//
//@BindingAdapter("loadingState")
//fun bindLoadingState(loadingView: LoadingView, state: LoadingState) {
//   loadingView.setState(state)
//}