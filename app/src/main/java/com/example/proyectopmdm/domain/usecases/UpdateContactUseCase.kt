package com.example.proyectopmdm.domain.usecases

import com.example.proyectopmdm.data.models.Contacto
import com.example.proyectopmdm.data.models.ContactsDao
import javax.inject.Inject

class UpdateContactUseCase @Inject constructor() {
    operator fun invoke(pos: Int, contact: Contacto): List<Contacto> {
        return ContactsDao.dao.updateContact(pos, contact)
    }
}