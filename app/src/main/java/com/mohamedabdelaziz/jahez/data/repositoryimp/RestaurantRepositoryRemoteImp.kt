package com.mohamedabdelaziz.jahez.data.repositoryimp

import javax.inject.Inject
import com.mohamedabdelaziz.jahez.data.datasource.remote.ApiService
import com.mohamedabdelaziz.jahez.domain.entities.RestaurantsResponseItem
import com.mohamedabdelaziz.jahez.domain.repositories.RestaurantRepositoryRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers.IO
 import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * This repository is responsible for
 * fetching data from server
 */
class RestaurantRepositoryRemoteImp @Inject constructor(private val apiService: ApiService) :
    RestaurantRepositoryRemote {
       override suspend fun getRestaurantList():  Flow<List<RestaurantsResponseItem>> = flow {
           emit(apiService.getRestaurantList())
       }.flowOn(IO)
}