package com.example.birthday_app_379760

import android.app.Application
import android.content.Context
import android.util.Log
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
            15, TimeUnit.MINUTES
        ) .setConstraints(
            Constraints.Builder()
                .build()
        )
            .build()
        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "Daglig arbeid",
                ExistingPeriodicWorkPolicy.REPLACE,
                workRequest
            )
        Log.d("MinApp", "Worker startet")
    }
}