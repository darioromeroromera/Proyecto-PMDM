package com.example.proyectopmdm.domain.usecases.users

import com.example.proyectopmdm.data.models.db.UserDao
import com.example.proyectopmdm.domain.usecases.mapping.toDomain
import com.example.proyectopmdm.domain.usecases.models.UserModel
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val dao: UserDao
) {
    suspend operator fun invoke(name: String, password: String): UserModel? {
        val user = dao.getUser(name, password)
        user?.let {
            return user.toDomain()
        }
        return null
    }
}