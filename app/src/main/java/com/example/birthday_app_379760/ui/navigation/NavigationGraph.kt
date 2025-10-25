package com.example.birthday_app_379760.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.birthday_app_379760.ui.VennerViewModel
import com.example.birthday_app_379760.ui.WorkViewModel
import com.example.birthday_app_379760.ui.screens.LeggTilVennSide
import com.example.birthday_app_379760.ui.screens.PreferanseSide
import com.example.birthday_app_379760.ui.screens.VennerDetaljer
import com.example.birthday_app_379760.ui.screens.VennerSide

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    vennerViewModel: VennerViewModel,
    workViewModel: WorkViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "venner_list",
        modifier = modifier
    ) {
        // Rute for listen over venner
        composable("venner_list") {
            VennerSide(
                friends = vennerViewModel.venner.collectAsState().value, // Henter venner fra ViewModel
                onFriendClick = { friend ->
                    navController.navigate("venner_details/${friend.id}") // Naviger til detaljskjerm
                },
                onAddFriendClick = {
                    navController.navigate("legg_til_venn") // Naviger til "Legg til venn"-skjerm
                },
                onPreferencesClick = {
                    navController.navigate("preferanse_side") // Naviger til preferansesiden
                }
            )
        }

        // Rute for preferansesiden
        composable("preferanse_side") {
            PreferanseSide(
                navController = navController,
                viewModel = workViewModel
            )
        }

        // Rute for detaljskjermen
        composable(
            route = "venner_details/{friendId}",
            arguments = listOf(navArgument("friendId") { type = androidx.navigation.NavType.IntType })
        ) { backStackEntry ->
            val friendId = backStackEntry.arguments?.getInt("friendId")
            val friend = vennerViewModel.venner.collectAsState().value
                .find { it.id == friendId } // Finn vennen basert på ID
            friend?.let {
                VennerDetaljer(
                    friend = it,
                    onUpdateFriend = { updatedFriend ->
                        vennerViewModel.updateVenn(updatedFriend) // Oppdater vennen i ViewModel
                    },
                    onNavigateBack = { navController.popBackStack() } // Gå tilbake til listen
                )
            }
        }

        // Rute for legg til venn-siden
        composable("legg_til_venn") {
            LeggTilVennSide(
                onAddFriend = { newFriend ->
                    vennerViewModel.addVenn(
                        name = newFriend.name,
                        birthDay = newFriend.birthDay,
                        birthMonth = newFriend.birthMonth,
                        telephoneNr = newFriend.telephoneNr,
                        message = newFriend.message
                    ) // Kaller `addVenn` i ViewModel for å legge til venn
                    navController.popBackStack() // Gå tilbake til listen etter å ha lagt til en venn
                },
                onNavigateBack = { navController.popBackStack() } // Gå tilbake uten å legge til
            )
        }
    }
}