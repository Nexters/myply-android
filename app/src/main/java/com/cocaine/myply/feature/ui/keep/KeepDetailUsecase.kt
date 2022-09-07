package com.cocaine.myply.feature.ui.keep

import com.cocaine.myply.feature.data.model.MemoInfo
import com.cocaine.myply.feature.data.repository.MyPlyRepository
import javax.inject.Inject

class KeepDetailUsecase @Inject constructor(private val repository: MyPlyRepository) {
    suspend fun getMemo(memoId: String): MemoInfo = repository.getMemo(memoId).data
}