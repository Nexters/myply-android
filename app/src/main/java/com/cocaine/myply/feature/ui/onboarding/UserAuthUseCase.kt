package com.cocaine.myply.feature.ui.onboarding

import com.cocaine.myply.feature.data.repository.MyPlyRepository
import javax.inject.Inject

class UserAuthUseCase @Inject constructor(private val myPlyRepository: MyPlyRepository) {

    suspend fun signupUser(nickname: String, keywords: List<String>) = myPlyRepository.signupUser(
        keywords, nickname
    )
}
