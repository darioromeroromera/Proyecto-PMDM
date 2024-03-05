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

fun Result<LoginResponse>.toDomain() : UserModel {
    if (this.isSuccess)
        return UserModel(this.getOrNull()!!.name, "", this.getOrNull()!!.email, this.getOrNull()!!.token, "")
    else {
        return UserModel(this.exceptionOrNull()!!.message!!)
    }
}

fun Result<RegisterResponse>.toStringResponse() : String {
    if (this.isSuccess)
        return "Usuario añadido correctamente"
    else
        return this.exceptionOrNull()!!.message!!
}

fun Result<GetContactsResponse>.toDomain() : ContactsListModel {
    if (this.isSuccess) {
        val contacts = this.getOrNull()!!.contactos
        val contactModels = emptyList<ContactModel>().toMutableList()
        contacts.forEach {
            contactModels.add(ContactModel(it.id, it.id_usuario, it.nombre, it.nombre_completo, it.telefono, it.detalles, it.imagen))
        }
        return ContactsListModel("", contactModels)
    } else {
        return ContactsListModel(this.exceptionOrNull()!!.message!!, emptyList<ContactModel>().toMutableList())
    }
}