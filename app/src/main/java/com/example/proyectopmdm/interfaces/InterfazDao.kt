package com.example.proyectopmdm.interfaces

import com.example.proyectopmdm.models.Contacto

interface InterfazDao {
    fun getContactsData(): List<Contacto>
}