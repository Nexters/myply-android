package com.cocaine.myply.feature.data.model

data class UserInfoResponse(val code: Int, val message: String?, val data: UserInfoData)

data class UserInfoData(val deviceToken: String, val keywords: List<String>, val name: String)
