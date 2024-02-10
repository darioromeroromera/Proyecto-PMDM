package com.example.proyectopmdm.data.models

class MutableRepository {
    companion object {
        var contacts:MutableList<Contacto> = emptyList<Contacto>().toMutableList()
    }
}