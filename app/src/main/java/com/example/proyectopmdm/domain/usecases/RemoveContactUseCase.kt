package com.example.proyectopmdm.domain.usecases

import com.example.proyectopmdm.data.models.Contacto
import com.example.proyectopmdm.data.models.ContactsDao

class RemoveContactUseCase {
    operator fun invoke(pos: Int): List<Contacto> {
        return ContactsDao.dao.removeContact(pos)
    }
}