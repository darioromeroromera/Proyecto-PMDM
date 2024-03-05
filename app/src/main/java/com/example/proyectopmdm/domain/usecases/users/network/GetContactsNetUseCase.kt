package com.example.proyectopmdm.domain.usecases.users.network

import com.example.proyectopmdm.data.models.network.responses.GetContactsResponse
import com.example.proyectopmdm.data.services.ContactsNetService
import com.example.proyectopmdm.domain.usecases.models.ContactModel
import javax.inject.Inject

class GetContactsNetUseCase @Inject constructor(
    private val contactsService: ContactsNetService
) {
    suspend operator fun invoke(token: String) : ContactModel {
        var contactsResult = contactsService.getContacts(token)

    }
}