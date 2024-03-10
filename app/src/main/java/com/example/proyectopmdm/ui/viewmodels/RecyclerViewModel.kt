package com.example.proyectopmdm.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectopmdm.data.models.network.MutableContactRepository
import com.example.proyectopmdm.domain.usecases.contacts.network.AddContactNetUseCase
import com.example.proyectopmdm.domain.usecases.contacts.network.DeleteContactNetUseCase
import com.example.proyectopmdm.domain.usecases.models.ContactModel
import com.example.proyectopmdm.domain.usecases.contacts.network.GetContactsNetUseCase
import com.example.proyectopmdm.domain.usecases.contacts.network.UpdateContactNetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RecyclerViewModel @Inject constructor(
    private val getContactsNetUseCase: GetContactsNetUseCase,
    private val addContactsNetUseCase: AddContactNetUseCase,
    private val deleteContactNetUseCase: DeleteContactNetUseCase,
    private val updateContactNetUseCase: UpdateContactNetUseCase
) : ViewModel() {
    var contactsLiveData = MutableLiveData<MutableList<ContactModel>>()

    var messageLiveData = MutableLiveData<String>("")

    fun listContacts(token: String) {
        viewModelScope.launch {
            val contactList = getContactsNetUseCase(token)
            contactsLiveData.value = contactList.contactos
            MutableContactRepository.contacts = contactList.contactos

            messageLiveData.value = contactList.details
        }
    }

    fun addContact(token: String, newContact: ContactModel) {
        viewModelScope.launch {
            val data = addContactsNetUseCase(token, newContact)
            data.imagen?.let {
                newContact.id = data.insertId
                newContact.imagen = data.imagen

                val contactList = MutableContactRepository.contacts
                contactList.add(newContact)

                contactsLiveData.value = contactList
                MutableContactRepository.contacts = contactList
            }

            messageLiveData.value = data.details
        }
    }

    fun removeContact(token: String, id: Long, pos: Int) {
        viewModelScope.launch {
            val responseData = deleteContactNetUseCase(token, id)
            if (!responseData.get(0).equals("error")) {
                val contactList = MutableContactRepository.contacts
                contactList.removeAt(pos)
                contactsLiveData.value = contactList
                MutableContactRepository.contacts = contactList
            }
            messageLiveData.value = responseData.get(1)
        }


    }

    fun updateContact(token: String, contact: ContactModel, id: Long, pos: Int) {
        viewModelScope.launch {
            val data = updateContactNetUseCase(token, contact, id)


            data.imagen?.let {
                Log.d("AAA", "Imagen de contacto: " + contact.imagen.toString())
                Log.d("AAA", "Imagen de api: " + data.imagen.toString())

                contact.imagen = data.imagen
                Log.d("AAA", "Nueva imagen de contacto: " + contact.imagen.toString())
                val contactList = MutableContactRepository.contacts
                contactList[pos] = contact

                contactsLiveData.value = contactList
                MutableContactRepository.contacts = contactList
            }
            messageLiveData.value = data.details
        }
    }

    /*

    fun updateContact(pos: Int, contact: Contacto) {
        viewModelScope.launch {
            val contacts = updateContactUseCase(pos, contact)
            contactsLiveData.value = contacts
        }
    }

 */
}