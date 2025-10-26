package com.example.birthday_app_379760.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.birthday_app_379760.data.Venner
import com.example.birthday_app_379760.R

@Composable
fun VennerSide(
    friends: List<Venner>,
    onFriendClick: (Venner) -> Unit,
    onAddFriendClick: () -> Unit,
    onPreferencesClick: () -> Unit
) {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            // Knapp for å navigere til PreferanseSide
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                // Naviger til preferansesiden
                Button(
                    onClick = onPreferencesClick,
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                ) {
                    Text(text = stringResource(R.string.preferanser))
                }

                // Naviger til legg til ny venn-siden
                Button(
                    onClick = onAddFriendClick,
                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                ) {
                    Text(text = stringResource(R.string.legg_til_venn))
                }
            }

            // Sjekk om listen over venner er tom
            if (friends.isEmpty()) {
                Text(
                    text = stringResource(R.string.ingen_venner),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                // Vis listen over venner
                friends.forEach { friend ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .clickable { onFriendClick(friend) }
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = friend.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "${stringResource(R.string.telefonnummer)}: ${friend.telephoneNr}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "${stringResource(R.string.bursdag)}: ${friend.birthDay}.${friend.birthMonth}",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVennerSide() {
    // Eksempler på venner
    val exampleFriends = listOf(
        Venner(id = 1, name = "Ola Nordmann", birthDay = 25, birthMonth = 12, telephoneNr = "12345678", message = "Gratulerer med dagen!"),
        Venner(id = 2, name = "Kari Nordmann", birthDay = 15, birthMonth = 7, telephoneNr = "87654321", message = "Håper du får en fantastisk dag!"),
        Venner(id = 3, name = "Per Hansen", birthDay = 10, birthMonth = 11, telephoneNr = "11223344", message = "Ha en fin dag!")
    )

    // Forhåndsvisning av VennerSide
    VennerSide(
        friends = exampleFriends,
        onFriendClick = { friend -> /* Simuler klikk på en venn */ },
        onAddFriendClick = { /* Simuler navigering til "Legg til venn" */ },
        onPreferencesClick = { /* Simuler navigering til "Preferanser" */ }
    )
}