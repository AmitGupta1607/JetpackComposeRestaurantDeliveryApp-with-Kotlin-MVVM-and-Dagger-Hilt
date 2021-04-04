package com.example.jetpackcomposeapp.uiPackage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeapp.data.repository.Repository
import com.example.jetpackcomposeapp.data.models.BannersReponse
import com.example.jetpackcomposeapp.data.models.CollectionsResponse
import com.example.jetpackcomposeapp.data.models.RestaurantListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    val loading= mutableStateOf(true)

    fun getBanners():LiveData<BannersReponse>{
        val listBanners = repository.fetchBanners()
        loading.value =false
        return listBanners
    }

    fun getRestaurantList():LiveData<RestaurantListResponse>{
        return repository.fetchAllRestaurantsForLocation()
    }

    fun getCollectionsList():LiveData<CollectionsResponse>{
        val collectionsResponse = repository.fetchCollections()
        return collectionsResponse
    }
}