package com.cocaine.myply.core.di

import android.content.Context
import androidx.room.Room
import com.cocaine.myply.core.storage.MyPlySharedPreference
import com.cocaine.myply.feature.data.datasource.local.MyPlyDatabase
import com.cocaine.myply.feature.data.datasource.remote.MyPlyService
import com.cocaine.myply.feature.data.repository.MyPlyRepository
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
    fun setMyPlySharedPreference(@ApplicationContext context: Context) =
        MyPlySharedPreference(context)

    @Provides
    @Singleton
    fun getMyPlyDatabase(@ApplicationContext context: Context): MyPlyDatabase {
        return Room.databaseBuilder(
            context,
            MyPlyDatabase::class.java,
            "MyPlyDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun getMyPlyRemoteRepository(myPlyService: MyPlyService, @ApplicationContext context: Context) =
        MyPlyRepository(myPlyService, context)
}
