package com.jwl.gank.bean




data class SearchBean(val count: Int = 0,
                      val error: Boolean = false,
                      val results: List<SearchResultItem>?)


data class SearchResultItem(val readability: String = "",
                       val publishedAt: String = "",
                       val ganhuoId: String = "",
                       val type: String = "",
                       val url: String = "",
                       val desc: String = "",
                       val who: String = "")