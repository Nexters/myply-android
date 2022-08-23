package com.cocaine.myply.feature.ui.keep

import com.cocaine.myply.feature.data.model.MemoResponse
import com.cocaine.myply.feature.data.model.MemoUpdate
import com.cocaine.myply.feature.data.repository.MyPlyRepository
import javax.inject.Inject

class KeepWriteUsecase @Inject constructor(private val myPlyRepository: MyPlyRepository) {
    suspend fun updateMemo(memoId: String, body: MemoUpdate): MemoResponse = myPlyRepository.updateMemo(memoId, body)
}