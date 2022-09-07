package com.cocaine.myply.feature.ui.keep

import com.cocaine.myply.feature.data.repository.MyPlyRepository
import javax.inject.Inject

class KeepUsecase @Inject constructor(private val repository: MyPlyRepository) {

    suspend fun getUserMemoList() = repository.getUserMemoList()
}