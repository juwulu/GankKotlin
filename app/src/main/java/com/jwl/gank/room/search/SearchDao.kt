package com.jwl.gank.room.search

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * author:  lujunwu
 * date:    2018/12/14 16:26
 * desc:    NoDiscription
 */

@Dao
interface SearchDao{

    @Query("SELECT * FROM search limit 20")
    fun getAll():List<Search>

    @Insert
    fun insert(search:Search)

    @Query("DELETE FROM search")
    fun deleteAll()

    @Delete
    fun delete(vararg search: Search)


}