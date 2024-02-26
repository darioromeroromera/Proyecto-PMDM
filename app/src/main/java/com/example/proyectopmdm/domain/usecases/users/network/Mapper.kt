package com.example.proyectopmdm.domain.usecases.users.network

import android.util.Log
import com.example.proyectopmdm.data.models.network.responses.LoginResponse
import com.example.proyectopmdm.data.models.network.responses.RegisterResponse
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