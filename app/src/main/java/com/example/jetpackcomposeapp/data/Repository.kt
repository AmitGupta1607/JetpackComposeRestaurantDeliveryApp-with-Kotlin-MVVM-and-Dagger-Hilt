package com.example.jetpackcomposeapp.data

import androidx.lifecycle.LiveData
import com.example.jetpackcomposeapp.data.models.BannersReponse
import com.example.jetpackcomposeapp.data.models.RestaurantListResponse

interface Repository {

    fun fetchBanners():LiveData<BannersReponse>
    fun fetchAllRestaurantsForLocation():LiveData<RestaurantListResponse>
}