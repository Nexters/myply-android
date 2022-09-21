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
    suspend fun updateMemo(@Path("memoId") memoId: String, @Body body: MemoUpdate): MemoInfo

    @GET("memos/{memoId}")
    suspend fun getMemo(@Path("memoId") memoId: String): MemoResponse

    @GET("members")
    suspend fun getUserInfo(): UserInfoResponse

    @PATCH("members")
    suspend fun updateUserName(@Body body: UserNameUpdateData): UserInfoResponse

    @PATCH("members")
    suspend fun updateUserKeyword(@Body body: UserKeywordUpdateData): UserInfoResponse

    @GET("tags/recommend")
    suspend fun getRecommendTags(): TagResponse

    @POST("memos/")
    suspend fun addMemo(@Body body: MemoRequest): MemoResponse
}
