package com.mohamedabdelaziz.jahez.entities


import com.mohamedabdelaziz.jahez.domain.entities.RestaurantsResponseItem
import org.junit.Assert
import org.junit.Test
class RestaurantResponseItemTest {
    private val responseItem = RestaurantsResponseItem()

    @Test
    fun getHours() {
        responseItem.hours = "12"
        Assert.assertEquals("12", responseItem.hours)
    }

    @Test
    fun getName() {
        responseItem.name = "Name"
        Assert.assertEquals("Name", responseItem.name)
    }

    @Test
    fun getImage() {
        responseItem.image = "https://github.com/xingshaocheng.png"
        Assert.assertEquals("https://github.com/xingshaocheng.png", responseItem.image)
    }


    @Test
    fun getOffer() {
        responseItem.offer = "9.0"
        Assert.assertEquals("9.0", responseItem.offer)
    }

}