package com.example.proyectopmdm.domain.usecases.contacts

import com.example.proyectopmdm.data.models.mem.Contacto
import com.example.proyectopmdm.data.models.mem.ContactsDao
import javax.inject.Inject

class GetContactsUseCase @Inject constructor() {
     operator fun invoke(): List<Contacto> {
         return ContactsDao.dao.getContacts()
     }
}