package lib.finpay.sdk.presentation.dataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import lib.finpay.sdk.data.remote.StatusCode
import lib.finpay.sdk.domain.exception.Failure
import lib.finpay.sdk.domain.repository.MovieRepository
import lib.finpay.sdk.domain.utils.SchedulerProvider
import lib.finpay.sdk.presentation.common.ResultState
import lib.finpay.sdk.presentation.model.Movie
import lib.finpay.sdk.utils.AppLogger
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action

class MovieDataSource(
    private val repository: MovieRepository,
    private val compositeDisposable: CompositeDisposable,
    private val scheduler: SchedulerProvider
) : PageKeyedDataSource<Int, Movie>() {

    private val resultState = MutableLiveData<ResultState<List<Movie>>>()

    val state: LiveData<ResultState<List<Movie>>> get() = resultState

    private var retryCompletable: Completable? = null

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        updateState(ResultState.OnLoading())
        compositeDisposable.add(
            repository.fetchMovie(1)
                .subscribe({
                    updateState(ResultState.OnSuccess(it))
                    callback.onResult(it, null, 2)
                }, {
                    handleNetworkError(mapToFailure(it), Action { loadInitial(params, callback) })
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        if (isRequestInProgress) return
        isRequestInProgress = true
        updateState(ResultState.OnLoading())
        compositeDisposable.add(
            repository.fetchMovie(params.key)
                .subscribe({
                    updateState(ResultState.OnSuccess(it))
                    callback.onResult(it, params.key + 1)
                    isRequestInProgress = false
                }, {
                    isRequestInProgress = false
                    val error = mapToFailure(it)
                    if (error.code == StatusCode.NO_CONTENT) {
                        updateState(ResultState.OnSuccess(emptyList()))
                    } else {
                        handleNetworkError(mapToFailure(it), Action { loadAfter(params, callback) })
                    }
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        AppLogger.d("Hdk load movie: LoadBefore")
    }

    private fun updateState(state: ResultState<List<Movie>>) {
        this.resultState.postValue(state)
    }

    private fun mapToFailure(throwable: Throwable): Failure {
        return if (throwable is Failure) throwable
        else Failure(StatusCode.UNKNOWN_ERROR, "There is unknown error")
    }

    private fun handleNetworkError(error: Failure, action: Action?) {
        updateState(ResultState.OnError(error))
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(scheduler.io())
                    .observeOn(scheduler.ui())
                    .subscribe()
            )
        }
    }
}
