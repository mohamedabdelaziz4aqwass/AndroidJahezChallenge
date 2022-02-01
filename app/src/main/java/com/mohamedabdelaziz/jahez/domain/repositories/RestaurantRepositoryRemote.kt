package com.mohamedabdelaziz.jahez.domain.repositories

import com.mohamedabdelaziz.jahez.domain.entities.RestaurantsResponseItem
import kotlinx.coroutines.flow.Flow

interface RestaurantRepositoryRemote {
    suspend fun getRestaurantList(): Flow<List<RestaurantsResponseItem>>
}