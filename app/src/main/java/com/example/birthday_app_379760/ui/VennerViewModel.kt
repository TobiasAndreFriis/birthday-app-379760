package com.example.birthday_app_379760.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.birthday_app_379760.data.Venner
import com.example.birthday_app_379760.repositories.VennerRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VennerViewModel(private val repository: VennerRepository, application: Application) : AndroidViewModel(application) {

    // Bruk StateFlow for å holde listen over venner
    private val _venner = MutableStateFlow<List<Venner>>(emptyList())
    val venner = _venner.asStateFlow()

    init {
        viewModelScope.launch {
            repository.alleVenner.collect { friends ->
                _venner.value = friends // Oppdater StateFlow når dataene endrer seg
                Log.d("VennerViewModel", "Data oppdatert: ${friends.size} venner")
            }
        }
    }

    fun loadVenner() {
        viewModelScope.launch {
            repository.alleVenner.collect { friends ->
                _venner.value = friends // Oppdaterer listen i StateFlow
                Log.d("VennerViewModel", "Data oppdatert: ${friends.size} venner")
            }
        }
    }

    fun addVenn(name: String, birthDay: Int, birthMonth: Int, telephoneNr: String, message: String) {
        viewModelScope.launch {
            try {
                val nyVenn = Venner(
                    name = name,
                    birthDay = birthDay,
                    birthMonth = birthMonth,
                    telephoneNr = telephoneNr,
                    message = message
                )
                repository.insert(nyVenn)
                loadVenner()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateVenn(venner: Venner) {
        viewModelScope.launch {
            try {
                repository.update(venner)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteVenn(venner: Venner) {
        viewModelScope.launch {
            try {
                repository.delete(venner)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}