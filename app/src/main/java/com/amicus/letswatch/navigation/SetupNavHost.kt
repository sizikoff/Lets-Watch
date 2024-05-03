package com.amicus.letswatch.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amicus.letswatch.Screens.MainScreen
import com.amicus.letswatch.Screens.SplashScreen
import com.amicus.letswatch.utils.Constants


sealed class Screens(val route:String){
    object Splash:Screens(route = Constants.Screen.SPLASH_SCREEN)
    object Main:Screens(route = Constants.Screen.MAIN_SCREEN)
    object Details:Screens(route = Constants.Screen.DETAIL_SCREEN)
}
@Composable
fun SetupNavHost(navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = Screens.Splash.route
    ){
        composable(route = Screens.Splash.route){
        SplashScreen(navController = navController)
        }
        composable(route = Screens.Main.route){
        MainScreen()
        }
        composable(route = Screens.Details.route){

        }

    }
}