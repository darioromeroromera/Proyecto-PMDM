package com.example.proyectopmdm.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.proyectopmdm.data.models.Contacto
import com.example.proyectopmdm.domain.usecases.AddContactUseCase
import com.example.proyectopmdm.domain.usecases.GetContactsUseCase
import com.example.proyectopmdm.domain.usecases.RemoveContactUseCase
import com.example.proyectopmdm.domain.usecases.UpdateContactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RecyclerViewModel @Inject constructor(
    private val getContactsUseCase : GetContactsUseCase,
    private val addContactUseCase : AddContactUseCase,
    private val removeContactUseCase : RemoveContactUseCase,
    private val updateContactUseCase : UpdateContactUseCase
) : ViewModel() {
    var contactsLiveData = MutableLiveData<List<Contacto>>()

    fun listContacts() {
        viewModelScope.launch {
            val contacts = getContactsUseCase()
            contactsLiveData.value = contacts
        }
    }

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
}