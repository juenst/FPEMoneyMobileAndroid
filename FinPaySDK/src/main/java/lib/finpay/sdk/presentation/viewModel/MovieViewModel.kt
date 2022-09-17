package lib.finpay.sdk.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import lib.finpay.sdk.domain.repository.MovieRepository
import lib.finpay.sdk.domain.utils.SchedulerProvider
import lib.finpay.sdk.presentation.base.BaseViewModel
import lib.finpay.sdk.presentation.common.ResultState
import lib.finpay.sdk.presentation.dataSource.MovieDataFactory
import lib.finpay.sdk.presentation.dataSource.MovieDataSource
import lib.finpay.sdk.presentation.model.Movie
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val schedulers: SchedulerProvider
) : BaseViewModel() {

    private val _count = 20

    lateinit var movieList:  LiveData<PagedList<Movie>>

    private val _movieFactory = MovieDataFactory(repository, compositeDisposable, schedulers)

    private val config = PagedList.Config.Builder()
        .setPageSize(_count)
        .setInitialLoadSizeHint(_count * 2)
        .setEnablePlaceholders(false)
        .build()

    fun fetchMovie() {
        movieList = LivePagedListBuilder(_movieFactory, config).build()
    }

    fun getState(): LiveData<ResultState<List<Movie>>> = Transformations.switchMap<MovieDataSource,
            ResultState<List<Movie>>>(_movieFactory.movieLiveData, MovieDataSource::state)

    fun listIsEmpty(): Boolean = movieList.value?.isEmpty() ?: true

    fun retry() {
        _movieFactory.movieLiveData.value?.retry()
    }

    fun refresh() {
        _movieFactory.movieLiveData.value?.invalidate()
    }
}
