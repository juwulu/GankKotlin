package com.jwl.gank.bean

data class RestMovieBean(val error: Boolean = false,
                         val results: List<ResultsItem>?)

data class ResultsItem(val createdAt: String = "",
                       val publishedAt: String = "",
                       val Id: String = "",
                       val source: String = "",
                       val used: Boolean = false,
                       val type: String = "",
                       val url: String = "",
                       val desc: String = "",
                       val who: String = "")