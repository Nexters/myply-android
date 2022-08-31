package com.cocaine.myply.feature.ui.mypage

import com.cocaine.myply.feature.data.repository.MyPlyRepository
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(private val myPlyRepository: MyPlyRepository) {

    suspend fun getUserInfo() = myPlyRepository.getUserInfo()
}
