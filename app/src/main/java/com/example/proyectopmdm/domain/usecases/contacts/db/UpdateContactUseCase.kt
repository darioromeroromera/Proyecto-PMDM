package com.example.proyectopmdm.domain.usecases.contacts.db

import com.example.proyectopmdm.data.models.mem.Contacto
import com.example.proyectopmdm.data.models.mem.ContactsDao
import javax.inject.Inject

class UpdateContactUseCase @Inject constructor() {
    operator fun invoke(pos: Int, contact: Contacto): List<Contacto> {
        return ContactsDao.dao.updateContact(pos, contact)
    }
}