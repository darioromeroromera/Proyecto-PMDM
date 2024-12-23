package com.example.proyectopmdm.domain.usecases.contacts.network

import com.example.proyectopmdm.data.models.network.responses.contact.AddContactResponse
import com.example.proyectopmdm.data.models.network.responses.contact.DeleteContactResponse
import com.example.proyectopmdm.data.models.network.responses.contact.GetContactsResponse
import com.example.proyectopmdm.data.models.network.responses.contact.PutContactResponse
import com.example.proyectopmdm.domain.usecases.models.AddContactData
import com.example.proyectopmdm.domain.usecases.models.ContactModel
import com.example.proyectopmdm.domain.usecases.models.ContactsListModel
import com.example.proyectopmdm.domain.usecases.models.EditContactData

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
            return AddContactData(data.insertId, "https://img.freepik.com/free-vector/illustration-businessman_53876-5856.jpg", "Contacto añadido correctamente")
        } else {
            return AddContactData(data.insertId, data.imagen, "Contacto añadido correctamente")
        }
    } else {
        return AddContactData(this.exceptionOrNull()!!.message!!)
    }
}

fun Result<DeleteContactResponse>.toArray() : List<String> {
    if (this.isSuccess) {
        val data = this.getOrNull()!!
        if (data.details == null) {
            return listOf(data.result, "Contacto borrado correctamente")
        }
        return listOf(data.result, data.details)
    } else {
        return listOf("error", this.exceptionOrNull()!!.message!!)
    }
}

fun Result<PutContactResponse>.toDomain() : EditContactData {
    if (this.isSuccess) {
        val data = this.getOrNull()!!
        if (data.imagen.equals("")) {
            return EditContactData("https://img.freepik.com/free-vector/illustration-businessman_53876-5856.jpg", "Contacto editado correctamente")
        } else {
            return EditContactData(data.imagen, "Contacto editado correctamente")
        }
    } else {
        return EditContactData(this.exceptionOrNull()!!.message!!)
    }
}