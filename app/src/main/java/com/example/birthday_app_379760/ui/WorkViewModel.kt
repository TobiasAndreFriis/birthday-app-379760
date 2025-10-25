package com.example.birthday_app_379760.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import com.example.birthday_app_379760.MinApp

open class WorkViewModel(application: Application): AndroidViewModel(application) {
    open fun start() {
        (application as MinApp).scheduleDailyWork(application.applicationContext)
    }

    open fun stop() {
        (application as MinApp).cancelDailyWork(application.applicationContext)
    }
}