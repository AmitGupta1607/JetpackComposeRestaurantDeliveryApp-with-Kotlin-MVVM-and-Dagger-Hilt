package com.example.jetpackcomposeapp.data.repository

import androidx.lifecycle.LiveData
import com.example.jetpackcomposeapp.data.models.BannersReponse
import com.example.jetpackcomposeapp.data.models.CollectionsResponse
import com.example.jetpackcomposeapp.data.models.RestaurantListResponse

interface Repository {

    fun fetchBanners():LiveData<BannersReponse>
    fun fetchAllRestaurantsForLocation():LiveData<RestaurantListResponse>
    fun fetchCollections():LiveData<CollectionsResponse>
}