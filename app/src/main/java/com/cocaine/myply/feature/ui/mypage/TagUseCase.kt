package com.cocaine.myply.feature.ui.mypage

import com.cocaine.myply.feature.data.repository.MyPlyRepository
import javax.inject.Inject

class TagUseCase @Inject constructor(private val myPlyRepository: MyPlyRepository){

    suspend fun getRecommendTags() = myPlyRepository.getRecommendTags()
}
