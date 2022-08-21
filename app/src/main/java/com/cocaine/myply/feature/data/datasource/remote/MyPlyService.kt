package com.cocaine.myply.feature.data.datasource.remote

import com.cocaine.myply.feature.data.model.SearchResponse
import com.cocaine.myply.feature.data.model.SearchTagResponse
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface MyPlyService {
    @GET("tags/recommend")
    suspend fun requestRecommendTags(): SearchTagResponse

    @GET("musics/search")
    suspend fun searchMusicPlayList(@Query("q") query: String, @Query("nextToken")nextPageToken: String? = null): SearchResponse
}