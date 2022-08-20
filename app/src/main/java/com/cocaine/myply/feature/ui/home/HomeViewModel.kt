package com.cocaine.myply.feature.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cocaine.myply.core.base.BaseViewModel
import com.cocaine.myply.feature.data.model.PlaylistOrder
import com.cocaine.myply.feature.data.model.VideoResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

    private val _playlistOrder = MutableLiveData<PlaylistOrder>()
    val playlistOrder: LiveData<PlaylistOrder> = _playlistOrder

    private val _playlists = MutableLiveData<List<VideoResponse>>()
    val playlists: LiveData<List<VideoResponse>> = _playlists

    private val _likedUpdatePlaylistId = MutableLiveData<Pair<String, Boolean>>()
    val likedUpdatePlaylistId: LiveData<Pair<String, Boolean>> = _likedUpdatePlaylistId

    fun updatePlaylistOrder(playlistOrder: PlaylistOrder) {
        _playlistOrder.value = playlistOrder
    }

    fun updatePlaylistLiked(clickedPosition: Int) {
        val clickedPlaylist = _playlists.value?.get(clickedPosition) ?: return
        // TODO 해당 비디오의 Liked update

        _likedUpdatePlaylistId.value = clickedPlaylist.youtubeVideoId to clickedPlaylist.isLiked
    }
}
