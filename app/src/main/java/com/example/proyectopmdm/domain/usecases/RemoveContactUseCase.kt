package com.example.proyectopmdm.domain.usecases

import com.example.proyectopmdm.data.models.Contacto
import com.example.proyectopmdm.data.models.ContactsDao
import javax.inject.Inject

class RemoveContactUseCase @Inject constructor() {
    operator fun invoke(pos: Int): List<Contacto> {
        return ContactsDao.dao.removeContact(pos)
    }
}