package com.example.proyectopmdm.domain.usecases.models

class ContactsListModel(
   val details: String,
   val contactos: MutableList<ContactModel>
) {
   constructor(
      details: String
   ) : this(
      details,
      contactos = emptyList<ContactModel>().toMutableList()
   )
}