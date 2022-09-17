package lib.finpay.sdk.data.repository

import lib.finpay.sdk.data.local.AppDatabase
import lib.finpay.sdk.data.mapper.MovieMapper
import lib.finpay.sdk.data.remote.api.MovieAPI
import lib.finpay.sdk.data.remote.base.ErrorNetworkHandler
import lib.finpay.sdk.domain.repository.MovieRepository
import lib.finpay.sdk.presentation.model.Movie
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieAPI,
    private val database: AppDatabase
) : MovieRepository {

    override fun fetchMovie(page: Int): Single<List<Movie>> {
        return service.getMovies(page = page)
            .compose(ErrorNetworkHandler())
            .map { MovieMapper.transformTo(it.results) }
    }
}
