package com.example.jetpackcomposeapp.font

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.jetpackcomposeapp.R

object FontConstants {


   val fontFamilyPoppins = FontFamily(
      Font(R.font.poppins_bold, FontWeight.Bold),
      Font(R.font.poppins_semibold,FontWeight.SemiBold),
      Font(R.font.poppins_regular,FontWeight.Normal),
      Font(R.font.poppins_medium,FontWeight.Medium)
   )
}