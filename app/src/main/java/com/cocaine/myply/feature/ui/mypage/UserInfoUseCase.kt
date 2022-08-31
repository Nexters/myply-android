package com.cocaine.myply.feature.ui.mypage

import com.cocaine.myply.feature.data.model.UserKeywordUpdateData
import com.cocaine.myply.feature.data.model.UserNameUpdateData
import com.cocaine.myply.feature.data.repository.MyPlyRepository
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(private val myPlyRepository: MyPlyRepository) {

    suspend fun getUserInfo() = myPlyRepository.getUserInfo()

    suspend fun updateUserName(userNameUpdateData: UserNameUpdateData) =
        myPlyRepository.updateUserName(userNameUpdateData)

    suspend fun updateUserKeyword(userKeywordUpdateData: UserKeywordUpdateData) =
        myPlyRepository.updateUserKeyword(userKeywordUpdateData)
}
