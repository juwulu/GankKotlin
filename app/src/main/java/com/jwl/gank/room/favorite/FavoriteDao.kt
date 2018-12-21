package com.jwl.gank.room.favorite

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * author:  lujunwu
 * date:    2018/12/17 17:21
 * desc:    NoDiscription
 */

@Dao
interface FavoriteDao{

    @Query("SELECT * FROM favorite order by id desc limit ((:pageNum-1)*:pageSize),:pageSize*:pageNum")
    fun queryAll(pageNum:Int,pageSize:Int):List<Favorite>

    @Query("SELECT * FROM  favorite where title=:title")
    fun queryByTitle(title:String):Favorite

    @Query("DELETE FROM favorite WHERE title = :title")
    fun delete(title: String)

    @Insert
    fun insert(favorite: Favorite)
}