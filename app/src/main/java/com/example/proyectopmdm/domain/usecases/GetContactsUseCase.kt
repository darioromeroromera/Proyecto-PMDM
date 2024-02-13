package com.example.proyectopmdm.domain.usecases

import com.example.proyectopmdm.data.models.Contacto
import com.example.proyectopmdm.data.models.ContactsDao
import javax.inject.Inject

class GetContactsUseCase @Inject constructor() {
     operator fun invoke(): List<Contacto> {
         return ContactsDao.dao.getContacts()
     }
}