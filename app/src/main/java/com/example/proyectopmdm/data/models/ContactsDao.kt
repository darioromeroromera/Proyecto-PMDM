package com.example.proyectopmdm.data.models

import com.example.proyectopmdm.data.datasource.Repository

class ContactsDao: ContactsDaoInterface {
    companion object {
        val dao: ContactsDao by lazy {
            ContactsDao()
        }
    }

    override fun getContactsFromDatasource(): List<Contacto> {
        MutableRepository.contacts = Repository.listaContactos.toMutableList()
        return MutableRepository.contacts
    }

    override fun getContacts(): List<Contacto> = MutableRepository.contacts
    override fun addContact(contact: Contacto): List<Contacto> {
        MutableRepository.contacts.add(contact)
        return getContacts()
    }

    override fun removeContact(pos: Int): List<Contacto> {
        MutableRepository.contacts.removeAt(pos)
        return getContacts()
    }

    override fun updateContact(pos: Int, contact: Contacto): List<Contacto> {
        MutableRepository.contacts.set(pos, contact)
        return getContacts()
    }


}