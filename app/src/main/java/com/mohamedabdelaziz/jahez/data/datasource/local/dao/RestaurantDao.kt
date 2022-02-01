package com.mohamedabdelaziz.jahez.data.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mohamedabdelaziz.jahez.domain.entities.RestaurantsResponseItem


@Dao
interface RestaurantDao {
    @Insert
      fun insert(restaurantList: List<RestaurantsResponseItem>)

    @Query("select * from restaurants_table")
      fun getAllRestaurants(): LiveData<List<RestaurantsResponseItem>>

    @Query("delete from restaurants_table")
    fun deleteAllData()

}