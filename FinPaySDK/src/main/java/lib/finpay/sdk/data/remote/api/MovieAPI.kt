package lib.finpay.sdk.data.remote.api

import lib.finpay.sdk.data.remote.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("discover/movie")
    fun getMovies(
        @Query("sort_by") sortBy: String = "release_date.desc",
        @Query("page") page: Int = 1
    ): Single<MovieResponse>
}
