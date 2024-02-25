package com.example.proyectopmdm.domain.usecases.users.network

import android.util.Log
import com.example.proyectopmdm.data.models.network.requests.LoginRequest
import com.example.proyectopmdm.data.services.ContactsNetService
import javax.inject.Inject
import com.example.proyectopmdm.domain.usecases.models.UserModel

class LoginUseCase @Inject constructor(
    private val contactsService: ContactsNetService
){
    suspend operator fun invoke(username: String, password: String) : UserModel? {
        val asdf = contactsService.login(LoginRequest(username, password))
        Log.d("AAA", asdf.toString())
        return asdf.toDomain()
    }
}