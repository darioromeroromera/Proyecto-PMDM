package com.example.proyectopmdm.domain.usecases.contacts

import com.example.proyectopmdm.data.models.mem.Contacto
import com.example.proyectopmdm.data.models.mem.ContactsDao
import javax.inject.Inject

class RemoveContactUseCase @Inject constructor() {
    operator fun invoke(pos: Int): List<Contacto> {
        return ContactsDao.dao.removeContact(pos)
    }
}