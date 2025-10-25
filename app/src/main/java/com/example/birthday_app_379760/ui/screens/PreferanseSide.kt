package com.example.birthday_app_379760.ui.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.birthday_app_379760.ui.WorkViewModel

@Composable
fun PreferanseSide(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: WorkViewModel
) {
    // Observer tjenestestatus fra ViewModel
    val isServiceActive = viewModel.isServiceRunning.collectAsState().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp), // Legger til litt padding for bedre layout
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Viser status for tjenesten
        Text(
            text = if (isServiceActive) "Service er AKTIV" else "Service er INAKTIV",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Kjør service-knappen
        Button(onClick = { viewModel.start() }) {
            Text(text = "Kjør service")
        }

        // Stopp service-knappen
        Button(
            onClick = { viewModel.stop() },
            modifier = Modifier.padding(top = 16.dp) // Litt mellomrom mellom knappene
        ) {
            Text(text = "Stopp service")
        }

        // Tilbake-knappen
        Button(
            onClick = { navController.popBackStack() }, // Naviger tilbake til forrige skjerm
            modifier = Modifier.padding(top = 32.dp) // Ekstra mellomrom over tilbake-knappen
        ) {
            Text(text = "Tilbake")
        }
    }
}


