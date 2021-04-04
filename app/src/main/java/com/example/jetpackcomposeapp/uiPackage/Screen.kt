package com.example.jetpackcomposeapp.uiPackage

import android.widget.ImageView
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorApplier

sealed class Screen (val route:String,val label:String,val icon:ImageVector){
    object Home :Screen("home","Home", Icons.Default.Home)
    object Catalogue:Screen("catalogue","Catalogue",Icons.Default.List)
    object Account:Screen("account","Account",Icons.Default.AccountBox)
}
