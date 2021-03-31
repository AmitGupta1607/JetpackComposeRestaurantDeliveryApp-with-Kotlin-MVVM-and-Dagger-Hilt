package com.example.jetpackcomposeapp.data.apiService


import com.example.jetpackcomposeapp.data.models.BannersReponse
import com.example.jetpackcomposeapp.data.models.RestaurantListResponse
import retrofit2.Call
import retrofit2.http.GET

interface DelivericiousApiService {


    //banners list
    @GET("f30e16d0-92b8-4f5f-899d-332d7419b8ca")
    fun getBanners():Call<BannersReponse>

    //restaurants list
    @GET("def344c4-0172-4d5e-bd9f-cae1b1ca9ad7")
    fun getRestaurantList():Call<RestaurantListResponse>

}