package com.cocaine.myply.feature.data.model

data class VideoResponse(
    val youtubeVideoId: String = "-1", // TODO: 통신 시, nullable 값으로 인한 파싱 에러가 발생함
    val thumbnailURL: String,
    val youtubeTags: List<String>?,
    val title: String,
    val videoDeepLink: String,
    val isLiked: Boolean = false,
    val isMemoed: Boolean = false
)
