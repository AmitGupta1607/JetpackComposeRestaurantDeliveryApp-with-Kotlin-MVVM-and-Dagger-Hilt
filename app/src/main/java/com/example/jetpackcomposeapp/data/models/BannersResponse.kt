package com.example.jetpackcomposeapp.data.models

data class BannersReponse(var banners:ArrayList<WeekDayBannerItem>) {
    data class WeekDayBannerItem(var title: String, var subtitle: String, var image: String)
}