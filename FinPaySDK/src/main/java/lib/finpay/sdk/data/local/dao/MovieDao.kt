//package lib.finpay.sdk.data.local.dao
//
//import androidx.room.Dao
//import androidx.room.Query
//import lib.finpay.sdk.data.local.entity.MovieEntity
//import io.reactivex.Single
//
//@Dao
//interface MovieDao : BaseDao<MovieEntity>{
//
//    @Query("SELECT * FROM MovieTable")
//    fun getAll(): Single<List<MovieEntity>>
//
//    @Query("SELECT * FROM MovieTable WHERE title LIKE '%' || :keyword || '%' ORDER BY id DESC")
//    fun getByKeyword(keyword: String): Single<List<MovieEntity>>
//
//    @Query("DELETE FROM MovieTable")
//    fun truncate()
//}