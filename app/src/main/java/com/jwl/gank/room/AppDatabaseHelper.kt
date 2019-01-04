package com.jwl.gank.room

import android.arch.persistence.room.Room
import android.content.Context
import com.jwl.gank.room.favorite.Favorite
import com.jwl.gank.room.read.Read
import com.jwl.gank.room.search.Search

/**
 * author:  lujunwu
 * date:    2018/12/14 16:46
 * desc:    NoDiscription
 */

class AppDatabaseHelper(ctx: Context) {

    var appDatabase = Room.databaseBuilder(ctx, AppDatabase::class.java, "search.db").build()

    companion object {
        @Volatile
        var INSTANCE: AppDatabaseHelper? = null

        fun getInstance(context: Context): AppDatabaseHelper {
            if (INSTANCE == null) {
                synchronized(AppDatabaseHelper::class) {
                    if (INSTANCE == null) {
                        INSTANCE = AppDatabaseHelper(context.applicationContext)
                    }
                }
            }
            return INSTANCE!!
        }

    }

    fun getAll(): List<Search> {
        return appDatabase.searchDao().getAll()
    }

    fun insertAll(vararg search: Search) {
        for (search in search) {
            appDatabase.searchDao().insert(search)
        }
    }

    fun deleteAll() {
        appDatabase.searchDao().deleteAll()
    }

    fun delete(vararg search: Search) {
        for (search in search) {
            appDatabase.searchDao().delete(search)
        }
    }


    fun getFavorites(pageNum: Int, pageSize: Int): List<Favorite> {
        return appDatabase.favoriteDao().queryAll(pageNum, pageSize)
    }

    fun deleteFavorite(title: String) {
        appDatabase.favoriteDao().delete(title)
    }

    fun insertFavorite(favorite: Favorite) {
        appDatabase.favoriteDao().insert(favorite)
    }

    fun queryFavoriteByTitle(title: String): Favorite {
        return appDatabase.favoriteDao().queryByTitle(title)
    }

    fun getReads(pageNum: Int, pageSize: Int): List<Read> {
        return appDatabase.readDao().queryAll(pageNum, pageSize)
    }

    fun deleteRead(title: String) {
        appDatabase.readDao().delete(title)
    }

    fun insertRead(read: Read) {
        appDatabase.readDao().insert(read)
    }

    fun queryReadByTitle(title: String): Read {
        return appDatabase.readDao().queryByTitle(title)
    }


}