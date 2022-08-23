package com.cocaine.myply.feature.data.datasource.remote

import com.cocaine.myply.feature.data.model.*
import retrofit2.http.*

interface MyPlyService {
    @GET("tags/recommend")
    suspend fun requestRecommendTags(): SearchTagResponse

    @GET("musics/search")
    suspend fun searchMusicPlayList(@Query("q") query: String, @Query("nextToken")nextPageToken: String? = null): SearchResponse

    @GET("memos")
    suspend fun getUserMemos(): UserMemoList

    @PATCH("memos/{memoId}")
    suspend fun updateMemo(@Path("memoId") memoId: String, @Body body: MemoUpdate): MemoResponse

    @GET("memos/{memoId}")
    suspend fun getMemo(@Path("memoId") memoId: String): MemoResponse
}