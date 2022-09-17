//package lib.finpay.sdk.presentation.dataSource
//
//import androidx.lifecycle.MutableLiveData
//import androidx.paging.DataSource
//import lib.finpay.sdk.domain.repository.MovieRepository
//import lib.finpay.sdk.domain.utils.SchedulerProvider
//import lib.finpay.sdk.presentation.model.Movie
//import io.reactivex.disposables.CompositeDisposable
//
//class MovieDataFactory(
//    private val repository: MovieRepository,
//    private val compositeDisposable: CompositeDisposable,
//    private val scheduler: SchedulerProvider
//) : DataSource.Factory<Int, Movie>() {
//
//    private lateinit var _dataSource: MovieDataSource
//
//    val movieLiveData = MutableLiveData<MovieDataSource>()
//
//    override fun create(): DataSource<Int, Movie> {
//        _dataSource = MovieDataSource(repository, compositeDisposable, scheduler)
//        movieLiveData.postValue(_dataSource)
//        return _dataSource
//    }
//}
