package com.example.birthday_app_379760

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class Arbeider(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.d("WorkManager", "Gjør dagens arbeid!")
        return Result.success()
    }
}