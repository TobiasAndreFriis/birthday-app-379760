package com.example.birthday_app_379760.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.birthday_app_379760.ui.VennerViewModel

@Composable
fun VennerApp(vm: VennerViewModel) {
    val venner by vm.venner.collectAsState()
    var name by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf("") }
    var telephoneNr by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Navn") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = birthday,
            onValueChange = { birthday = it },
            label = { Text("Fødsels dato") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = telephoneNr,
            onValueChange = { telephoneNr = it },
            label = { Text("Telefon nummer") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (name.isNotBlank() && birthday.isNotBlank()) {
                    vm.addVenn(name, birthday, telephoneNr)
                    name = ""
                    birthday = ""
                    telephoneNr = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {Text("Legg til venn")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(venner) { venner ->
                Text("Navn: ${venner.name}, Fødsels dato: ${venner.birthday}, Telefon nummer: ${venner.telephoneNr}")
                Divider()
            }
        }
    }
}