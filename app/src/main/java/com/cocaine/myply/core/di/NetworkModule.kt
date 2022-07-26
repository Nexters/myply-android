package com.cocaine.myply.core.di

import com.cocaine.myply.BuildConfig
import com.cocaine.myply.feature.data.datasource.remote.MyPlyService
import com.cocaine.myply.feature.data.datasource.remote.MyPlyServiceImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = ""

    @Provides
    @Singleton
    fun getMyPlyRetrofit(): Retrofit {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val logging =
            HttpLoggingInterceptor().apply { setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC) }
        val okhttpInterceptor = OkHttpClient.Builder().addInterceptor(logging).build()

        val retrofit = Retrofit.Builder().client(okhttpInterceptor).baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

        return retrofit
    }

    @Provides
    @Singleton
    fun getMyPlyService(retrofit: Retrofit): MyPlyService = MyPlyServiceImpl(retrofit)

}