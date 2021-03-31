package com.example.jetpackcomposeapp.uiPackage

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeapp.data.Repository
import com.example.jetpackcomposeapp.data.models.BannersReponse
import com.example.jetpackcomposeapp.data.models.RestaurantListResponse
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: Repository): ViewModel() {


    fun getBanners():LiveData<BannersReponse>{
      return repository.fetchBanners()
    }

    fun getRestaurantList():LiveData<RestaurantListResponse>{
        return repository.fetchAllRestaurantsForLocation()
    }
}