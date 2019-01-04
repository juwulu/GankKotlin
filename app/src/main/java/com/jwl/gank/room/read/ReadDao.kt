package com.jwl.gank.room.read

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.jwl.gank.room.favorite.Favorite

/**
 * author:  lujunwu
 * date:    2018/12/17 17:21
 * desc:    NoDiscription
 */

@Dao
interface ReadDao{

    @Query("SELECT * FROM read order by id desc limit ((:pageNum-1)*:pageSize),:pageSize*:pageNum")
    fun queryAll(pageNum:Int,pageSize:Int):List<Read>

    @Query("SELECT * FROM  read where title=:title")
    fun queryByTitle(title:String): Read

    @Query("DELETE FROM read WHERE title = :title")
    fun delete(title: String)

    @Insert
    fun insert(read: Read)
}