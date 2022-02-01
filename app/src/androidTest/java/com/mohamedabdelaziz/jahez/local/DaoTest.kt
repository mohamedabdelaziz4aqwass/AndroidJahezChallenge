package com.mohamedabdelaziz.jahez.local

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mohamedabdelaziz.jahez.domain.entities.RestaurantsResponseItem
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.ArrayList

@RunWith(AndroidJUnit4ClassRunner::class)
class DaoTest : DataBaseTest() {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertTrendingTest() {
          val responseItem1 = RestaurantsResponseItem()
          val responseItem2 = RestaurantsResponseItem()
          val responseItem3 = RestaurantsResponseItem()
          val responseItem4 = RestaurantsResponseItem()

        val trendingItemResponseList: MutableList<RestaurantsResponseItem> = ArrayList()
        trendingItemResponseList.add(responseItem1)
        trendingItemResponseList.add(responseItem2)
        trendingItemResponseList.add(responseItem3)
        trendingItemResponseList.add(responseItem4)

        appDatabase.restaurantDao().insert(trendingItemResponseList)
        appDatabase.restaurantDao().getAllRestaurants().observeForever { restaurantList ->
            Assert.assertEquals(restaurantList.size, 4)
        }
    }

    @Test
    fun deleteTrendingTest() {
        appDatabase.restaurantDao().deleteAllData()
        appDatabase.restaurantDao().getAllRestaurants()
            .observeForever { restaurants -> Assert.assertEquals(restaurants.size, 0) }
    }
}