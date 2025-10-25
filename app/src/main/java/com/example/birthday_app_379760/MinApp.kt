package com.example.birthday_app_379760

import android.app.Application
import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MinApp: Application() {
    fun cancelDailyWork(context: Context){
        WorkManager.getInstance(context).cancelUniqueWork("Daglig arbeid")
    }
    fun scheduleDailyWork(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<Arbeider>(
            24, TimeUnit.HOURS
        ) .setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED) // only run with internet
                .build()
        )
            .build()
        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "Daglig arbeid",
                ExistingPeriodicWorkPolicy.KEEP, // keep previous if already scheduled
                workRequest
            )
    }
}