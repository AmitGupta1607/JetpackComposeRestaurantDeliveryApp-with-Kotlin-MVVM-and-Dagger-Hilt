package com.example.jetpackcomposeapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackcomposeapp.data.apiService.DelivericiousApiService
import com.example.jetpackcomposeapp.data.models.BannersReponse
import com.example.jetpackcomposeapp.data.models.CollectionsResponse
import com.example.jetpackcomposeapp.data.models.RestaurantListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RepositoryImpl(var retrofit:Retrofit) : Repository {

    var bannersReponseLiveData=
        MutableLiveData<BannersReponse>()

    var apiService:DelivericiousApiService

    init {
        apiService = retrofit.create(DelivericiousApiService::class.java)
    }

    override fun fetchBanners():LiveData<BannersReponse> {
        apiService
            .getBanners().enqueue(object :Callback<BannersReponse>{
                override fun onResponse(
                    call: Call<BannersReponse>?,
                    response: Response<BannersReponse>?
                ) {
                    bannersReponseLiveData.postValue(response?.body())
                }

                override fun onFailure(call: Call<BannersReponse>?, t: Throwable?) {

                }
            })

        return bannersReponseLiveData
    }

    override fun fetchAllRestaurantsForLocation() :LiveData<RestaurantListResponse>{
        val mutableLiveDataRestaurantList = MutableLiveData<RestaurantListResponse>()
        apiService.getRestaurantList().enqueue(object :Callback<RestaurantListResponse>{
            override fun onResponse(
                call: Call<RestaurantListResponse>?,
                response: Response<RestaurantListResponse>?
            ) {
                mutableLiveDataRestaurantList.postValue(response?.body())
                Log.v("AAAA","Success Restauant")
            }

            override fun onFailure(call: Call<RestaurantListResponse>?, t: Throwable?) {
                Log.v("AAAA","Failure Restauant")
            }
        })
        return mutableLiveDataRestaurantList
    }

    override fun fetchCollections(): LiveData<CollectionsResponse> {
        val mutableLiveData = MutableLiveData<CollectionsResponse>()
        apiService.getCollectionsList().enqueue(object :Callback<CollectionsResponse>{
            override fun onResponse(
                call: Call<CollectionsResponse>?,
                response: Response<CollectionsResponse>?
            ) {
                val body = response!!.body()
                mutableLiveData.postValue(body)
            }

            override fun onFailure(call: Call<CollectionsResponse>?, t: Throwable?) {
            }
        })

        return mutableLiveData

    }
}