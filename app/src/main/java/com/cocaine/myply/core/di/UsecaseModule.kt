package com.cocaine.myply.core.di

import com.cocaine.myply.feature.data.repository.MyPlyRepository
import com.cocaine.myply.feature.ui.mypage.TagUseCase
import com.cocaine.myply.feature.ui.mypage.UserInfoUseCase
import com.cocaine.myply.feature.ui.search.SearchUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object UsecaseModule {

    @Provides
    fun provideSearchUsecase(rep: MyPlyRepository) = SearchUsecase(rep)

    @Provides
    fun provideUserInfoUseCase(myPlyRepository: MyPlyRepository) = UserInfoUseCase(myPlyRepository)

    @Provides
    fun provideTagUseCase(myPlyRepository: MyPlyRepository) = TagUseCase(myPlyRepository)
}
