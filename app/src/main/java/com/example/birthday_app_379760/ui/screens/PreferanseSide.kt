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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.birthday_app_379760.R
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
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Viser status for tjenesten
        Text(
            text = if (isServiceActive) stringResource(R.string.sms_service_aktiv) else stringResource(R.string.sms_service_inaktiv),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Kjør service-knappen
        Button(onClick = { viewModel.start() }) {
            Text(text = stringResource(R.string.kjør_sms_service))
        }

        // Stopp service-knappen
        Button(
            onClick = { viewModel.stop() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = stringResource(R.string.stopp_sms_service))
        }

        // Tilbake-knappen
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Text(text = stringResource(R.string.tilbake))
        }
    }
}


