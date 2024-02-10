package com.example.proyectopmdm.domain.usecases

import com.example.proyectopmdm.data.models.Contacto
import com.example.proyectopmdm.data.models.ContactsDao

class AddContactUseCase {
    operator fun invoke(contact: Contacto) : List<Contacto> {
        return ContactsDao.dao.addContact(contact)
    }
}