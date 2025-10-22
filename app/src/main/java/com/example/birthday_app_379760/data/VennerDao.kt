package com.example.birthday_app_379760.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface VennerDao {
    @Insert
    suspend fun insert(venner: Venner)
    @Query("SELECT * FROM venner_table ORDER BY id DESC")
    fun getAllVenner(): Flow<List<Venner>>
}