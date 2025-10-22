package com.example.birthday_app_379760.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "venner_table")
data class Venner(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val birthday: String,
    val telephoneNr: String
)