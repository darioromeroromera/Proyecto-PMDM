package com.example.proyectopmdm.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectopmdm.data.models.mem.Contacto
import com.example.proyectopmdm.data.models.network.MutableContactRepository
import com.example.proyectopmdm.domain.usecases.contacts.AddContactUseCase
import com.example.proyectopmdm.domain.usecases.contacts.GetContactsUseCase
import com.example.proyectopmdm.domain.usecases.contacts.RemoveContactUseCase
import com.example.proyectopmdm.domain.usecases.contacts.UpdateContactUseCase
import com.example.proyectopmdm.domain.usecases.models.ContactsListModel
import com.example.proyectopmdm.domain.usecases.users.network.GetContactsNetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RecyclerViewModel @Inject constructor(
    //private val getContactsUseCase : GetContactsUseCase,
    private val getContactsNetUseCase: GetContactsNetUseCase,
    private val addContactUseCase : AddContactUseCase,
    private val removeContactUseCase : RemoveContactUseCase,
    private val updateContactUseCase : UpdateContactUseCase
) : ViewModel() {
    //var contactsLiveData = MutableLiveData<List<Contacto>>()
    var contactsLiveData = MutableLiveData<ContactsListModel>()

    fun listContacts(token: String) {
        viewModelScope.launch {
            val contacts = getContactsNetUseCase(token)
            contactsLiveData.value = contacts
            MutableContactRepository.contacts = contacts.contactos
            contacts.contactos.forEach {
                Log.d("AAA", it.nombreCompleto)
            }
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