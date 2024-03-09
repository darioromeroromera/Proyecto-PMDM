package com.example.proyectopmdm.domain.usecases.users.network

import com.example.proyectopmdm.data.models.network.responses.auth.LoginResponse
import com.example.proyectopmdm.data.models.network.responses.auth.RegisterResponse
import com.example.proyectopmdm.domain.usecases.models.UserModel


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