package com.mohamedabdelaziz.jahez.presentation.viewmodels

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mohamedabdelaziz.jahez.local.DataBaseTest
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mohamedabdelaziz.jahez.core.utils.constants.Constants
import com.mohamedabdelaziz.jahez.data.datasource.local.dao.RestaurantDao
import com.mohamedabdelaziz.jahez.data.datasource.remote.ApiService
import com.mohamedabdelaziz.jahez.data.repositoryimp.RestaurantRepositoryLocalImp
import com.mohamedabdelaziz.jahez.data.repositoryimp.RestaurantRepositoryRemoteImp
import com.mohamedabdelaziz.jahez.domain.entities.RestaurantsResponseItem
import com.mohamedabdelaziz.jahez.domain.usecase.RestaurantLocalUseCase
import com.mohamedabdelaziz.jahez.domain.usecase.RestaurantRemoteUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4ClassRunner::class)
class ViewModelTest : DataBaseTest() {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private  lateinit var viewModel: RestaurantViewModel

    private lateinit var apiService: ApiService

    private lateinit var repositoryRemote: RestaurantRepositoryRemoteImp

    private lateinit var repositoryLocal: RestaurantRepositoryLocalImp
    @Before
    fun init() {
        apiService = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
        val restaurantDao: RestaurantDao=appDatabase.restaurantDao()
        repositoryRemote= RestaurantRepositoryRemoteImp(apiService)
        repositoryLocal= RestaurantRepositoryLocalImp(restaurantDao)
       var remoteUseCase=RestaurantRemoteUseCase(repositoryRemote)
       var localUseCase = RestaurantLocalUseCase(repositoryLocal)
        viewModel = RestaurantViewModel(remoteUseCase, localUseCase)
    }

    @Test
    fun testTrendingViewModel() {
        init()
        viewModel.restaurantMutableLiveData.observeForever { item: List<RestaurantsResponseItem> ->
            Assert.assertEquals(
                item.size.toLong(),
                4
            )
        }
    }
}