package com.example.proyectopmdm.domain.usecases

import com.example.proyectopmdm.data.models.UserDao
import com.example.proyectopmdm.domain.usecases.models.UserModel
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val dao: UserDao
){
    suspend operator fun invoke(name: String, password: String, email: String) {
        val userEntity = dao.saveUser(name, password, email)
    }
}