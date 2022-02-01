package com.mohamedabdelaziz.jahez.domain.entities

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
@Keep
@Entity(tableName = "restaurants_table")
data class RestaurantsResponseItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var distance: Double,
    var hasOffer: Boolean,
    var hours: String,
    var image: String,
    var name: String,
    var offer: String?,
    var rating: Double
){
    constructor() : this(0,0.0,false,"","","", "",0.0)
}
