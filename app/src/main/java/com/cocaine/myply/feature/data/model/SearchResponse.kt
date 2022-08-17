package com.cocaine.myply.feature.data.model

data class SearchResponse(val code: Int, val message: String?, val data: SearchMusicData?)

data class SearchMusicData(val musics: List<VideoResponse>, val nextPageToken: String?)

data class SearchTagResponse(val code: Int, val message: String?, val data: Tags)

data class Tags(val tags: List<String>)