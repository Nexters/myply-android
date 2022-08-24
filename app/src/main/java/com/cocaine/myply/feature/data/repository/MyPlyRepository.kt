package com.cocaine.myply.feature.data.repository

import com.cocaine.myply.feature.data.datasource.remote.MyPlyService
import javax.inject.Inject

class MyPlyRepository @Inject constructor(private val myPlyService: MyPlyService) {
    suspend fun getRecommendKeyword() = myPlyService.requestRecommendTags()

    suspend fun searchMusicPlayList(query: String, nextPageToken: String? = null) =
        myPlyService.searchMusicPlayList(query, nextPageToken)

    suspend fun retrieveMusicPlayList(nextPageToken: String? = null, order: String) =
        myPlyService.retrieveMusicPlayList(
            nextPageToken, order
        )

    suspend fun getPreferenceMusicPlaylist(nextPageToken: String?) =
        myPlyService.getPreferenceMusicPlaylist(nextPageToken)
}
