package com.example.birthday_app_379760.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.birthday_app_379760.ui.VennerViewModel

@Composable
fun BursdagsApp(modifier: Modifier,viewModel: VennerViewModel) {
    val navController = rememberNavController()
    val persons: Unit = viewModel.addSamplePerson()
    NavHost(navController, startDestination = "list") {
        composable("list") {
            VennerSide(
                persons = viewModel.persons.collectAsState().value,
                onPersonClick = { person ->
                    navController.navigate("detail/${person.id}")
                }
            )
        }
        composable(
            route = "detail/{personId}",
            arguments = listOf(navArgument("personId") { type =
                NavType.IntType })
        ) { backStackEntry ->
            val personId = backStackEntry.arguments?.getInt("personId")
            val person = viewModel.persons.collectAsState().value
                .find { it.id == personId }
            person?.let {
                PersonDetailScreen(it)
            }
        }
    }
}