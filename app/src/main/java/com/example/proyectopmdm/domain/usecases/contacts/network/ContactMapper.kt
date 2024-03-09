package com.example.proyectopmdm.domain.usecases.contacts.network

import com.example.proyectopmdm.data.models.network.responses.contact.AddContactResponse
import com.example.proyectopmdm.data.models.network.responses.contact.GetContactsResponse
import com.example.proyectopmdm.domain.usecases.models.AddContactData
import com.example.proyectopmdm.domain.usecases.models.ContactModel
import com.example.proyectopmdm.domain.usecases.models.ContactsListModel

fun Result<GetContactsResponse>.toDomain() : ContactsListModel {
    if (this.isSuccess) {
        val contactList = this.getOrNull()!!
        val contactModels = emptyList<ContactModel>().toMutableList()
        contactList.contactos.forEach {
            if (it.imagen != null) {
                contactModels.add(ContactModel(it.id, it.nombre, it.nombre_completo, it.telefono, it.detalles, it.imagen))
            } else {
                contactModels.add(
                    ContactModel(it.id, it.nombre, it.nombre_completo, it.telefono, it.detalles,
                    "https://img.freepik.com/free-vector/illustration-businessman_53876-5856.jpg")
                )
            }
        }
        return ContactsListModel(contactList.details, contactModels)
    } else {
        return ContactsListModel(this.exceptionOrNull()!!.message!!)
    }
}

fun Result<AddContactResponse>.toDomain() : AddContactData {
    if (this.isSuccess) {
        val data = this.getOrNull()!!
        if (data.imagen.equals("")) {
            return AddContactData(this.getOrNull()!!.insertId, "https://img.freepik.com/free-vector/illustration-businessman_53876-5856.jpg", "Contacto añadido correctamente")
        } else {
            return AddContactData(this.getOrNull()!!.insertId, this.getOrNull()!!.imagen, "Contacto añadido correctamente")
        }
    } else {
        return AddContactData(this.exceptionOrNull()!!.message!!)
    }
}