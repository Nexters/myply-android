package com.cocaine.myply.feature.data.model

data class VideoResponse(
    val youtubeVideoId: String,
    val thumbnailURL: String,
    val youtubeTags: List<String>,
    val title: String,
    val videoDeepLink: String,
    val isLiked: Boolean
)
