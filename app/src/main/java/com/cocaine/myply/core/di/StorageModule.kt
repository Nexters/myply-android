package com.cocaine.myply.core.di

import android.content.Context
import com.cocaine.myply.core.storage.MyPlySharedPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun setMyPlySharedPreference(@ApplicationContext context: Context) = MyPlySharedPreference(context)
}