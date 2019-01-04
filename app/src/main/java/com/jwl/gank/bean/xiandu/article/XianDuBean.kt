package com.jwl.gank.bean.xiandu.article


import com.google.gson.annotations.SerializedName

data class Site(@SerializedName("subscribers")
                val subscribers: Int = 0,
                @SerializedName("cat_cn")
                val catCn: String = "",
                @SerializedName("icon")
                val icon: String = "",
                @SerializedName("name")
                val name: String = "",
                @SerializedName("id")
                val id: String = "",
                @SerializedName("type")
                val type: String = "",
                @SerializedName("cat_en")
                val catEn: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("desc")
                val desc: String = "",
                @SerializedName("feed_id")
                val feedId: String = "")


data class ResultsItem(@SerializedName("cover")
                       val cover: String = "",
                       @SerializedName("uid")
                       val uid: String = "",
                       @SerializedName("site")
                       val site: Site,
                       @SerializedName("deleted")
                       val deleted: Boolean = false,
                       @SerializedName("crawled")
                       val crawled: Long = 0,
                       @SerializedName("created_at")
                       val createdAt: String = "",
                       @SerializedName("raw")
                       val raw: String = "",
                       @SerializedName("_id")
                       val Id: String = "",
                       @SerializedName("published_at")
                       val publishedAt: String = "",
                       @SerializedName("title")
                       val title: String = "",
                       @SerializedName("content")
                       val content: String = "",
                       @SerializedName("url")
                       val url: String = "")


data class XianDuBean(@SerializedName("error")
                      val error: Boolean = false,
                      @SerializedName("results")
                      val results: List<ResultsItem>?)


