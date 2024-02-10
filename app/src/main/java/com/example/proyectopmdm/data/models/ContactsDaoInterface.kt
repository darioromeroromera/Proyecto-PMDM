package com.example.proyectopmdm.data.models

interface ContactsDaoInterface {
    fun getContactsFromDatasource(): List<Contacto>

    fun getContacts(): List<Contacto>

    fun addContact(contact: Contacto): List<Contacto>

    fun removeContact(pos: Int): List<Contacto>

    fun updateContact(pos: Int, contact: Contacto): List<Contacto>
}