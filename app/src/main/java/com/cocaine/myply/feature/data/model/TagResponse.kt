package com.cocaine.myply.feature.data.model

data class TagResponse(val code: Int, val message: String, val data: TagData)

data class TagData(val tags: List<String>)
