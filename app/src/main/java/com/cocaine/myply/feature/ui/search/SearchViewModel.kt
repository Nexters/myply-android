package com.cocaine.myply.feature.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cocaine.myply.core.base.BaseViewModel
import com.cocaine.myply.feature.data.model.VideoResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val usecase: SearchUsecase) : BaseViewModel() {
    private val _recommendKeyword = MutableLiveData<List<String>>()
    val recommendKeyword: LiveData<List<String>> = _recommendKeyword

    private val _searchVideoResponse = MutableLiveData<List<VideoResponse>?>()
    val searchVideoResponse: LiveData<List<VideoResponse>?> = _searchVideoResponse

    val curSearchMsg = MutableLiveData<String>()

    private var _nextPageToken = MutableLiveData<String?>(null)
    val nextPageToken: LiveData<String?> = _nextPageToken

    init {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val result = usecase.getRecommendKeyworkd()
            withContext(Dispatchers.Main) {
                _recommendKeyword.value = result.data.tags
            }
        }
    }

    fun searchMusicPlayList(query: String?) {
        if(query == null) return
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val result = usecase.searchMusicPlayList(query, _nextPageToken.value)
            withContext(Dispatchers.Main) {
                _searchVideoResponse.value = result.data?.musics
                _nextPageToken.value = result.data?.nextPageToken
            }
        }
    }
}