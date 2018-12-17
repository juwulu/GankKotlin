package com.jwl.gank.room.search

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * author:  lujunwu
 * date:    2018/12/14 16:20
 * desc:    NoDiscription
 */

@Entity
data class Search(
        @PrimaryKey(autoGenerate = true) var id:Int,
        @ColumnInfo var record:String
)