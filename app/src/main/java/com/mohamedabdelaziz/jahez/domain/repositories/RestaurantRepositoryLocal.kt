package com.mohamedabdelaziz.jahez.domain.repositories
import androidx.lifecycle.LiveData
import com.mohamedabdelaziz.jahez.domain.entities.RestaurantsResponseItem

interface RestaurantRepositoryLocal {
    suspend fun  insert(restaurantList: List<RestaurantsResponseItem>)
      fun getRestaurantList(): LiveData<List<RestaurantsResponseItem>>
   suspend  fun deleteAllData()
}