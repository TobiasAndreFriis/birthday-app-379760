package com.example.birthday_app_379760.repositories

import com.example.birthday_app_379760.data.Venner
import com.example.birthday_app_379760.data.VennerDao
import kotlinx.coroutines.flow.Flow

class VennerRepository(private val dao: VennerDao) {

    // Live oppdaterte data for alle venner
    val alleVenner: Flow<List<Venner>> = dao.getAllVenner()

    // Legg til en venn i databasen
    suspend fun insert(venner: Venner) {
        dao.insert(venner)
    }

    // Oppdater en venns data i databasen
    suspend fun update(venner: Venner) {
        dao.update(venner)
    }

    // Slett en venn fra databasen
    suspend fun delete(venner: Venner) {
        dao.delete(venner)
    }

    // Hent venner som har bursdag på en spesifikk dag og måned
    suspend fun getFriendsWithBirthday(day: Int, month: Int): List<Venner> {
        return dao.getFriendsWithBirthday(day, month)
    }
}