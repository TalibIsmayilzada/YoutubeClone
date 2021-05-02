package com.talib.youtubeclone.ui.home

data class YoutubeModel(
    val id: Int,
    val thumbImg: String,
    val authImg: String,
    val title: String,
    val desc: String,
    val isPost: Boolean? = null
)
