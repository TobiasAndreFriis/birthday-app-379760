package com.example.birthday_app_379760.ui.screens

import android.app.Person
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun VennerSide(persons: List<Person>, onPersonClick: (Person) -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        persons.forEach { person ->
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .clickable { onPersonClick(person) }
            ) {Column(Modifier.padding(12.dp)) {
                Text(text = person.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Age: ${person.age}")
            }
            }
        }
    }
}