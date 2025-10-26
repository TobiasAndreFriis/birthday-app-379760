package com.example.birthday_app_379760.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Venner::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun vennerDao(): VennerDao

    companion object {
        // Singleton-instans av databasen
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            // Bruker "double-checked locking" for å sikre tråd-sikkerhet
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "venner_db" // Navnet på databasen
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}