package com.cocaine.myply.feature.data.repository

import com.cocaine.myply.feature.data.datasource.remote.MyPlyService
import com.cocaine.myply.feature.data.model.MemoInfo
import com.cocaine.myply.feature.data.model.MemoResponse
import com.cocaine.myply.feature.data.model.MemoUpdate
import com.cocaine.myply.feature.data.model.UserMemoList
import javax.inject.Inject

class MyPlyRepository @Inject constructor(private val myPlyService: MyPlyService) {
    suspend fun getRecommendKeyword() = myPlyService.requestRecommendTags()

    suspend fun searchMusicPlayList(query: String, nextPageToken: String? = null) = myPlyService.searchMusicPlayList(query, nextPageToken)

    suspend fun getUserMemoList(): UserMemoList = myPlyService.getUserMemos()

    suspend fun updateMemo(memoId: String, body: MemoUpdate): MemoInfo = myPlyService.updateMemo(memoId, body)

    suspend fun getMemo(memoId: String): MemoResponse = myPlyService.getMemo(memoId)
}