package com.example.proyectopmdm.domain.usecases.contacts.network

import com.example.proyectopmdm.data.services.ContactsNetService
import com.example.proyectopmdm.domain.usecases.models.ContactsListModel
import com.example.proyectopmdm.domain.usecases.users.network.toDomain
import javax.inject.Inject

class GetContactsNetUseCase @Inject constructor(
    private val contactsService: ContactsNetService
) {
    suspend operator fun invoke(token: String) : ContactsListModel {
        val contactsResult = contactsService.getContacts(token)
        return contactsResult.toDomain()
    }
}