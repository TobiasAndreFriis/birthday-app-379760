package com.example.birthday_app_379760.data

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Venner::class], version = 1, exportSchema =
    false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vennerDao(): VennerDao
}