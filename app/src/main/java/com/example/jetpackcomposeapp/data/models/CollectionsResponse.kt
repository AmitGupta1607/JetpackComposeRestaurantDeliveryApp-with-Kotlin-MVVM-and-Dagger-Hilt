package com.example.jetpackcomposeapp.data.models

data class CollectionsResponse(var data:ArrayList<RestaurantsItemList>){


    data class RestaurantsItemList(var title:String,var restaurants:ArrayList<RestaurantItem>)

    data class RestaurantItem(var name:String, var cuisines:String,var price:String,
                              var imageUrl:String,var rating:String)

}

