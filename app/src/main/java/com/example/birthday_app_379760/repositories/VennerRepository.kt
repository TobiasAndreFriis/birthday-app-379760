package com.example.birthday_app_379760.repositories

import com.example.birthday_app_379760.data.Venner
import com.example.birthday_app_379760.data.VennerDao
import kotlinx.coroutines.flow.Flow
class VennerRepository(private val dao: VennerDao) {
    val alleVenner: Flow<List<Venner>> = dao.getAllVenner()
    suspend fun insert(venner: Venner) {
        dao.insert(venner)
    }
}