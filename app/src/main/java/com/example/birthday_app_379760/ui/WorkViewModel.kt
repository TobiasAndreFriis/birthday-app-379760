package com.example.birthday_app_379760.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import com.example.birthday_app_379760.MinApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkViewModel(application: Application) : AndroidViewModel(application) {
    private val _isServiceRunning = MutableStateFlow(false)
    val isServiceRunning = _isServiceRunning.asStateFlow()

    fun start() {
        (getApplication<Application>() as MinApp).scheduleDailyWork(getApplication<Application>().applicationContext)
        _isServiceRunning.value = true
    }

    fun stop() {
        (getApplication<Application>() as MinApp).cancelDailyWork(getApplication<Application>().applicationContext)
        _isServiceRunning.value = false
    }
}