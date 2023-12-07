package com.example.proyectopmdm.dao

import com.example.proyectopmdm.interfaces.InterfazDao
import com.example.proyectopmdm.models.Contacto
import com.example.proyectopmdm.objects_models.Repository

class ContactosDao: InterfazDao {
    companion object {
        val dao: ContactosDao by lazy {
            ContactosDao()
        }
    }

    override fun getContactsData(): List<Contacto> = Repository.listaContactos

}