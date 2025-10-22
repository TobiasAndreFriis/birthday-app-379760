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
    val venner: StateFlow<List<Venner>> =
        repository.alleVenner.stateIn(viewModelScope,
            SharingStarted.Lazily, emptyList())
    fun addVenn(name: String, birthday: String, telephoneNr: String) {
        viewModelScope.launch {
            repository.insert(Venner(name = name, birthday = birthday, telephoneNr = telephoneNr))
        }
    }
}