package com.example.proyectopmdm.domain.usecases.users.network

import com.example.proyectopmdm.data.models.network.responses.LoginResponse
import com.example.proyectopmdm.domain.usecases.models.UserModel
import retrofit2.Response
import kotlin.math.log

fun Result<LoginResponse>.toDomain() : UserModel? {
    if (this.isSuccess)
        return UserModel(this.getOrNull()!!.name, "", this.getOrNull()!!.email, this.getOrNull()!!.token)
    else
        return null
}