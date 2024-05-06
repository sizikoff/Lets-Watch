package com.amicus.letswatch.Screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.amicus.letswatch.MainViewModel

@Composable
fun DetailScreen(navController: NavHostController, viewModel: MainViewModel,itemId:String) {
    Text(text = itemId)
}