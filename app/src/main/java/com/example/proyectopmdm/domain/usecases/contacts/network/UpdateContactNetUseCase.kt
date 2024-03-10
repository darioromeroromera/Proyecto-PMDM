package com.example.proyectopmdm.domain.usecases.contacts.network

import android.util.Log
import com.example.proyectopmdm.data.models.network.requests.AddPutContactRequest
import com.example.proyectopmdm.data.services.ContactsNetService
import com.example.proyectopmdm.domain.usecases.models.ContactModel
import com.example.proyectopmdm.domain.usecases.models.EditContactData
import javax.inject.Inject

class UpdateContactNetUseCase @Inject constructor(
    private val contactsService: ContactsNetService
) {
    suspend operator fun invoke(token: String, newContact: ContactModel, id: Long) : EditContactData {
        val request = AddPutContactRequest(newContact.nombre, newContact.nombreCompleto, newContact.telefono, newContact.detalles, newContact.imagen)
        Log.d("AAA", request.imagen.toString())

        return contactsService.updateContact(token, request, id).toDomain()
    }
}