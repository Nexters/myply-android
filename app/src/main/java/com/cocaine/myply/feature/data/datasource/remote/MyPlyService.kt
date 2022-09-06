package com.cocaine.myply.feature.data.datasource.remote

import com.cocaine.myply.feature.data.model.SearchResponse
import com.cocaine.myply.feature.data.model.SearchTagResponse
import com.cocaine.myply.feature.data.model.TagResponse
import com.cocaine.myply.feature.data.model.UserInfoResponse
import com.cocaine.myply.feature.data.model.UserKeywordUpdateData
import com.cocaine.myply.feature.data.model.UserNameUpdateData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query

interface MyPlyService {
    @GET("tags/recommend")
    suspend fun requestRecommendTags(): SearchTagResponse

    @GET("musics/search")
    suspend fun searchMusicPlayList(
        @Query("q") query: String,
        @Query("nextToken") nextPageToken: String? = null
    ): SearchResponse

    @GET("members")
    suspend fun getUserInfo(): UserInfoResponse

    @PATCH("members")
    suspend fun updateUserName(@Body body: UserNameUpdateData): UserInfoResponse

    @PATCH("members")
    suspend fun updateUserKeyword(@Body body: UserKeywordUpdateData): UserInfoResponse

    @GET("tags/recommend")
    suspend fun getRecommendTags(): TagResponse
}
