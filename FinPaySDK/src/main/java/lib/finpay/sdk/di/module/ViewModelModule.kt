package lib.finpay.sdk.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lib.finpay.sdk.di.scope.ApplicationScope
import lib.finpay.sdk.di.scope.ViewModelKey
import lib.finpay.sdk.presentation.ViewModelFactory
import lib.finpay.sdk.presentation.viewModel.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ApplicationScope
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    internal abstract fun providesMovieViewModel(viewModel: MovieViewModel) : ViewModel
}