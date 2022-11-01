package com.cocaine.myply.feature.data.datasource.remote

import com.cocaine.myply.feature.data.model.MemoInfo
import com.cocaine.myply.feature.data.model.MemoRequest
import com.cocaine.myply.feature.data.model.MemoResponse
import com.cocaine.myply.feature.data.model.MemoUpdate
import com.cocaine.myply.feature.data.model.MusicResponse
import com.cocaine.myply.feature.data.model.SearchResponse
import com.cocaine.myply.feature.data.model.SearchTagResponse
import com.cocaine.myply.feature.data.model.TagResponse
import com.cocaine.myply.feature.data.model.UserInfoResponse
import com.cocaine.myply.feature.data.model.UserKeywordUpdateData
import com.cocaine.myply.feature.data.model.UserMemoList
import com.cocaine.myply.feature.data.model.UserNameUpdateData
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MyPlyService {
    @GET("tags/recommend")
    suspend fun requestRecommendTags(): SearchTagResponse

    @GET("musics/search")
    suspend fun searchMusicPlayList(
        @Query("q") query: String,
        @Query("nextToken") nextPageToken: String? = null
    ): SearchResponse

    @GET("memos")
    suspend fun getUserMemos(): UserMemoList

    @PATCH("memos/{memoId}")
    suspend fun updateMemo(@Path("memoId") memoId: String, @Body body: MemoUpdate): MemoInfo

    @GET("memos/{memoId}")
    suspend fun getMemo(@Path("memoId") memoId: String): MemoResponse

    @GET("musics")
    suspend fun retrieveMusicPlayList(
        @Query("nextToken") nextPageToken: String? = null,
        @Query("order") order: String
    ): MusicResponse

    @GET("musics/preference")
    suspend fun getPreferenceMusicPlaylist(@Query("nextToken") nextPageToken: String?): MusicResponse

    @GET("members")
    suspend fun getUserInfo(): UserInfoResponse

    @POST("members/")
    suspend fun signupUser(@Body body: UserInfoData): UserInfoResponse

    @PATCH("members")
    suspend fun updateUserName(@Body body: UserNameUpdateData): UserInfoResponse

    @PATCH("members")
    suspend fun updateUserKeyword(@Body body: UserKeywordUpdateData): UserInfoResponse

    @GET("tags/recommend")
    suspend fun getRecommendTags(): TagResponse

    @POST("memos/")
    suspend fun addMemo(@Body body: MemoRequest): MemoResponse

    @DELETE("memos/{memoIdOrYoutubeID}")
    suspend fun deleteMemo(@Path("memoIdOrYoutubeID") memoIdOrYoutubeID: String)
}
