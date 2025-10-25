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

@Composable
fun BursdagsApp(
    modifier: Modifier = Modifier, // Gjør Modifier valgfri for lettere bruk
    vennerViewModel: VennerViewModel,
    workViewModel: WorkViewModel
) {
    val navController = rememberNavController()


    // Start navigasjonen
    NavHost(
        navController = navController,
        startDestination = "venner_list",
        modifier = modifier
    ) {
        // Hovedskjermen som viser listen over venner
        composable("venner_list") {
            // Pakk skjermen i en nøkkel for å tvinge reinitialisering
            VennerSide(
                friends = vennerViewModel.venner.collectAsState().value, // Hent venner fra ViewModel
                onFriendClick = { friend -> // Naviger til detaljskjerm når en venn trykkes på
                    navController.navigate("venner_details/${friend.id}")
                },
                onAddFriendClick = {
                    navController.navigate("legg_til_venn") // Naviger til "Legg til venn"-skjerm
                },
                onPreferencesClick = {
                    navController.navigate("preferanse_side") // Naviger til preferansesiden
                }
            )
        }

        // Skjerm for å legge til en ny venn
        composable("legg_til_venn") {
            LeggTilVennSide(
                onAddFriend = { newFriend ->
                    vennerViewModel.addVenn(
                        name = newFriend.name,
                        birthDay = newFriend.birthDay,
                        birthMonth = newFriend.birthMonth,
                        telephoneNr = newFriend.telephoneNr,
                        message = newFriend.message
                    )
                    navController.popBackStack() // Gå tilbake til listen
                },
                onNavigateBack = { navController.popBackStack() } // Gå tilbake uten å legge til
            )
        }

        // Detaljskjerm for en spesifikk venn
        composable(
            route = "venner_details/{friendId}",
            arguments = listOf(navArgument("friendId") { type = NavType.IntType })
        ) { backStackEntry ->
            val friendId = backStackEntry.arguments?.getInt("friendId")
            val friend = vennerViewModel.venner.collectAsState().value
                .find { it.id == friendId } // Finn vennen med riktig ID
            friend?.let {
                VennerDetaljer(
                    friend = it,
                    onUpdateFriend = { updatedFriend ->
                        vennerViewModel.updateVenn(updatedFriend) // Oppdater venn i ViewModel
                    },
                    onDeleteFriend = { deletedFriend ->
                        vennerViewModel.deleteVenn(deletedFriend) // Slett venn i ViewModel
                        navController.popBackStack() // Gå tilbake til listen
                    },
                    onNavigateBack = { navController.popBackStack() } // Tilbakeknapp
                )
            }
        }

        // Preferansesiden
        composable("preferanse_side") {
            PreferanseSide(
                navController = navController,
                viewModel = workViewModel
            )
        }
    }
}