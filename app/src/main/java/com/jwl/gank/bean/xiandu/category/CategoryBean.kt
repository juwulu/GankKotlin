package com.jwl.gank.bean.xiandu.category


import com.google.gson.annotations.SerializedName

data class CategoryBean(@SerializedName("error")
                        val error: Boolean = false,
                        @SerializedName("results")
                        val results: List<ResultsItem>?)


data class ResultsItem(@SerializedName("name")
                       val name: String = "",
                       @SerializedName("en_name")
                       val enName: String = "",
                       @SerializedName("rank")
                       val rank: Int = 0,
                       @SerializedName("_id")
                       val Id: String = "")


