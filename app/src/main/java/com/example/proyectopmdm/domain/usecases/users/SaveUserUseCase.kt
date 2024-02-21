package com.example.proyectopmdm.domain.usecases.users

import com.example.proyectopmdm.data.models.db.UserDao
import com.example.proyectopmdm.data.models.db.UserEntity
import com.example.proyectopmdm.domain.usecases.models.UserModel
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val dao: UserDao
){
    suspend operator fun invoke(userModel: UserModel) {
        val userEntity = dao.saveUser(UserEntity(name = userModel.name, password = userModel.password, email = userModel.email))
    }
}