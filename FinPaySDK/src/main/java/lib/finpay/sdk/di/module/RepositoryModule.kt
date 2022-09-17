//package lib.finpay.sdk.di.module
//
//import lib.finpay.sdk.data.local.AppDatabase
//import lib.finpay.sdk.data.remote.api.MovieAPI
//import lib.finpay.sdk.data.repository.MovieRepositoryImpl
//import lib.finpay.sdk.di.scope.ApplicationScope
//import lib.finpay.sdk.domain.repository.MovieRepository
//import dagger.Module
//import dagger.Provides
//
//@Module
//class RepositoryModule {
//
//    @Provides
//    @ApplicationScope
//    fun provideMovieRepository(service: MovieAPI, database: AppDatabase): MovieRepository {
//        return MovieRepositoryImpl(service, database)
//    }
//}
