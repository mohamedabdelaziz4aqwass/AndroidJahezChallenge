package com.mohamedabdelaziz.jahez.domain.usecase

import com.mohamedabdelaziz.jahez.domain.entities.RestaurantsResponseItem
import javax.inject.Inject
import com.mohamedabdelaziz.jahez.domain.repositories.RestaurantRepositoryRemote
import kotlinx.coroutines.flow.Flow


class RestaurantRemoteUseCase @Inject constructor(private var repository: RestaurantRepositoryRemote){
    suspend fun invokeRestaurantList(): Flow<List<RestaurantsResponseItem>> = repository.getRestaurantList()

}