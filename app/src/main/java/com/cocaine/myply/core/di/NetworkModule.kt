package com.cocaine.myply.core.di

import android.content.Context
import com.cocaine.myply.BuildConfig
import com.cocaine.myply.core.utils.getDeviceToken
import com.cocaine.myply.feature.data.datasource.remote.MyPlyService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://myply-server-rwwy3wj4sa-du.a.run.app/api/v1/"

    @Provides
    @Singleton
    fun getMyPlyRetrofit(@ApplicationContext context: Context): Retrofit {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val logging =
            HttpLoggingInterceptor().apply {
                setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC)
            }

        val networkInterceptor = Interceptor { chain ->
            val request =
                chain.request().newBuilder().addHeader("Device-token", getDeviceToken(context))
                    .build()
            chain.proceed(request)
        }

        val okhttpInterceptor =
            OkHttpClient.Builder().addInterceptor(logging).addNetworkInterceptor(networkInterceptor)
                .build()

        val retrofit = Retrofit.Builder().client(okhttpInterceptor).baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

        return retrofit
    }

    @Provides
    @Singleton
    fun getMyPlyService(retrofit: Retrofit): MyPlyService = retrofit.create(MyPlyService::class.java)

}