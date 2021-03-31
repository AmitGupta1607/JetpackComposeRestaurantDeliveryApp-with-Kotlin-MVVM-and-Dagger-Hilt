package com.example.jetpackcomposeapp.uiPackage

import android.os.Bundle
import android.widget.HorizontalScrollView
import android.widget.StackView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.imageLoader
import com.example.jetpackcomposeapp.AppColors
import com.example.jetpackcomposeapp.R
import com.example.jetpackcomposeapp.data.models.BannersReponse
import com.example.jetpackcomposeapp.data.models.RestaurantListResponse
import com.example.jetpackcomposeapp.font.FontConstants
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.coil.CoilImage

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var mainActivityViewModel:MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)


        setContent {

            JetpackComposeAppTheme {

                // A surface container using the 'background' color from the theme

                Surface(color = MaterialTheme.colors.background,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()) {
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()

                    ){
                    Column (){
                        getToolbar()
                        getSearchCard()
                        getFiltersList()
//                            showBanners(mainActivityViewModel = mainActivityViewModel)
                            showRestaurantsList(mainActivityViewModel = mainActivityViewModel)
                    }

                }
            }
            }
        }



    }
}

 @Composable
 fun showBanners(mainActivityViewModel: MainActivityViewModel){
  val bannerResponse by mainActivityViewModel.getBanners().observeAsState()
     if(bannerResponse!=null) {
         getBannersList(bannerResponse = bannerResponse!!)
     }
 }

 @Composable
  fun showRestaurantsList(mainActivityViewModel: MainActivityViewModel){
      val restaurantsResponse by mainActivityViewModel.getRestaurantList().observeAsState()
      if(restaurantsResponse!=null){
          showRestaurantsList(restaurantListResponse = restaurantsResponse!!,mainActivityViewModel = mainActivityViewModel)
      }
  }

@Composable
fun getBannersList(bannerResponse: BannersReponse){
    val listBanners = bannerResponse.banners
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        for( banner in listBanners) {
            getBannerView(
                title = banner.title, description = banner.subtitle,
                urlImage = banner.image
            )

        }
    }
}

@Composable
fun showRestaurantsList( restaurantListResponse:RestaurantListResponse,mainActivityViewModel: MainActivityViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)

    ) {
        item {
            showBanners(mainActivityViewModel = mainActivityViewModel)
        }
        item{
            Text(text= stringResource(R.string.restaurants_near_you),
            style = TextStyle(fontWeight = FontWeight.Bold,color=Color.Black,
            fontFamily = FontConstants.fontFamilyPoppins,fontSize = 17.sp),
            modifier = Modifier.padding(top=30.dp,bottom = 5.dp,start = 10.dp))
        }
        items(restaurantListResponse.restaurants) { item ->
            getRestaurantCardItem(restaurantListItem = item)
        }
    }
}




@Composable
fun getToolbar(){
    TopAppBar(
        title = {
            Text("Delivericious")
        },
        navigationIcon = {
            // navigation icon is use
            // for drawer icon.
            IconButton(onClick = { }) {
                // below line is use to
                // specify navigation icon.
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Back Arrow")
            }
        },
        backgroundColor = Color.White,
        contentColor=Color.Black,
        elevation = 10.dp

    )
}

@Composable
fun getSearchCard(){
    Card(modifier = Modifier
        .padding(12.dp)
        .height(50.dp)
        .fillMaxWidth(),elevation = 4.dp,
    shape = RoundedCornerShape(40)){
        Row(modifier = Modifier.padding(10.dp)) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search",
            tint=Color.Red,modifier = Modifier.padding(top=2.dp,start = 10.dp))
            Text(stringResource(R.string.search_bar_title),
                style = TextStyle(fontWeight = FontWeight.Bold,
            color = Color(0xffc0c0c0),fontSize = 16.sp,fontFamily = FontFamily.SansSerif),
            modifier = Modifier.padding(start = 10.dp,top=2.dp))
        }
    }

}

@Composable
fun getFiltersList(){
    val filters = arrayOf<String>("Cuisines","Rating-4+","Fastest Delivery","TakeAway","Free Delivery",
    "Buy 1 Get 1")

    
    Row(
        modifier = Modifier
            .padding(top = 5.dp, start = 5.dp)
            .width(1000.dp)
            .horizontalScroll(rememberScrollState()),
    ) {
        for( filter in filters){
            getFilter(filter)
        }
    }


}


@Composable
fun getRestaurantCardItem(restaurantListItem:RestaurantListResponse.RestaurantListItem){
    Card(modifier = Modifier.padding(start = 1.dp,end = 1.dp,top=20.dp)
        ,elevation = 5.dp) {

        Column() {


            CoilImage(
                data = restaurantListItem.imageUrl, modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(
                        shape = RoundedCornerShape(30), color = Color.Transparent,
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
                        color = Color.Black
                        ),
                    modifier = Modifier.padding(top = 10.dp, start = 20.dp)
                )
                Text(
                    text = restaurantListItem.rating,
                    Modifier
                        .padding(end = 20.dp,top=10.dp)
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
                    .padding(top = 2.dp,bottom=25.dp,end=20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    restaurantListItem.cuisines, color = Color(0xFF717171),
                    modifier = Modifier.padding( start = 20.dp,end=20.dp),
                    style = TextStyle(fontWeight = FontWeight.Normal,fontSize = 15.sp,
                    fontFamily = FontConstants.fontFamilyPoppins)
                )
                Text(
                    text = restaurantListItem.price,
                    style = TextStyle(fontWeight = FontWeight.Normal,fontFamily = FontConstants.fontFamilyPoppins,
                    color = Color.Black,fontSize = 14.sp)
                )

            }
        }
        }

}

@Composable
fun getBannerView(title:String,description:String,urlImage:String){
    Box(modifier = Modifier
        .width(250.dp)
        .height(300.dp)
        .padding(
            top = 20.dp,
            start = 10.dp,
            end = 10.dp
        ),){



        CoilImage(data = urlImage,
            contentScale = ContentScale.Crop
            ,contentDescription = "Image",
        modifier = Modifier
            .width(250.dp)
            .height(350.dp)
            .background(
                shape = RoundedCornerShape(3),
                color = Color.Transparent
            )
            .clip(RoundedCornerShape(8)),
        )
        Column() {
            Text(title,style = TextStyle(color=Color.White,fontWeight = FontWeight.Bold,
            fontFamily = FontConstants.fontFamilyPoppins),
                fontSize = 30.sp,modifier = Modifier.padding(start=10.dp,top = 10.dp,end=15.dp))
            Text(description,
                style = TextStyle(color=Color.White,fontWeight = FontWeight.Medium,
                    fontFamily = FontConstants.fontFamilyPoppins),
                fontSize = 15.sp,modifier = Modifier.padding(start=10.dp,top = 15.dp))
        }



    }
}

@Composable
fun getFilter(filter:String){
    Box(modifier = Modifier
        .padding(start = 10.dp, bottom = 5.dp)
        .border(width = 0.7.dp, color = Color(0xFFc0c0c0), shape = RoundedCornerShape(25))
       ){
        Text(filter,modifier = Modifier.padding(start = 7.dp,top=4.dp,end = 7.dp,bottom =4.dp),
        style= TextStyle(fontWeight = FontWeight.SemiBold,color = AppColors.filterTextColor),
            fontSize = 12.sp,fontFamily = FontConstants.fontFamilyPoppins)


    }
}

