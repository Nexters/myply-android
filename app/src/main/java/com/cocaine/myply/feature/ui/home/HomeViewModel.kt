package com.cocaine.myply.feature.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cocaine.myply.core.base.BaseViewModel
import com.cocaine.myply.feature.data.model.MemoState
import com.cocaine.myply.feature.data.model.PlaylistOrder
import com.cocaine.myply.feature.data.model.MusicResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

    private val _playlistOrder = MutableLiveData<PlaylistOrder>()
    val playlistOrder: LiveData<PlaylistOrder> = _playlistOrder

    private val _playlists = MutableLiveData<List<MusicResponse>>()
    val playlists: LiveData<List<MusicResponse>> = _playlists

    private val _likedUpdatePlaylistId = MutableLiveData<Pair<String, MemoState>>()
    val likedUpdatePlaylistId: LiveData<Pair<String, MemoState>> = _likedUpdatePlaylistId

    fun updatePlaylistOrder(playlistOrder: PlaylistOrder) {
        _playlistOrder.value = playlistOrder
    }

    fun updatePlaylistLiked(clickedPosition: Int) {
        val clickedPlaylist = _playlists.value?.get(clickedPosition) ?: return
        // TODO 해당 비디오의 Liked update

        _likedUpdatePlaylistId.value = clickedPlaylist.youtubeVideoID to clickedPlaylist.memoState
    }
}
