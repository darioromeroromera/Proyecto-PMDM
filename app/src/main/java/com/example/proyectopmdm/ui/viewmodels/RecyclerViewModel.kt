package com.example.proyectopmdm.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectopmdm.data.models.network.MutableContactRepository
import com.example.proyectopmdm.domain.usecases.contacts.network.AddContactNetUseCase
import com.example.proyectopmdm.domain.usecases.contacts.network.DeleteContactNetUseCase
import com.example.proyectopmdm.domain.usecases.models.ContactModel
import com.example.proyectopmdm.domain.usecases.contacts.network.GetContactsNetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RecyclerViewModel @Inject constructor(
    //private val getContactsUseCase : GetContactsUseCase,
    private val getContactsNetUseCase: GetContactsNetUseCase,
    private val addContactsNetUseCase: AddContactNetUseCase,
    private val deleteContactNetUseCase: DeleteContactNetUseCase
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

    fun findContactoById(id: Long) : Int {
        val position = MutableContactRepository.contacts.indexOfFirst {
            it.id == id
        }

        return position
    }
/*

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