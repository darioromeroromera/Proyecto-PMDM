package com.example.proyectopmdm.domain.usecases.contacts.network

import com.example.proyectopmdm.data.services.ContactsNetService
import javax.inject.Inject

class DeleteContactNetUseCase @Inject constructor(
    private val contactsService: ContactsNetService
){
    suspend operator fun invoke(token: String, id: Long) : List<String> {
        return contactsService.deleteContact(token, id).toArray()
    }
}