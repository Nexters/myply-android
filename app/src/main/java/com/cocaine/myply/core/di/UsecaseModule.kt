package com.cocaine.myply.core.di

import com.cocaine.myply.feature.data.repository.MyPlyRepository
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
}