package lib.finpay.sdk.domain.repository

import lib.finpay.sdk.presentation.model.Movie
import io.reactivex.Single

interface MovieRepository {

    fun fetchMovie(page: Int): Single<List<Movie>>
}
