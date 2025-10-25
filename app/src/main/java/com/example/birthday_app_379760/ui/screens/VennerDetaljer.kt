package com.example.birthday_app_379760.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.birthday_app_379760.data.Venner

@Composable
fun VennerDetaljer(
    friend: Venner,
    onUpdateFriend: (Venner) -> Unit, // Funksjon for å oppdatere en venn
    onDeleteFriend: (Venner) -> Unit, // Funksjon for å slette en venn
    onNavigateBack: () -> Unit // Funksjon for å gå tilbake
) {
    var name by remember { mutableStateOf(friend.name) }
    var phoneNumber by remember { mutableStateOf(friend.telephoneNr) }
    var birthDay by remember { mutableStateOf(friend.birthDay.toString()) }
    var birthMonth by remember { mutableStateOf(friend.birthMonth.toString()) }
    var message by remember { mutableStateOf(friend.message) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Navn") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Telefonnummer") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        TextField(
            value = birthDay,
            onValueChange = { birthDay = it },
            label = { Text("Bursdag (Dag)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        TextField(
            value = birthMonth,
            onValueChange = { birthMonth = it },
            label = { Text("Bursdag (Måned)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        TextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Melding (valgfritt)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                val updatedFriend = friend.copy(
                    name = name,
                    telephoneNr = phoneNumber,
                    birthDay = birthDay.toIntOrNull() ?: friend.birthDay,
                    birthMonth = birthMonth.toIntOrNull() ?: friend.birthMonth,
                    message = message
                )
                onUpdateFriend(updatedFriend) // Oppdaterer vennen via ViewModel
                onNavigateBack() // Gå tilbake til forrige skjerm
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(text = "Oppdater venn")
        }

        Button(
            onClick = {
                onDeleteFriend(friend) // Sletter vennen via ViewModel
                onNavigateBack() // Gå tilbake til listen
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(text = "Slett venn")
        }

        Button(
            onClick = { onNavigateBack() }, // Naviger tilbake
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Tilbake")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVennerDetaljer() {
    // Eksempeldatavenn
    val exampleFriend = Venner(
        id = 1,
        name = "Ola Nordmann",
        birthDay = 25,
        birthMonth = 12,
        telephoneNr = "12345678",
        message = "Gratulerer med dagen!"
    )

    // Forhåndsvisning av VennerDetaljer
    VennerDetaljer(
        friend = exampleFriend,
        onUpdateFriend = { updatedFriend ->
            println("Oppdatert venn: $updatedFriend") // Simuler oppdatering i Preview
        },
        onDeleteFriend = { deletedFriend ->
            println("Slettet venn: $deletedFriend") // Simuler sletting i Preview
        },
        onNavigateBack = {
            println("Naviger tilbake") // Simuler tilbake-navigasjon i Preview
        }
    )
}