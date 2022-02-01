package com.mohamedabdelaziz.jahez.data.datasource.remote

import retrofit2.http.GET
 import com.mohamedabdelaziz.jahez.domain.entities.RestaurantsResponseItem

interface ApiService {
    @GET("restaurants.json")
    suspend fun getRestaurantList(): List<RestaurantsResponseItem>
}