package com.jwl.gank.room.read

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Read(
        @PrimaryKey(autoGenerate = true) var id:Int,
        @ColumnInfo var title:String,
        @ColumnInfo var publishTime:String,
        @ColumnInfo var source:String,
        @ColumnInfo var imgUrl:String,
        @ColumnInfo var url:String
)