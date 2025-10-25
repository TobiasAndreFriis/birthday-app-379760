package com.example.birthday_app_379760.ui.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp), // Legger til litt padding for bedre layout
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

@Preview(showBackground = true)
@Composable
fun PreviewPreferanseSide() {
    // Mock WorkViewModel for Preview
    val mockWorkViewModel = object : WorkViewModel(Application()) {
        override fun start() {
            // Eksempel: Logikk kan være tom
        }

        override fun stop() {
            // Eksempel: Logikk kan være tom
        }
    }

    // Forhåndsvisning av PreferanseSide
    PreferanseSide(
        navController = NavController(LocalContext.current),
        viewModel = mockWorkViewModel
    )
}