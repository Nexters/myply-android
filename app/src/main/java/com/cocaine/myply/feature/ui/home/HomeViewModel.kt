package com.cocaine.myply.feature.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cocaine.myply.core.base.BaseViewModel
import com.cocaine.myply.feature.data.model.MemoState
import com.cocaine.myply.feature.data.model.MusicData
import com.cocaine.myply.feature.data.model.PlaylistOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val musicUseCase: MusicUseCase) : BaseViewModel() {

    private val _playlistOrder = MutableLiveData(PlaylistOrder.RECENT)
    val playlistOrder: LiveData<PlaylistOrder> = _playlistOrder

    private val _playlists = MutableLiveData<List<MusicData>>()
    val playlists: LiveData<List<MusicData>> = _playlists

    private val _likedUpdatePlaylistId = MutableLiveData<Pair<String, MemoState>>()
    val likedUpdatePlaylistId: LiveData<Pair<String, MemoState>> = _likedUpdatePlaylistId

    private var _nextPageToken = MutableLiveData<String?>(null)
    val nextPageToken: LiveData<String?> = _nextPageToken

    init {
        loadPlaylists()
    }

    fun loadPlaylists() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _playlistOrder.value?.let { order ->
                when (order) {
                    PlaylistOrder.PREFERENCE -> {
                        musicUseCase.getPreferenceMusicPlaylist(_nextPageToken.value)
                    }
                    else -> {
                        musicUseCase.retrieveMusicPlaylist(_nextPageToken.value, order.value)
                    }
                }.data?.let { (musics, nextPageToken) ->
                    _playlists.postValue(musics)
                    _nextPageToken.postValue(nextPageToken)
                }
            }
        }
    }

    fun updatePlaylistOrder(playlistOrder: PlaylistOrder) {
        _playlistOrder.value = playlistOrder
        _nextPageToken.value = null
    }

    fun updatePlaylistLiked(clickedPosition: Int) {
        val clickedPlaylist = _playlists.value?.get(clickedPosition) ?: return
        // TODO 해당 비디오의 Liked update

        _likedUpdatePlaylistId.value = clickedPlaylist.youtubeVideoID to clickedPlaylist.memoState
    }
}
