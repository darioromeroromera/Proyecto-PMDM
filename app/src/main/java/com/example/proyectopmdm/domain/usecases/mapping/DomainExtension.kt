package com.example.proyectopmdm.domain.usecases.mapping

import com.example.proyectopmdm.data.models.db.UserEntity
import com.example.proyectopmdm.domain.usecases.models.UserModel


fun UserEntity.toDomain() : UserModel? {
    this?.let {
        return UserModel(this.name!!, this.password!!, this.email!!, "")
    }
    return null
}