package com.cocaine.myply.feature.ui.search

import com.cocaine.myply.feature.data.repository.MyPlyRepository
import javax.inject.Inject

class SearchUsecase @Inject constructor(private val myPlyRemoteRepository: MyPlyRepository) {
    suspend fun getRecommendKeyworkd() = myPlyRemoteRepository.getRecommendKeyword()

    suspend fun searchMusicPlayList(query: String, nextPageToken: String? = null) = myPlyRemoteRepository.searchMusicPlayList(query, nextPageToken)

    suspend fun addMemo(youtubeId: String, body: String = "") = myPlyRemoteRepository.addMemo(youtubeId, body)
}