package com.example.birthday_app_379760.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.birthday_app_379760.ui.StartViewModel
import com.example.birthday_app_379760.ui.screens.PreferanseSide
import com.example.birthday_app_379760.ui.screens.StartSide

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "start") {    // Endre til "start" når det integreres
        composable("start") { StartSide(navController = navController) }
        composable("preferanser") { PreferanseSide(navController = navController)}
    }
}