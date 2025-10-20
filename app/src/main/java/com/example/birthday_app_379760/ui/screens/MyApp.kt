package com.example.birthday_app_379760.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.birthday_app_379760.ui.navigation.NavigationGraph

@Composable
fun MyApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavigationGraph(navController = navController, modifier = modifier)
}