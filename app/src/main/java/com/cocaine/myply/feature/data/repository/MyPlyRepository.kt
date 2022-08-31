package com.cocaine.myply.feature.data.repository

import com.cocaine.myply.feature.data.datasource.remote.MyPlyService
import com.cocaine.myply.feature.data.model.UserKeywordUpdateData
import com.cocaine.myply.feature.data.model.UserNameUpdateData
import javax.inject.Inject

class MyPlyRepository @Inject constructor(private val myPlyService: MyPlyService) {
    suspend fun getRecommendKeyword() = myPlyService.requestRecommendTags()

    suspend fun searchMusicPlayList(query: String, nextPageToken: String? = null) =
        myPlyService.searchMusicPlayList(query, nextPageToken)

    suspend fun getUserInfo() = myPlyService.getUserInfo()

    suspend fun updateUserName(userNameUpdateData: UserNameUpdateData) =
        myPlyService.updateUserName(userNameUpdateData)

    suspend fun updateUserKeyword(userKeywordUpdateData: UserKeywordUpdateData) =
        myPlyService.updateUserKeyword(userKeywordUpdateData)
}
