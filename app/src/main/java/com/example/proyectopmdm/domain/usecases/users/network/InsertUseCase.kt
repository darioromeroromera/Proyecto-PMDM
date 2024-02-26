package com.example.proyectopmdm.domain.usecases.users.network

import com.example.proyectopmdm.data.models.network.requests.LoginRequest
import com.example.proyectopmdm.data.models.network.requests.RegisterRequest
import com.example.proyectopmdm.data.services.ContactsNetService
import com.example.proyectopmdm.domain.usecases.models.UserModel
import javax.inject.Inject

class InsertUseCase @Inject constructor(
    private val contactsService: ContactsNetService
) {
    suspend operator fun invoke(user: UserModel) : String {
        return contactsService.register(RegisterRequest(user.email, user.name, user.password)).toStringResponse()
    }
}