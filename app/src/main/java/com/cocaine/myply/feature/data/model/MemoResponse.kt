package com.cocaine.myply.feature.data.model

data class MemoResponse(
    val memoId: String,
    val thumbnailURL: String,
    val title: String,
    val keywords: List<String>,
    val body: String
)
