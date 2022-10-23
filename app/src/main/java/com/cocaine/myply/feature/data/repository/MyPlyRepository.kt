package com.cocaine.myply.feature.data.repository

import com.cocaine.myply.feature.data.datasource.remote.MyPlyService
import com.cocaine.myply.feature.data.model.*
import javax.inject.Inject

class MyPlyRepository @Inject constructor(private val myPlyService: MyPlyService) {
    suspend fun getRecommendKeyword() = myPlyService.requestRecommendTags()

    suspend fun searchMusicPlayList(query: String, nextPageToken: String? = null) = myPlyService.searchMusicPlayList(query, nextPageToken)

    suspend fun getUserMemoList(): UserMemoList = myPlyService.getUserMemos()

    suspend fun updateMemo(memoId: String, body: MemoUpdate): MemoInfo = myPlyService.updateMemo(memoId, body)

    suspend fun getMemo(memoId: String): MemoResponse = myPlyService.getMemo(memoId)

    suspend fun retrieveMusicPlayList(nextPageToken: String? = null, order: String) =
        myPlyService.retrieveMusicPlayList(
            nextPageToken, order
        )

    suspend fun getPreferenceMusicPlaylist(nextPageToken: String?) =
        myPlyService.getPreferenceMusicPlaylist(nextPageToken)

    suspend fun getUserInfo() = myPlyService.getUserInfo()

    suspend fun updateUserName(userNameUpdateData: UserNameUpdateData) =
        myPlyService.updateUserName(userNameUpdateData)

    suspend fun updateUserKeyword(userKeywordUpdateData: UserKeywordUpdateData) =
        myPlyService.updateUserKeyword(userKeywordUpdateData)

    suspend fun getRecommendTags() = myPlyService.getRecommendTags()

    suspend fun addMemo(youtubeId: String, body: String = "") = myPlyService.addMemo(MemoRequest(youtubeId, body))
}
