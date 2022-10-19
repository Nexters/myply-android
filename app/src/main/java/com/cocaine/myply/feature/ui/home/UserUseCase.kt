package com.cocaine.myply.feature.ui.home

import com.cocaine.myply.feature.data.repository.MyPlyRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(private val repository: MyPlyRepository) {

    suspend fun getUserRegisterState() = repository.getUserInfo()
}
