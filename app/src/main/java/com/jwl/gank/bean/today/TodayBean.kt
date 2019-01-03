package com.jwl.gank.bean.today

import com.google.gson.annotations.SerializedName

data class TodayBean(@SerializedName("category")
                     val category: List<String>?,
                     @SerializedName("error")
                     val error: Boolean = false,
                     @SerializedName("results")
                     val results: Results)