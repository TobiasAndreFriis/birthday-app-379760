package com.example.birthday_app_379760.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.birthday_app_379760.data.Venner
import com.example.birthday_app_379760.repositories.VennerRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VennerViewModel(private val repository: VennerRepository, application: Application) : AndroidViewModel(application) {

    // Live data for all venner (reactive updates)
    val venner: StateFlow<List<Venner>> =
        repository.alleVenner.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )

    // Legg til en ny venn
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
            } catch (e: Exception) {
                // Legg til logging eller feilhåndtering her
                e.printStackTrace()
            }
        }
    }

    // Oppdater data for en venn
    fun updateVenn(venner: Venner) {
        viewModelScope.launch {
            try {
                repository.update(venner)
            } catch (e: Exception) {
                // Legg til logging eller feilhåndtering her
                e.printStackTrace()
            }
        }
    }

    // Slett en venn
    fun deleteVenn(venner: Venner) {
        viewModelScope.launch {
            try {
                repository.delete(venner)
            } catch (e: Exception) {
                // Legg til logging eller feilhåndtering her
                e.printStackTrace()
            }
        }
    }

    // Hent venner som har bursdag på en gitt dag og måned
    fun getFriendsWithBirthday(day: Int, month: Int, onResult: (List<Venner>) -> Unit) {
        viewModelScope.launch {
            try {
                val vennerMedBursdag = repository.getFriendsWithBirthday(day, month)
                onResult(vennerMedBursdag)
            } catch (e: Exception) {
                // Legg til logging eller feilhåndtering her
                e.printStackTrace()
            }
        }
    }
}