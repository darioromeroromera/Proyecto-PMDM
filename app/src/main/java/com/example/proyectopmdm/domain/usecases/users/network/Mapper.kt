package com.example.proyectopmdm.domain.usecases.users.network

import android.util.Log
import com.example.proyectopmdm.data.models.network.responses.GetContactsResponse
import com.example.proyectopmdm.data.models.network.responses.LoginResponse
import com.example.proyectopmdm.data.models.network.responses.RegisterResponse
import com.example.proyectopmdm.domain.usecases.models.ContactModel
import com.example.proyectopmdm.domain.usecases.models.ContactsListModel
import com.example.proyectopmdm.domain.usecases.models.UserModel
import retrofit2.Response
import kotlin.math.log

fun <T> T?.whenNull(block: () -> Unit) = this ?: block()

fun Result<LoginResponse>.toDomain() : UserModel {
    if (this.isSuccess) {
        val user = this.getOrNull()!!
        if (user.imagen != null)
            return UserModel(user.name, "", user.email, user.token, "", user.imagen)
        else
            return UserModel(this.getOrNull()!!.name, "", this.getOrNull()!!.email, this.getOrNull()!!.token, "")
    }
    else {
        return UserModel(this.exceptionOrNull()!!.message!!)
    }
}

fun Result<RegisterResponse>.toStringResponse() : String {
    if (this.isSuccess)
        return "Usuario añadido correctamente"
    else {
        return this.exceptionOrNull()!!.message!!
    }
}

fun Result<GetContactsResponse>.toDomain() : ContactsListModel {
    if (this.isSuccess) {
        val contactList = this.getOrNull()!!
        val contactModels = emptyList<ContactModel>().toMutableList()
        contactList.contactos.forEach {
            if (it.imagen != null) {
                contactModels.add(ContactModel(it.id, it.id_usuario, it.nombre, it.nombre_completo, it.telefono, it.detalles, it.imagen))
            } else {
                contactModels.add(ContactModel(it.id, it.id_usuario, it.nombre, it.nombre_completo, it.telefono, it.detalles,
                    "https://img.freepik.com/free-vector/illustration-businessman_53876-5856.jpg"))
            }
        }
        return ContactsListModel(contactList.details, contactModels)
    } else {
        return ContactsListModel(this.exceptionOrNull()!!.message!!)
    }
}