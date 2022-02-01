package com.mohamedabdelaziz.jahez.presentation.modules

import dagger.hilt.InstallIn
import javax.inject.Singleton
import com.mohamedabdelaziz.jahez.data.datasource.remote.ApiService
import com.mohamedabdelaziz.jahez.domain.repositories.RestaurantRepositoryRemote
import com.mohamedabdelaziz.jahez.data.repositoryimp.RestaurantRepositoryRemoteImp
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {
    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Singleton
    @Provides
    fun provideRestaurantRepositoryRemote(apiService: ApiService): RestaurantRepositoryRemote =
        RestaurantRepositoryRemoteImp(apiService)
}