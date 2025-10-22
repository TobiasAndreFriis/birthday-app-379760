package com.example.birthday_app_379760.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.birthday_app_379760.ui.screens.PreferanseSide
import com.example.birthday_app_379760.ui.screens.VennerSide

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "venner") {    // Endre til "start" når det integreres
        composable("venner") { VennerSide(navController = navController) }
        composable("preferanser") { PreferanseSide(navController = navController)}
    }
}