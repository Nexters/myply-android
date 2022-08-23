package com.cocaine.myply.feature.ui.keep

import com.cocaine.myply.feature.data.model.MemoResponse
import com.cocaine.myply.feature.data.repository.MyPlyRepository
import javax.inject.Inject

class KeepDetailUsecase @Inject constructor(private val repository: MyPlyRepository) {
    suspend fun getMemo(memoId: String): MemoResponse = repository.getMemo(memoId)
}