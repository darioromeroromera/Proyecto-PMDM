package com.example.proyectopmdm.domain.usecases.users.db

import com.example.proyectopmdm.data.models.db.UserDao
import com.example.proyectopmdm.domain.usecases.mapping.toDomain
import com.example.proyectopmdm.domain.usecases.models.UserModel
import javax.inject.Inject

class GetUserNameOnlyUseCase @Inject constructor(
    private val dao: UserDao
) {
    suspend operator fun invoke(name: String): UserModel? {
        val user = dao.getUserByUsername(name)
        user?.let {
            return user.toDomain()
        }
        return null
    }
}