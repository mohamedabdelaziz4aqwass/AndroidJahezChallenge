package com.mohamedabdelaziz.jahez.domain.usecase


import androidx.lifecycle.LiveData
import javax.inject.Inject
import com.mohamedabdelaziz.jahez.domain.repositories.RestaurantRepositoryLocal
import com.mohamedabdelaziz.jahez.domain.entities.RestaurantsResponseItem


class RestaurantLocalUseCase @Inject constructor(private var repository: RestaurantRepositoryLocal){
       fun invokeRestaurantList(): LiveData<List<RestaurantsResponseItem>> =repository.getRestaurantList()
    suspend fun insertRestaurant(restaurantList: List<RestaurantsResponseItem>) {
        repository.insert(restaurantList)
    }
    suspend fun deleteRestaurants(){
        repository.deleteAllData()
    }

}
