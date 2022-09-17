//package lib.finpay.sdk.di.module
//
//import android.app.Application
//import androidx.room.Room
//import lib.finpay.sdk.data.local.AppDatabase
//import lib.finpay.sdk.data.local.dao.MovieDao
//import lib.finpay.sdk.di.scope.ApplicationScope
//import dagger.Module
//import dagger.Provides
//
//@Module
//class DatabaseModule {
//
//    @Provides
//    @ApplicationScope
//    fun provideDatabase(app: Application): AppDatabase {
//        return Room.databaseBuilder(app, AppDatabase::class.java, AppDatabase.DB_NAME)
//            .fallbackToDestructiveMigration() // db will cleared when upgrade version
//            .build()
//    }
//
//    @Provides
//    @ApplicationScope
//    fun provideMovieDao(database: AppDatabase): MovieDao {
//        return database.movieDao()
//    }
//}