package com.example.birthday_app_379760

import android.content.Context
import android.content.SharedPreferences
import android.telephony.SmsManager
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.birthday_app_379760.data.AppDatabase
import com.example.birthday_app_379760.data.Venner
import kotlinx.coroutines.runBlocking
import java.util.Calendar

class Arbeider(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "app_preferences", Context.MODE_PRIVATE
    )

    override fun doWork(): Result {
        Log.d("WorkManager", "Starter daglig arbeid!")

        return try {
            // Hent dagens dato (dag og måned)
            val calendar = Calendar.getInstance()
            val todayDay = calendar.get(Calendar.DAY_OF_MONTH)
            val todayMonth = calendar.get(Calendar.MONTH) + 1 // MONTH er 0-basert

            // Hent venner med bursdag i dag fra databasen
            val database = AppDatabase.getInstance(applicationContext) // Singleton-instans av databasen
            val vennerDao = database.vennerDao()
            val vennerMedBursdag: List<Venner>

            Log.d("Arbeider", "${todayDay}, ${todayMonth}")

            runBlocking {
                vennerMedBursdag = vennerDao.getFriendsWithBirthday(todayDay, todayMonth)
            }
            if (vennerMedBursdag.isEmpty()){
                Log.d("Arbeider", "Det er ingen venner med bursdag i dag!")
            }
            else{
                Log.d("Arbeider", "Det er hvertfall 1 venn med bursdag i dag!")
            }

            // Hent SmsManager ved hjelp av getSystemService
            val smsManager: SmsManager = applicationContext.getSystemService(SmsManager::class.java)

            // Send SMS til alle venner som har bursdag i dag
            for (venn in vennerMedBursdag) {
                // Bruk standardmelding hvis ingen spesifikk melding er satt for denne vennen
                val messageToSend = venn.message.ifBlank { "Gratulerer med dagen!" }

                try {
                    // Send SMS med SmsManager
                    //smsManager.sendTextMessage(venn.telephoneNr, null, messageToSend, null, null)
                    Log.d("Test SMS", "Sender sms til ${venn.telephoneNr} med melding '${messageToSend}'")
                    Log.d("WorkManager", "SMS sendt til ${venn.telephoneNr} med melding: $messageToSend")
                } catch (e: Exception) {
                    Log.e("WorkManager", "Feil under sending av SMS til ${venn.telephoneNr}: ", e)
                }
            }

            Log.d("WorkManager", "Daglig arbeid utført!")
            Result.success()
        } catch (e: Exception) {
            Log.e("WorkManager", "Feil under utførelse av daglig arbeid: ", e)
            Result.failure()
        }
    }
}