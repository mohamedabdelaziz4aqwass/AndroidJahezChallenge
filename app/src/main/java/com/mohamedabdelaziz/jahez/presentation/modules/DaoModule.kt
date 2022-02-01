package com.mohamedabdelaziz.jahez.presentation.modules

import com.mohamedabdelaziz.jahez.data.datasource.local.dao.RestaurantDao
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.mohamedabdelaziz.jahez.domain.repositories.RestaurantRepositoryLocal
import com.mohamedabdelaziz.jahez.data.repositoryimp.RestaurantRepositoryLocalImp
import com.mohamedabdelaziz.jahez.data.datasource.local.dp.RestaurantDataBase
import dagger.Module
import dagger.Provides

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    @Singleton
    fun provideDao(restaurantDataBase: RestaurantDataBase): RestaurantDao = restaurantDataBase.restaurantDao()

    @Singleton
    @Provides
    fun provideRestaurantRepositoryLocal(restaurantDao: RestaurantDao): RestaurantRepositoryLocal =
        RestaurantRepositoryLocalImp(restaurantDao)


}