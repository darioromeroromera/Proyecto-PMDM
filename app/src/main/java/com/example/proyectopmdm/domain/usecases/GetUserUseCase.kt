package com.example.proyectopmdm.domain.usecases

import com.example.proyectopmdm.data.models.UserDao
import com.example.proyectopmdm.domain.usecases.models.UserModel
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val dao: UserDao
) {
    suspend operator fun invoke(name: String, password: String): UserModel? {
        val userEntity = dao.getUser(name, password)
        userEntity?.let {
            return UserModel(userEntity.name!!, userEntity.password!!, userEntity.email!!)
        }
        return null
    }
}