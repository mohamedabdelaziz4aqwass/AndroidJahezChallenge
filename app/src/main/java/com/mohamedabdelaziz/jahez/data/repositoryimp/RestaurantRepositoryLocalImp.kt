package com.mohamedabdelaziz.jahez.data.repositoryimp


import androidx.lifecycle.LiveData
import com.mohamedabdelaziz.jahez.data.datasource.local.dao.RestaurantDao
import javax.inject.Inject
import com.mohamedabdelaziz.jahez.domain.repositories.RestaurantRepositoryLocal
import com.mohamedabdelaziz.jahez.domain.entities.RestaurantsResponseItem

/**
 * This repository is responsible for
 * fetching data from  db
 */
class RestaurantRepositoryLocalImp @Inject constructor(private val restaurantDao: RestaurantDao) :
    RestaurantRepositoryLocal {
    override suspend fun insert(restaurantList: List<RestaurantsResponseItem>) {
        restaurantDao.insert(restaurantList)
    }


    override   fun getRestaurantList(): LiveData<List<RestaurantsResponseItem> >{
        return restaurantDao.getAllRestaurants()
    }

    override suspend fun deleteAllData() {
        restaurantDao.deleteAllData()
    }

}