package com.example.proyectopmdm.domain.usecases.contacts.network

import com.example.proyectopmdm.data.models.network.requests.AddPutContactRequest
import com.example.proyectopmdm.data.services.ContactsNetService
import com.example.proyectopmdm.domain.usecases.models.AddContactData
import com.example.proyectopmdm.domain.usecases.models.ContactModel
import javax.inject.Inject

class AddContactNetUseCase@Inject constructor(
    private val contactsService: ContactsNetService
) {
    suspend operator fun invoke(token: String, newContact: ContactModel) : AddContactData{
        val request = AddPutContactRequest(newContact.nombre, newContact.nombreCompleto, newContact.telefono, newContact.detalles, newContact.imagen)

        return contactsService.addContact(token, request).toDomain()
    }
}