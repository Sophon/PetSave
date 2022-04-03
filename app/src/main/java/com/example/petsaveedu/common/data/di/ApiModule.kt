package com.example.petsaveedu.common.data.di

import com.example.petsaveedu.common.data.api.ApiConstants
import com.example.petsaveedu.common.data.api.PetFinderApi
import com.example.petsaveedu.common.data.api.interceptors.AuthenticationInterceptor
import com.example.petsaveedu.common.data.api.interceptors.NetworkStatusInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): PetFinderApi {
        return builder
            .build()
            .create(PetFinderApi::class.java)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
    }

    @Provides
    fun provideOkHttpClient(
        networkStatusInterceptor: NetworkStatusInterceptor,
        authenticationInterceptor: AuthenticationInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkStatusInterceptor)
            .addInterceptor(authenticationInterceptor)
            .build()
    }
}