package com.example.birthday_app_379760

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.birthday_app_379760.data.AppDatabase
import com.example.birthday_app_379760.repositories.VennerRepository
import com.example.birthday_app_379760.ui.VennerViewModel
import com.example.birthday_app_379760.ui.WorkViewModel
import com.example.birthday_app_379760.ui.screens.BursdagsApp
import com.example.birthday_app_379760.ui.theme.Birthdayapp379760Theme



class MainActivity : ComponentActivity() {
    private val beomSmstillatelse = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {isGranted: Boolean ->
        if (isGranted) {
            Log.d("SMS","Tillatelse til å sende SMS")
        } else {
            Log.d("SMS","Ikke tillatelse til å sende SMS")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "venner_db"
        ).build()
        val repository = VennerRepository(db.vennerDao())
        val vennerViewModel= VennerViewModel(repository, application)
        val workViewModel = WorkViewModel(application)
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
            == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("SMS","Tillatelse til å sende SMS")
        } else
        {
            beomSmstillatelse.launch(Manifest.permission.SEND_SMS)
        }
        setContent {
            BursdagsApp(modifier = Modifier,vennerViewModel, workViewModel)
        }
    }
}