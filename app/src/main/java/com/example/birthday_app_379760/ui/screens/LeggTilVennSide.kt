package com.example.birthday_app_379760.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.birthday_app_379760.data.Venner

@Composable
fun LeggTilVennSide(
    onAddFriend: (Venner) -> Unit,
    onNavigateBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var birthDay by remember { mutableStateOf("") }
    var birthMonth by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input for navn
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Navn") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Input for telefonnummer
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Telefonnummer") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Input for bursdag (dag)
        TextField(
            value = birthDay,
            onValueChange = { birthDay = it },
            label = { Text("Bursdag (Dag)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Input for bursdag (måned)
        TextField(
            value = birthMonth,
            onValueChange = { birthMonth = it },
            label = { Text("Bursdag (Måned)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Input for melding
        TextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Melding (valgfritt)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        // Legg til venn-knapp
        Button(
            onClick = {
                if (name.isNotBlank() && phoneNumber.isNotBlank() && birthDay.isNotBlank() && birthMonth.isNotBlank()) {
                    val newFriend = Venner(
                        name = name,
                        telephoneNr = phoneNumber,
                        birthDay = birthDay.toIntOrNull() ?: 0,
                        birthMonth = birthMonth.toIntOrNull() ?: 0,
                        message = message.ifBlank { "Gratulerer med dagen!" }
                    )
                    onAddFriend(newFriend)
                    onNavigateBack()
                }
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(text = "Legg til venn")
        }

        // Tilbake-knapp
        Button(
            onClick = onNavigateBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Tilbake")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLeggTilVennSide() {
    LeggTilVennSide(
        onAddFriend = { newFriend ->
            // Simuler at en venn er lagt til (ingen faktisk lagring i Preview)
        },
        onNavigateBack = {
            // Simuler tilbake-navigasjon
        }
    )
}