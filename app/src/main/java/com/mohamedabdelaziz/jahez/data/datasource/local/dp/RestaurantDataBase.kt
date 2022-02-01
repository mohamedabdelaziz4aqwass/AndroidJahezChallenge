package com.mohamedabdelaziz.jahez.data.datasource.local.dp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohamedabdelaziz.jahez.data.datasource.local.dao.RestaurantDao
import com.mohamedabdelaziz.jahez.domain.entities.RestaurantsResponseItem

@Database(entities = [RestaurantsResponseItem::class], version = 1, exportSchema = false)
 abstract class RestaurantDataBase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}