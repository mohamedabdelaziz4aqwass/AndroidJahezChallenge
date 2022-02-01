package com.mohamedabdelaziz.jahez.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mohamedabdelaziz.jahez.domain.usecase.RestaurantLocalUseCase
import com.mohamedabdelaziz.jahez.domain.usecase.RestaurantRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.mohamedabdelaziz.jahez.domain.entities.RestaurantsResponseItem
import javax.inject.Inject

@HiltViewModel
 class RestaurantViewModel @Inject constructor(
    private var restaurantRemoteUseCase: RestaurantRemoteUseCase,
    private var restaurantLocalUseCase: RestaurantLocalUseCase
) : ViewModel() {
     var restaurantMutableLiveData = MutableLiveData<List<RestaurantsResponseItem>>()
    private lateinit var restaurantsResponseLocal: LiveData<List<RestaurantsResponseItem>>
    init {
        getRestaurantListRemote()
    }

    private fun getRestaurantListRemote() {
        viewModelScope.launch {
            restaurantRemoteUseCase.invokeRestaurantList().catch {
                Log.e(TAG, it.message!!)
            }.collect {
                restaurantMutableLiveData.value = it
                deleteRestaurants()
                insertRestaurants(it)
            }
        }
    }

    private suspend fun insertRestaurants(restaurantItem: List<RestaurantsResponseItem>) {
        restaurantLocalUseCase.insertRestaurant(restaurantItem)
    }
    private suspend fun deleteRestaurants() {
        restaurantLocalUseCase.deleteRestaurants()
    }



    fun getRestaurantListLocal(): LiveData<List<RestaurantsResponseItem>> {
       restaurantsResponseLocal = restaurantLocalUseCase.invokeRestaurantList()
        return restaurantsResponseLocal
    }

    companion object {
        private const val TAG = "RestaurantViewModel"
    }
}