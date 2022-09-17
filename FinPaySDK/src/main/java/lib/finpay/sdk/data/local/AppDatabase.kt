//package lib.finpay.sdk.data.local
//
//import androidx.room.Database
//import androidx.room.RoomDatabase
//import lib.finpay.sdk.data.local.AppDatabase.Companion.DB_VERSION
//import lib.finpay.sdk.data.local.dao.MovieDao
//import lib.finpay.sdk.data.local.entity.MovieEntity
//
//@Database(entities = [MovieEntity::class], version = DB_VERSION, exportSchema = false)
//abstract class AppDatabase : RoomDatabase() {
//    companion object {
//        const val DB_NAME = "finpay.db"
//        const val DB_VERSION = 1
//    }
//
//    abstract fun movieDao(): MovieDao
//}
