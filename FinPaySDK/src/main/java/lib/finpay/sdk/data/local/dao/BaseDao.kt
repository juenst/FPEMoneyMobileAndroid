//package lib.finpay.sdk.data.local.dao
//
//import androidx.room.*
//
//@Dao
//interface BaseDao<T>  {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertList(data: List<T>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(data: T)
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    fun updateList(data: List<T>)
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    fun update(data: T)
//
//    @Delete
//    fun delete(data: T)
//}