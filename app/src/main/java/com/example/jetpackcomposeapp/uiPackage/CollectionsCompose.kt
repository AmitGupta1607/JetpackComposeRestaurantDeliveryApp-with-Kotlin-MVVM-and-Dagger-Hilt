package com.example.jetpackcomposeapp.uiPackage


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeapp.R
import com.example.jetpackcomposeapp.data.models.CollectionsResponse
import com.example.jetpackcomposeapp.data.models.RestaurantListResponse
import com.example.jetpackcomposeapp.font.FontConstants
import dev.chrisbanes.accompanist.coil.CoilImage


class CollectionsCompose {

    @Composable
    public fun showCatalogScreen(mainActivityViewModel: MainActivityViewModel){
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 250.dp)
            ) {
                showProgress(mainActivityViewModel.loading.value)
            }
            Column {
                showCollections(mainActivityViewModel = mainActivityViewModel)
            }
        }
    }


    @Composable
    fun showCollections(mainActivityViewModel: MainActivityViewModel){
        val collectionResponse by mainActivityViewModel.getCollectionsList().observeAsState()
        if(collectionResponse!=null){
            showCollectionsList(collectionsResponse = collectionResponse!!)
        }
    }

    @Composable
    fun showCollectionsList(collectionsResponse: CollectionsResponse){
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            for(item in collectionsResponse.data) {
                getCollectionItems(item)
            }
        }

    }

    @Composable
    fun getCollectionItems(restaurantCollection:CollectionsResponse.RestaurantsItemList){

        Column(modifier = Modifier
            .padding(top = 20.dp, start = 25.dp, end = 10.dp,bottom = 20.dp)
            .fillMaxWidth()) {
            Text(text = restaurantCollection.title, style = TextStyle(fontWeight = FontWeight.Bold,
            fontFamily = FontConstants.fontFamilyPoppins,fontSize = 17.sp),color=Color.Black)

            LazyRow(modifier = Modifier.padding(top=10.dp)) {
                items(restaurantCollection.restaurants) { item ->
                    getRestaurantCardItem(restaurantListItem = item)
                }
            }

        }

    }

    @Composable

    fun getRestaurantCardItem(restaurantListItem: CollectionsResponse.RestaurantItem){
        Card(modifier = Modifier.padding(start = 10.dp,end = 10.dp,top=20.dp,bottom = 10.dp)
            .width(350.dp).height(300.dp)
            ,elevation = 3.dp) {

            Column() {
                CoilImage(
                    data = restaurantListItem.imageUrl, modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .border(
                            shape = RoundedCornerShape(30),
                            color = androidx.compose.ui.graphics.Color.Transparent,
                            width = 0.dp
                        ),
                    contentDescription = restaurantListItem.cuisines,
                    contentScale = ContentScale.Crop
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = restaurantListItem.name, style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            color = androidx.compose.ui.graphics.Color.Black
                        ),
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp)
                    )
                    Text(
                        text = restaurantListItem.rating,
                        Modifier
                            .padding(end = 20.dp, top = 10.dp)
                            .height(20.dp),
                        style = TextStyle(
                            color = colorResource(id = R.color.green), fontWeight = FontWeight.Bold,
                            fontFamily = FontConstants.fontFamilyPoppins, fontSize = 15.sp
                        ),

                        )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp, bottom = 25.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        restaurantListItem.cuisines, color = Color(0xFF717171),
                        modifier = Modifier.padding( start = 20.dp,end=20.dp),
                        style = TextStyle(fontWeight = FontWeight.Normal,fontSize = 15.sp,
                            fontFamily = FontConstants.fontFamilyPoppins),
                        maxLines = 1

                    )
                    Text(
                        text = restaurantListItem.price,
                        style = TextStyle(fontWeight = FontWeight.Normal,fontFamily = FontConstants.fontFamilyPoppins,
                            color = androidx.compose.ui.graphics.Color.Black,fontSize = 14.sp)
                    )

                }
            }
        }
    }



}