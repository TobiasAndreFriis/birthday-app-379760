package com.example.birthday_app_379760.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.birthday_app_379760.ui.VennerViewModel
import com.example.birthday_app_379760.ui.WorkViewModel
import androidx.compose.runtime.key
import com.example.birthday_app_379760.ui.navigation.NavigationGraph

@Composable
fun BursdagsApp(
    modifier: Modifier = Modifier,
    vennerViewModel: VennerViewModel,
    workViewModel: WorkViewModel
) {
    val navController = rememberNavController() // Opprett NavController

    // Kall NavigationGraph og send inn nødvendig informasjon
    NavigationGraph(
        navController = navController,
        modifier = modifier,
        vennerViewModel = vennerViewModel,
        workViewModel = workViewModel
    )
}