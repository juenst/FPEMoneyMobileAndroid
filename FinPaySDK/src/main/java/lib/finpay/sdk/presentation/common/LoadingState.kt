package lib.finpay.sdk.presentation.common

sealed class LoadingState {
    object OnLoading : LoadingState()
    object OnFinish : LoadingState()
    data class OnError(val code: Int) : LoadingState()
}