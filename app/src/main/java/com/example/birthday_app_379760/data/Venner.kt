package com.example.birthday_app_379760.data

import android.R
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "venner")
data class Venner(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val birthDay: Int,
    val birthMonth: Int,
    val telephoneNr: String,
    var message: String
)