package com.example.birthday_app_379760.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.birthday_app_379760.ui.WorkViewModel

@Composable
fun PreferanseSide(navController: NavController, modifier: Modifier = Modifier, viewModel: WorkViewModel){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {Button(onClick = { viewModel.start()}) {
        Text(text = "Kjør service")
    }
        Button(onClick = {viewModel.stop()}){
            Text(text="Stopp service")
        }
    }
}