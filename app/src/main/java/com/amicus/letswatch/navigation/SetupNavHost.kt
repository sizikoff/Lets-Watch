package com.amicus.letswatch.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amicus.letswatch.MainViewModel
import com.amicus.letswatch.screens.DetailScreen
import com.amicus.letswatch.screens.MainScreen
import com.amicus.letswatch.utils.Constants


sealed class Screens(var route:String){
    object Main:Screens(route = Constants.Screen.MAIN_SCREEN)
    object Details:Screens(route = Constants.Screen.DETAIL_SCREEN)
}
@Composable
fun SetupNavHost(navController: NavHostController,viewModel: MainViewModel) {
    NavHost(navController = navController,
        startDestination = Screens.Main.route
    ){
        composable(route = Screens.Main.route){
        MainScreen(navController = navController,viewModel = viewModel)
        }
        composable(route = Screens.Details.route + "/{Id}"){ backStackEntry->
        DetailScreen(viewModel = viewModel, itemId = backStackEntry.arguments?.getString("Id")?: "1")
        }
    }
}