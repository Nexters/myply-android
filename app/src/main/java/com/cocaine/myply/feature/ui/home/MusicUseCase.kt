package com.cocaine.myply.feature.ui.home

import com.cocaine.myply.feature.data.repository.MyPlyRepository
import javax.inject.Inject

class MusicUseCase @Inject constructor(private val myPlyRepository: MyPlyRepository) {

    suspend fun retrieveMusicPlaylist(nextPageToken: String? = null, order: String) =
        myPlyRepository.retrieveMusicPlayList(nextPageToken, order)

    suspend fun getPreferenceMusicPlaylist(nextPageToken: String?) =
        myPlyRepository.getPreferenceMusicPlaylist(nextPageToken)

    suspend fun createMemo(youtubeVideoID: String) = myPlyRepository.addMemo(youtubeVideoID)

    suspend fun deleteMemo(youtubeVideoID: String) = myPlyRepository.deleteMemo(youtubeVideoID)
}
