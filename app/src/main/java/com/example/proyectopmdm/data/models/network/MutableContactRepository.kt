package com.example.proyectopmdm.data.models.network

import com.example.proyectopmdm.data.models.mem.Contacto
import com.example.proyectopmdm.domain.usecases.models.ContactModel
import com.example.proyectopmdm.domain.usecases.models.ContactsListModel

class MutableContactRepository {
    companion object {
        var contacts:MutableList<ContactModel> = emptyList<ContactModel>().toMutableList()
    }
}