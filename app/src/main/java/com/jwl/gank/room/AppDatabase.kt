package com.jwl.gank.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.jwl.gank.room.favorite.Favorite
import com.jwl.gank.room.favorite.FavoriteDao
import com.jwl.gank.room.read.Read
import com.jwl.gank.room.read.ReadDao
import com.jwl.gank.room.search.Search
import com.jwl.gank.room.search.SearchDao

/**
 * author:  lujunwu
 * date:    2018/12/14 16:38
 * desc:    NoDiscription
 */

@Database(entities = arrayOf(Search::class,Favorite::class, Read::class),version = 2)
abstract class AppDatabase():RoomDatabase(){
    abstract fun searchDao():SearchDao
    abstract fun favoriteDao():FavoriteDao
    abstract fun readDao():ReadDao
}