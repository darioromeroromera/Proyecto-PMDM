package com.example.proyectopmdm.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectopmdm.data.models.network.MutableContactRepository
import com.example.proyectopmdm.domain.usecases.contacts.db.AddContactUseCase
import com.example.proyectopmdm.domain.usecases.contacts.db.RemoveContactUseCase
import com.example.proyectopmdm.domain.usecases.contacts.db.UpdateContactUseCase
import com.example.proyectopmdm.domain.usecases.contacts.network.AddContactsNetUseCase
import com.example.proyectopmdm.domain.usecases.models.ContactModel
import com.example.proyectopmdm.domain.usecases.contacts.network.GetContactsNetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RecyclerViewModel @Inject constructor(
    //private val getContactsUseCase : GetContactsUseCase,
    private val getContactsNetUseCase: GetContactsNetUseCase,
    private val addContactsNetUseCase: AddContactsNetUseCase
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
            if (data.imagen != null) {
                newContact.id = data.insertId
                newContact.imagen = data.imagen

                val contactList = MutableContactRepository.contacts
                contactList.add(newContact)

                contactsLiveData.value = contactList
            }

            messageLiveData.value = data.details
        }
    }
/*
    fun addContact(contact: Contacto) {
        viewModelScope.launch {
            val contacts = addContactUseCase(contact)
            contactsLiveData.value = contacts
        }
    }

    fun removeContact(pos: Int) {
        viewModelScope.launch {
            val contacts = removeContactUseCase(pos)
            contactsLiveData.value = contacts
        }
    }

    fun updateContact(pos: Int, contact: Contacto) {
        viewModelScope.launch {
            val contacts = updateContactUseCase(pos, contact)
            contactsLiveData.value = contacts
        }
    }

 */
}