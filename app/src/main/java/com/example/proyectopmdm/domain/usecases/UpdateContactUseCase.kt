package com.example.proyectopmdm.domain.usecases

import com.example.proyectopmdm.data.models.Contacto
import com.example.proyectopmdm.data.models.ContactsDao

class UpdateContactUseCase {
    operator fun invoke(pos: Int, contact: Contacto): List<Contacto> {
        return ContactsDao.dao.updateContact(pos, contact)
    }
}