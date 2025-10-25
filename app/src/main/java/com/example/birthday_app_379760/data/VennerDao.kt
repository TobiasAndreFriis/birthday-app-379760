package com.example.birthday_app_379760.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VennerDao {
    // Legg til en ny venn
    @Insert
    suspend fun insert(venner: Venner)

    // Oppdater en venn
    @Update
    suspend fun update(venner: Venner)

    // Slett en venn
    @Delete
    suspend fun delete(venner: Venner)

    // Hent alle venner
    @Query("SELECT * FROM venner ORDER BY id DESC")
    fun getAllVenner(): Flow<List<Venner>>

    // Hent venner som har bursdag (dag og måned)
    @Query("SELECT * FROM venner WHERE birthDay = :day AND birthMonth = :month")
    fun getFriendsWithBirthday(day: Int, month: Int): List<Venner>
}