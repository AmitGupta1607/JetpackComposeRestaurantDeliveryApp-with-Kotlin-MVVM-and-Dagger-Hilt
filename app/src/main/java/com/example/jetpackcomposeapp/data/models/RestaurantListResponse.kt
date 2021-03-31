package com.example.jetpackcomposeapp.data.models

data class RestaurantListResponse (var restaurants:ArrayList<RestaurantListItem>){
    data class RestaurantListItem(var name:String, var cuisines:String,var price:String,
              var imageUrl:String,var rating:String)
}