package com.jwl.gank.room.favorite

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * author:  lujunwu
 * date:    2018/12/17 17:19
 * desc:    NoDiscription
 */
@Entity
data class Favorite(
        @PrimaryKey(autoGenerate = true) var id:Int,
        @ColumnInfo var title:String,
        @ColumnInfo var publishTime:String,
        @ColumnInfo var source:String,
        @ColumnInfo var imgUrl:String,
        @ColumnInfo var url:String
)