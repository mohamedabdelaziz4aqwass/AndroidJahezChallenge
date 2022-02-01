package com.mohamedabdelaziz.jahez.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mohamedabdelaziz.jahez.data.datasource.local.dp.RestaurantDataBase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class DataBaseTest {
    lateinit var appDatabase: RestaurantDataBase
    @Before
    fun initDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
            RestaurantDataBase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }
}